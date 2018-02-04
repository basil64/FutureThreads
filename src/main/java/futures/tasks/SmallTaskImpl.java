package futures.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SmallTaskImpl extends Task {
    final static Logger LOGGER = LoggerFactory.getLogger(SmallTaskImpl.class);

    @Override
    public String call() throws Exception {
        return executeTask(LOGGER, "Small Task " + id);
    }
}
