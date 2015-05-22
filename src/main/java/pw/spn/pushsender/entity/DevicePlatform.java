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
@Table(name = "device_platforms")
public class DevicePlatform implements Serializable {
    private static final long serialVersionUID = 2777343569534929885L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "device_platforms_generator")
    @SequenceGenerator(name = "device_platforms_generator", sequenceName = "device_platforms_seq")
    private Integer id;

    @Column
    private String name;

    public DevicePlatform() {
    }

    public DevicePlatform(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
