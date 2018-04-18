package io.pivotal.dbreload.repository;

import io.pivotal.dbreload.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
