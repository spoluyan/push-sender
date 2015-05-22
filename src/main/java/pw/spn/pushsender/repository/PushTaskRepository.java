package pw.spn.pushsender.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import pw.spn.pushsender.entity.PushTask;

public interface PushTaskRepository extends CrudRepository<PushTask, Integer> {
    Set<PushTask> findByIsScheduledIsFalse();
}
