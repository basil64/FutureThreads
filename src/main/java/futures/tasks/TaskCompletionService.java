package futures.tasks;

import futures.TaskExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;

@Component
public class TaskCompletionService {
    private final static Logger LOGGER = LoggerFactory.getLogger(TaskCompletionService.class);
    private CompletionService<String> completionService;

    @Autowired
    public TaskCompletionService(TaskExecutor taskExecutor) {
        this.completionService = new ExecutorCompletionService<>(taskExecutor.getExecutor());
        LOGGER.info("TaskCompletionService created");
    }

    public CompletionService<String> getCompletionService() {
        return completionService;
    }
}
