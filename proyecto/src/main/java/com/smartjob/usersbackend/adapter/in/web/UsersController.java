package com.smartjob.usersbackend.adapter.in.web;

import com.smartjob.usersbackend.adapter.in.web.interfaces.UsersApi;
import com.smartjob.usersbackend.adapter.in.web.models.AddUserPayload;
import com.smartjob.usersbackend.adapter.in.web.models.CreatedUserDTO;
import com.smartjob.usersbackend.core.usecases.AddUserUsecase;
import com.smartjob.usersbackend.utilities.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@Validated
@RestController
public class UsersController implements UsersApi {

    private final AddUserUsecase addUserUsecase;

    @Autowired
    public UsersController(AddUserUsecase addUserUsecase) {
        this.addUserUsecase = addUserUsecase;
    }

    @Override
    public ResponseEntity<CreatedUserDTO> addUser(AddUserPayload addUserPayload) {
        log.info("addUser, name={}, email={}: ", addUserPayload.getName(), addUserPayload.getEmail());

        var userModel = UserMapper.INSTANCE.fromAddUserPayloadToModel(addUserPayload);
        var result = UserMapper.INSTANCE.fromModelToCreatedUserDTO(addUserUsecase.addUser(userModel));

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

}
