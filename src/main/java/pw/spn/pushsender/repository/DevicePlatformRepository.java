package pw.spn.pushsender.repository;

import org.springframework.data.repository.CrudRepository;

import pw.spn.pushsender.entity.DevicePlatform;

public interface DevicePlatformRepository extends CrudRepository<DevicePlatform, Integer> {
}
