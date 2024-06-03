package org.tarik.casestudy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tarik.casestudy.entities.concretes.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
