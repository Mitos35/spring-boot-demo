package my.code.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import my.code.conditional.FirstConditional;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

//@Profile("dev")
@Conditional(FirstConditional.class)
@Configuration
@Slf4j
public class ApplicationConfiguration {

    @PostConstruct
    public void init() {
        log.warn("app is loaded!!!");
    }
}
