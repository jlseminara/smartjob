package com.smartjob.usersbackend.adapter.usecases;

import com.smartjob.usersbackend.core.model.User;
import com.smartjob.usersbackend.core.port.out.persistence.UsersRepository;
import com.smartjob.usersbackend.core.usecases.AddUserUsecase;
import com.smartjob.usersbackend.exceptions.DataCreationException;
import com.smartjob.usersbackend.exceptions.DataFormatErrorException;
import com.smartjob.usersbackend.exceptions.DataIntegrityException;
import com.smartjob.usersbackend.utilities.passwordencoder.PasswordEncoder;
import com.smartjob.usersbackend.utilities.validators.EmailValidator;
import com.smartjob.usersbackend.utilities.validators.PasswordFormatValidator;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.Optional;
import com.smartjob.usersbackend.utilities.token.JwtGenerator;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class AddUserUsecaseImpl implements AddUserUsecase {

    private final int RANDOM_SALT_SIZE = 30;
    private final UsersRepository usersRepository;
    private final PasswordFormatValidator passwordFormatValidator;

    @Autowired
    public AddUserUsecaseImpl(UsersRepository usersRepository, PasswordFormatValidator passwordFormatValidator) {
        this.usersRepository = usersRepository;
        this.passwordFormatValidator = passwordFormatValidator;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, timeout = 5)
    public User addUser(User user) {
        log.info("addUser, name={}, email={}: ", user.getName(), user.getEmail());

        if(!EmailValidator.isMailValid(user.getEmail()))
            throw new DataFormatErrorException("Invalid email format");

        if(!passwordFormatValidator.isPasswordValidFormat(user.getPassword()))
            throw new DataFormatErrorException("Invalid password format. " + passwordFormatValidator.getFormatExplanation());

        user.setPasswordRandomSalt(PasswordEncoder.generateRandomSalt(RANDOM_SALT_SIZE));
        user.setPassword(PasswordEncoder.bcryptPasswordEncryption(user.getPassword(), user.getPasswordRandomSalt()));
        user.setToken(JwtGenerator.generateToken(user.getEmail()));

        Optional<User> retrievedUser;
        try {
            retrievedUser = usersRepository.addUser(user);
        } catch(ConstraintViolationException ex) {
            log.error("addUser. ConstraintViolationException while  creating user in database: name={}, email={}, error={}",
                    user.getName(), user.getEmail(), ex.getMessage());
            String errorMsg = ex.getConstraintViolations().isEmpty() ?
                            "unspecified violation" : ex.getConstraintViolations().stream().findFirst().get().getMessage();
            throw new DataIntegrityException("Data constraint violation: " + errorMsg);
        } catch(DataIntegrityViolationException ex) {
            log.error("addUser. DataIntegrityViolationException while  creating user in database: name={}, email={}",
                    user.getName(), user.getEmail());
            throw new DataIntegrityException("Data integrity violationException, email already exists in database.");
        }
        catch(RuntimeException ex) {
            /*
             * Por simplicidad evito catchear exceptiones especificas como PersistenceException
             */
            log.error("addUser. Error creating user in database: name={}, email={}, errorDetails={}",
                    user.getName(), user.getEmail(), ex.getMessage());
            throw new DataCreationException("Error accessing the data repository");
        }

        return retrievedUser.orElseThrow(() ->
                new DataCreationException("Error creating user in database")
        );
    }

}
