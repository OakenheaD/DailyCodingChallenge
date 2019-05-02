package com.oakenhead.dcc;

import com.oakenhead.dcc.challenge.CodingChallenge;
import com.oakenhead.dcc.challenge.QuadValue;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.month04.day26.AnyTwoNumbersAddUpToK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.Collection;

@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication()
public class DailyCodingChallengeApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(DailyCodingChallengeApp.class);
    private static ConfigurableApplicationContext context;

    public static void main(final String[] args) {

        LOGGER.info("   ```---===≡using daily-coding-challenge alpha≡===---```   ");
        context = SpringApplication.run(DailyCodingChallengeApp.class, args);
        LOGGER.info("   ```---===started daily-coding-challenge alpha===---```   ");

        //post construct static init;
        context.getBean(DailyCodingChallengeApp.class).runChallenges();

    }

    private void runChallenges() {

        runChallenges(context.getBeanFactory().getBeansOfType(CodingChallenge.class).values());

    }

    private void runChallenges(final Collection<CodingChallenge> challenges) {

        challenges.stream().forEach(this::runChallenge);

    }

    private void runChallenge(final CodingChallenge challenge) {

        final QuadValue<Long, Long, Integer, Boolean> result = challenge.doRunTestsAndCheckIfPass();

        final String challengeSuccess = result.right ? "success" : "fail";
        final long challengeBegin = result.left;
        final long challengeEnd = result.middleLeft;

        final Long challengeDuration = challengeEnd - challengeBegin;
        final String perTestCase = Long.toUnsignedString(challengeDuration / (long) result.middleRight);

        LOGGER.info(String.format("challenge %s of \"%s\" is %s in %s ns", challenge.dateString(), challenge.shortName() , challengeSuccess, perTestCase));

    }
}
