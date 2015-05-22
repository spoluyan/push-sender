package pw.spn.pushsender.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "push_tasks")
public class PushTask implements Serializable {
    private static final long serialVersionUID = 7216041517399772200L;

    private static final String[] DAYS_OF_WEEK = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
            "Sunday" };

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "push_tasks_generator")
    @SequenceGenerator(name = "push_tasks_generator", sequenceName = "push_tasks_seq")
    private Integer id;

    @Column
    private String text;

    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column
    private Integer time;

    @Column(name = "device_platforms")
    private String devicePlatforms;

    @Column(name = "is_scheduled")
    private boolean isScheduled;

    public PushTask() {
    }

    public PushTask(String text, Integer dayOfWeek, Integer time, String devicePlatforms) {
        this.text = text;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.devicePlatforms = devicePlatforms;
    }

    @Override
    public String toString() {
        return new StringBuilder("Task ID: ").append(id).append(". Scheduled on ").append(DAYS_OF_WEEK[dayOfWeek])
                .append(" at ").append(getTimeString()).toString();
    }

    private String getTimeString() {
        if (time == 0) {
            return "12 AM";
        }
        if (time < 12) {
            return time + " AM";
        }
        if (time == 12) {
            return "12 PM";
        }
        return (time - 12) + " PM";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getDevicePlatforms() {
        return devicePlatforms;
    }

    public void setDevicePlatforms(String devicePlatforms) {
        this.devicePlatforms = devicePlatforms;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    public void setScheduled(boolean isScheduled) {
        this.isScheduled = isScheduled;
    }
}
