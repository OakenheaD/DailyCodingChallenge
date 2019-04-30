package com.oakenhead.dcc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication()
public class DailyCodingChallengeApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameSpringApp.class);

    public static void main(final String[] args) {

        LOGGER.info("   ```---===≡using daily-coding-challenge alpha≡===---```   ");
        ConfigurableApplicationContext context = SpringApplication.run(GameSpringApp.class, args);
        LOGGER.info("   ```---===started daily-coding-challenge alpha===---```   ");

        //post construct static init;
        //context.getBean(FooBar.class).doTest();

    }
}
