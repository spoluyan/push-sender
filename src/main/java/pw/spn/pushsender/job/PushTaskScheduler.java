package pw.spn.pushsender.job;

import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import pw.spn.pushsender.entity.PushTask;
import pw.spn.pushsender.repository.PushTaskRepository;

@Component
public class PushTaskScheduler implements ApplicationContextAware {
    private static final Log LOG = LogFactory.getLog(PushTaskScheduler.class);

    private ApplicationContext applicationContext;

    @Autowired
    private PushTaskRepository pushTaskRepository;

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    @PostConstruct
    private void setAllTasksUnscheduled() {
        LOG.info("Unschedule all tasks.");
        pushTaskRepository.findAll().forEach(task -> {
            task.setScheduled(false);
            pushTaskRepository.save(task);
        });
    }

    @Scheduled(fixedDelay = 5000)
    public void checkPushTasks() {
        LOG.info("Check unscheduled tasks");
        Set<PushTask> unscheduledTasks = pushTaskRepository.findByIsScheduledIsFalse();
        LOG.info("Found " + unscheduledTasks.size() + " unscheduled tasks");
        unscheduledTasks.forEach(task -> shceduleTask(task));
    }

    private void shceduleTask(PushTask task) {
        LOG.info("Scheduling " + task.toString());

        PushJob job = applicationContext.getBean(PushJob.class, task.getText(), task.getDevicePlatforms());
        CronTrigger trigger = buildTrigger(task);
        taskScheduler.schedule(job, trigger);

        task.setScheduled(true);
        pushTaskRepository.save(task);

    }

    private CronTrigger buildTrigger(PushTask task) {
        return new CronTrigger("0 0 " + String.valueOf(task.getTime()) + " ? * "
                + String.valueOf(task.getDayOfWeek() + 1));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
