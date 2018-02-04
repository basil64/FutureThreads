package futures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TaskExecutor{
    private final static Logger LOGGER = LoggerFactory.getLogger(TaskExecutor.class);
    private ExecutorService executor;

    public TaskExecutor() {
        executor = Executors.newFixedThreadPool(10);
        LOGGER.info("Executor created");
    }

    public ExecutorService getExecutor() {
        return executor;
    }
}
