package com.smartjob.usersbackend.core.port.out.persistence;

import com.smartjob.usersbackend.core.model.User;
import java.util.Optional;

public interface UsersRepository {
    Optional<User> addUser(User user);
}
