package pw.spn.pushsender.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import pw.spn.pushsender.entity.AuthToken;
import pw.spn.pushsender.repository.AuthTokenRepository;

@Component
@Scope("prototype")
public class PushJob implements Runnable {
    private static final Log LOG = LogFactory.getLog(PushJob.class);

    private String text;
    private List<Integer> devicePlatformIds;

    @Value("${push-size}")
    private int pushSize;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public PushJob(String text, String platforms) {
        this.text = text;
        parsePlatforms(platforms);
    }

    private void parsePlatforms(String platforms) {
        String[] splitted = platforms.split(",");
        devicePlatformIds = new ArrayList<>(splitted.length);
        IntStream.range(0, splitted.length).forEach(i -> devicePlatformIds.add(Integer.valueOf(splitted[i])));
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();

        LOG.info("Running push job.");
        LOG.info("Job start time: " + new Date().toString());
        LOG.info("Message: " + text);
        LOG.info("Device platforms IDs: " + devicePlatformIds);

        long authTokensCount = authTokenRepository.countByDevicePlatformIdIn(devicePlatformIds);

        LOG.info("Found " + authTokensCount + " auth tokens.");

        long pushOperationsCount = authTokensCount / pushSize;
        if (pushOperationsCount * pushSize < authTokensCount) {
            pushOperationsCount++;
        }

        LongStream.range(0, pushOperationsCount).forEach(i -> pushMessage(i));

        long endTime = System.currentTimeMillis();
        LOG.info("Job end time: " + new Date().toString());
        LOG.info("Job execution time: " + (endTime - startTime) / 1000 + " sec");
    }

    private void pushMessage(long operationsCounter) {
        Pageable pageable = new PageRequest((int) operationsCounter, pushSize);
        Page<AuthToken> tokens = authTokenRepository.findByDevicePlatformIdInOrderByIdAsc(devicePlatformIds, pageable);

        tokens.forEach(token -> pushMessage(token));
    }

    private void pushMessage(AuthToken token) {
        LOG.info("Sending message to token " + token.getId());

        // TODO do some other work here
    }

}
