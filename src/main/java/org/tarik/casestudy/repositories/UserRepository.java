package org.tarik.casestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tarik.casestudy.entities.concretes.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
