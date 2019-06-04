package com.oakenhead.dcc;

import com.oakenhead.dcc.challenge.CodingChallenge;
import com.oakenhead.dcc.challenge.QuadValue;
import com.oakenhead.dcc.challenge.TripleValue;
import com.oakenhead.dcc.challenge.beans.FrugalRollingQueue;
import com.oakenhead.dcc.challenge.beans.RollingQueue;
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
import java.util.stream.IntStream;

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

        //runChallenges(context.getBeanFactory().getBeansOfType(CodingChallenge.class).values());
        runLast10Challenges(context.getBeanFactory().getBeansOfType(CodingChallenge.class).values());

    }

    private void runChallenges(final Collection<CodingChallenge> challenges) {

        challenges.stream().forEach(this::runChallenge);

    }

    private void runLast10Challenges(final Collection<CodingChallenge> challenges) {

        final int challengesToRun = 10;

        final FrugalRollingQueue<CodingChallenge> challengeRollingQueue = new FrugalRollingQueue<>();
        challenges.stream().forEach(challengeRollingQueue::push);
        IntStream.range(0, challengesToRun)
                .map(i -> challengesToRun - i - 1)
                .forEach(i -> runChallenge(challengeRollingQueue.get(i)));

    }

    private void runChallenge(final CodingChallenge challenge) {

        final QuadValue<Long, Long, Integer, Boolean> result = challenge.doRunTestsAndCheckIfPass();

        final String challengeSuccess = result.right ? "success" : "fail";
        final long challengeBegin = result.left;
        final long challengeEnd = result.middleLeft;

        final Long challengeDuration = challengeEnd - challengeBegin;
        final String perTestCase = Long.toUnsignedString(challengeDuration / (long) result.middleRight);
        final String perTestCaseInMs = Long.toUnsignedString((challengeDuration / ((long) result.middleRight ) / 1_000_000));

        LOGGER.info(String.format("challenge %s of \"%s\" is %s in %s ms (%s ns) per case",
                    challenge.dateString(), challenge.shortName() , challengeSuccess, perTestCaseInMs, perTestCase));

    }
}
