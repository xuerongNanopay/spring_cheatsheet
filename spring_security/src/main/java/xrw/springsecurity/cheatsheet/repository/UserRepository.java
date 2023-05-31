package xrw.springsecurity.cheatsheet.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import xrw.springsecurity.cheatsheet.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
  User findByUsername(String username);
}
