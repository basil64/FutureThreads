package futures.tasks;

import org.slf4j.Logger;

import java.util.concurrent.Callable;

public abstract class Task implements Callable<String> {

    protected int iterations;
    protected int id;
    static protected int taskId = 0;

    public Task() {
        this.id = taskId++;
    }

    String executeTask(Logger LOGGER, String taskName) {
        LOGGER.info(taskName + " started. Number of iteration [{}]", iterations);
        for (int i = 0; i < iterations; i++) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return taskName + " completed";
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getIterations() {
        return iterations;
    }

    public int getId() {
        return id;
    }
}
