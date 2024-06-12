package org.tarik.casestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tarik.casestudy.entities.concretes.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
