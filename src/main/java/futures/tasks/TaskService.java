package futures.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Supplier;

@Service
public class TaskService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaskService.class);
    private final TaskCompletionService taskCompletionService;
    private final Supplier<SmallTaskImpl> smallTaskSupplier;
    private final Supplier<BigTaskImpl> bigTaskSupplier;

    @Autowired
    private ObjectFactory<SmallTaskImpl> smallTaskObjectFactory;

    @Autowired
    public TaskService(TaskCompletionService taskCompletionService,
                       @Qualifier("SmallTaskSupplier") Supplier<SmallTaskImpl> smallTaskSupplier,
                       @Qualifier("BigTaskSupplier") Supplier<BigTaskImpl> bigTaskSupplier) {
        this.taskCompletionService = taskCompletionService;
        this.smallTaskSupplier = smallTaskSupplier;
        this.bigTaskSupplier = bigTaskSupplier;
        LOGGER.info("TaskService created");
    }

    @PostConstruct
    public void startService() {
        SmallTaskImpl smallTask1 = smallTaskObjectFactory.getObject();
        smallTask1.setIterations(20);
        SmallTaskImpl smallTask2 = smallTaskObjectFactory.getObject();
        smallTask2.setIterations(35);
        BigTaskImpl bigTask1 = bigTaskSupplier.get();
        bigTask1.setIterations(100);
        LOGGER.info("bigTask1 id = [{}]", bigTask1.getId());
        BigTaskImpl bigTask2 = bigTaskSupplier.get();
        LOGGER.info("bigTask2 id = [{}]", bigTask2.getId());
        bigTask2.setIterations(70);
        CompletionService<String> completionService = taskCompletionService.getCompletionService();
        completionService.submit(bigTask1);
        completionService.submit(smallTask1);
        completionService.submit(bigTask2);
        completionService.submit(smallTask2);
        while (true) {
            try {
                Future<String> future = completionService.take();
                String result = future.get();
                LOGGER.info("Task finished [{}]", result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
