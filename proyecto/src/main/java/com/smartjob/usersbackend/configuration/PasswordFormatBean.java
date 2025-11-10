package com.smartjob.usersbackend.configuration;

import com.smartjob.usersbackend.utilities.validators.PasswordFormatValidator;
import com.smartjob.usersbackend.utilities.validators.passfromatstrategies.PasswordLongFormatValidator;
import com.smartjob.usersbackend.utilities.validators.passfromatstrategies.PasswordShortFormatValidator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PasswordFormatBean {

    @Bean
    @ConditionalOnProperty(name = "custom.password_format", havingValue = "1", matchIfMissing = true)
    public PasswordFormatValidator shortFormatValidator() {
        return new PasswordShortFormatValidator();
    }

    @Bean
    @ConditionalOnProperty(name = "custom.password_format", havingValue = "2")
    public PasswordFormatValidator longFormatValidator() {
        return new PasswordLongFormatValidator();
    }

    @Bean
    @ConditionalOnMissingBean(PasswordFormatValidator.class)
    public PasswordFormatValidator defaultFormatValidator() {
        return new PasswordShortFormatValidator();
    }

}
