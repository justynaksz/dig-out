package com.digout.webapp.service.validator;

import com.digout.webapp.repository.role.Role;
import com.digout.webapp.service.DTO.AppUserDTO;
import com.digout.webapp.service.DTO.DTO;
import com.digout.webapp.service.exeption.EmptyFieldException;
import com.digout.webapp.service.exeption.InvalidInputException;
import com.digout.webapp.service.exeption.PatternBreakException;
import org.springframework.stereotype.Component;

@Component
public class AppUserValidator extends Validator {

    @Override
    public void isValid(DTO dto) throws InvalidInputException, EmptyFieldException {
        var user = (AppUserDTO) dto;
        validateUser(user);
    }

    private void validateUser(AppUserDTO user) throws InvalidInputException, EmptyFieldException {
        validateNickname(user.getNickname());
        validatePassword(user.getPassword());
        validateEmail(user.getEmail());
        validateRole(user.getRole());
        validatePhoto(user.getPhoto());
    }

    private void validateNickname(String nickname) throws InvalidInputException, EmptyFieldException {
        var nicknameField = "NICKNAME";
        if (nickname == null || nickname.isEmpty()) {
            throw new EmptyFieldException(nicknameField);
        }
        validateLength(nicknameField, 50, nickname);
        if (!nickname.matches("[[a-zA-Z0-9._%-]+]{5,}")) {
            throw new PatternBreakException(nicknameField, nickname);
        }
    }

    private void validatePassword(String password) throws InvalidInputException, EmptyFieldException {
        var passwordField = "PASSWORD";
        if (password == null || password.isEmpty()) {
            throw new EmptyFieldException(passwordField);
        }
        validateLength(passwordField, 70, password);
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
            throw new PatternBreakException(passwordField, password);
        }
    }

    private void validateEmail(String email) throws InvalidInputException, EmptyFieldException {
        var emailField = "E-MAIL";
        if (email == null || email.isEmpty()) {
            throw new EmptyFieldException(emailField);
        }
        validateLength(emailField, 70, email);
        if (!email.matches("^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) {
            throw new PatternBreakException(emailField, email);
        }
    }

    private void validateRole(String role) throws InvalidInputException, EmptyFieldException {
        var roleField = "ROLE";
        if (role == null || role.isEmpty()) {
            throw new EmptyFieldException(roleField);
        }
        if (!role.equals(Role.ADMIN) && !role.equals(Role.USER)) {
            throw new InvalidInputException(roleField, role, "USER or ADMIN required.");
        }
    }
}
