package futures.tasks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BigTaskImpl extends Task {
    final static Logger LOGGER = LoggerFactory.getLogger(BigTaskImpl.class);


    @Override
    public String call() throws Exception {
        return executeTask(LOGGER, "Big Task " + id);
    }
}
