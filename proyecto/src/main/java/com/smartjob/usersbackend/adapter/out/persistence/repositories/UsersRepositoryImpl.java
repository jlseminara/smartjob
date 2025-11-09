package com.smartjob.usersbackend.adapter.out.persistence.repositories;

import com.smartjob.usersbackend.core.model.User;
import com.smartjob.usersbackend.core.port.out.persistence.UsersRepository;
import com.smartjob.usersbackend.utilities.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Optional;



@Slf4j
@Component
public class UsersRepositoryImpl implements UsersRepository {

    private final UsersRepositoryJpaInterface usersRepositoryJpa;

    @Autowired
    public UsersRepositoryImpl(UsersRepositoryJpaInterface usersRepositoryJpa) {
        this.usersRepositoryJpa = usersRepositoryJpa;
    }

    @Override
    public Optional<User> addUser(User user) {
        log.info("addUser, name={}, email={}: ", user.getName(), user.getEmail());

        var userEntity = UserMapper.INSTANCE.fromModelToEntity(user);
        userEntity.getTelephonesList().forEach(tel->tel.setUser(userEntity));
        userEntity.setIsActive(true);
        var dbResult = usersRepositoryJpa.saveAndFlush(userEntity);

        return Optional.of(UserMapper.INSTANCE.fromEntityToModel(dbResult));
    }

}


