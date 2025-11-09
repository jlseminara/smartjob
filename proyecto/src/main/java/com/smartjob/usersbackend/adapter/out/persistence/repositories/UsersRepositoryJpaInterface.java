package com.smartjob.usersbackend.adapter.out.persistence.repositories;

import com.smartjob.usersbackend.adapter.out.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersRepositoryJpaInterface extends JpaRepository<UserEntity, Long> {
}