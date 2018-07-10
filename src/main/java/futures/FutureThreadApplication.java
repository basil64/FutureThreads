package futures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FutureThreadApplication {
    final static Logger LOGGER = LoggerFactory.getLogger(FutureThreadApplication.class);
    public static void main(String[] args) {
        LOGGER.info("Hello [{}]", 100);
		LOGGER.info("This is test message 1");
		LOGGER.info("This is test message 2");
        SpringApplication.run(FutureThreadApplication.class, args);
    }
}
