package pw.spn.pushsender.mock;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import pw.spn.pushsender.entity.AuthToken;
import pw.spn.pushsender.entity.DevicePlatform;
import pw.spn.pushsender.entity.PushTask;
import pw.spn.pushsender.entity.User;
import pw.spn.pushsender.repository.AuthTokenRepository;
import pw.spn.pushsender.repository.DevicePlatformRepository;
import pw.spn.pushsender.repository.PushTaskRepository;
import pw.spn.pushsender.repository.UserRepository;

@Component
@Lazy(false)
public class MockDataGenerator {
    private static final Log LOG = LogFactory.getLog(MockDataGenerator.class);

    private static final int USERS_COUNT = 100_000;
    private static final int PUSH_TASK_TIME = 9; // AM

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DevicePlatformRepository devicePlatformRepository;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private PushTaskRepository pushTaskRepository;

    @PostConstruct
    public void generateData() {
        LOG.info("Creating mock data");
        clearDatabase();
        Set<User> users = createUsers();
        Set<DevicePlatform> platforms = createPlatforms();
        createAuthTokens(users, platforms);
        createPushTasks(platforms);
        LOG.info("Mock data created");
    }

    private Set<User> createUsers() {
        LOG.info("Creating users");
        Set<User> users = new HashSet<>();
        IntStream.range(0, USERS_COUNT).forEach(i -> users.add(new User("User " + i)));
        userRepository.save(users);
        return users;
    }

    private Set<DevicePlatform> createPlatforms() {
        LOG.info("Creating platforms");
        Set<DevicePlatform> platforms = new HashSet<>();

        platforms.add(devicePlatformRepository.save(new DevicePlatform("Win Phone")));
        platforms.add(devicePlatformRepository.save(new DevicePlatform("iOS")));
        platforms.add(devicePlatformRepository.save(new DevicePlatform("Android")));
        platforms.add(devicePlatformRepository.save(new DevicePlatform("Linux")));
        platforms.add(devicePlatformRepository.save(new DevicePlatform("Other")));

        return platforms;
    }

    private void createAuthTokens(Set<User> users, Set<DevicePlatform> platforms) {
        LOG.info("Creating auth tokens");
        Set<AuthToken> tokens = new HashSet<>();
        users.forEach(user -> platforms.forEach(platform -> tokens.add(new AuthToken(user.getId(), UUID.randomUUID()
                .toString(), UUID.randomUUID().toString(), platform.getId()))));
        authTokenRepository.save(tokens);
    }

    private void createPushTasks(Set<DevicePlatform> platforms) {
        LOG.info("Creating push tasks");
        String platformsIDS = platforms.stream().mapToInt(DevicePlatform::getId)
                .collect(StringBuilder::new, (sb, i) -> sb.append(i).append(','), StringBuilder::append).toString();
        IntStream.range(0, 7).forEach(
                i -> pushTaskRepository.save(new PushTask("Hello!", i, PUSH_TASK_TIME, platformsIDS)));
    }

    private void clearDatabase() {
        LOG.info("Clearing database");
        pushTaskRepository.deleteAll();
        authTokenRepository.deleteAll();
        devicePlatformRepository.deleteAll();
        userRepository.deleteAll();
    }
}
