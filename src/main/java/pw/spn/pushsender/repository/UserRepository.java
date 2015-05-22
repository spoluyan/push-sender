package pw.spn.pushsender.repository;

import org.springframework.data.repository.CrudRepository;

import pw.spn.pushsender.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
