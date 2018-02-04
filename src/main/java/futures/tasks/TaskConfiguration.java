package futures.tasks;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.function.Supplier;

@Configuration
public class TaskConfiguration {
    @Bean
    @Scope(value = "prototype")
    public BigTaskImpl getBigTaskImpl() {
        return new BigTaskImpl();
    }

    @Bean
    @Scope(value = "prototype")
    public SmallTaskImpl getSmallTaskImpl() {
        return new SmallTaskImpl();
    }

    @Bean("SmallTaskSupplier")
    public Supplier<SmallTaskImpl> smallTaskSupplier() {
        return this::getSmallTaskImpl;
    }


    @Bean("BigTaskSupplier")
    public Supplier<BigTaskImpl> bigTaskSupplier() {
        return this::getBigTaskImpl;
    }
}
