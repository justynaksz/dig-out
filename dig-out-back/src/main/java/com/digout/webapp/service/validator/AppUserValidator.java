package com.digout.webapp.service.validator;

import com.digout.webapp.repository.role.Role;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import org.springframework.stereotype.Component;

@Component
public class AppUserValidator extends Validator {

    @Override
    public void isValid(DTO dto) throws InvalidInputException, EmptyFieldException {
        var user = (AppUserDTO) dto;
        validateUser(user);
    }

    // Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:
    public void validatePassword(String password) throws InvalidInputException, EmptyFieldException {
        var passwordField = "PASSWORD";
        validateNotEmptyOrNull(password, passwordField);
        validateLength(passwordField, 70, password);
        var regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        validatePattern(password, passwordField, regex);
    }

    private void validateUser(AppUserDTO user) throws InvalidInputException, EmptyFieldException {
        validateNickname(user.getNickname());
        validateEmail(user.getEmail());
        validateRole(user.getRole());
        validatePhoto(user.getPhoto());
    }

    private void validateNickname(String nickname) throws InvalidInputException, EmptyFieldException {
        var nicknameField = "NICKNAME";
        validateNotEmptyOrNull(nickname, nicknameField);
        validateLength(nicknameField, 50, nickname);
        validatePattern(nickname, nicknameField, "[[a-zA-Z0-9._%-]+]{5,}");
    }


    private void validateEmail(String email) throws InvalidInputException, EmptyFieldException {
        var emailField = "E-MAIL";
        validateNotEmptyOrNull(email, emailField);
        validateLength(emailField, 70, email);
        var regex = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
        validatePattern(email, emailField, regex);
    }

    private void validateRole(String role) throws InvalidInputException, EmptyFieldException {
        var roleField = "ROLE";
        validateNotEmptyOrNull(role, roleField);
        if (!role.equals(Role.ADMIN) && !role.equals(Role.USER)) {
            throw new InvalidInputException(roleField, role, "USER or ADMIN required.");
        }
    }
}
