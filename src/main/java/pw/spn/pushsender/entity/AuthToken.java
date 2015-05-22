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
@Table(name = "auth_tokens")
public class AuthToken implements Serializable {
    private static final long serialVersionUID = -2608017160832059790L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_tokens_generator")
    @SequenceGenerator(name = "auth_tokens_generator", sequenceName = "auth_tokens_seq")
    private Long id;

    @Column
    private Long userid;

    @Column
    private String deviceid;

    @Column
    private String pushtoken;

    @Column(name = "device_platform")
    private Integer devicePlatformId;

    public AuthToken() {
    }

    public AuthToken(Long userid, String deviceid, String pushtoken, Integer devicePlatformId) {
        this.userid = userid;
        this.deviceid = deviceid;
        this.pushtoken = pushtoken;
        this.devicePlatformId = devicePlatformId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getPushtoken() {
        return pushtoken;
    }

    public void setPushtoken(String pushtoken) {
        this.pushtoken = pushtoken;
    }

    public Integer getDevicePlatformId() {
        return devicePlatformId;
    }

    public void setDevicePlatformId(Integer devicePlatformId) {
        this.devicePlatformId = devicePlatformId;
    }
}
