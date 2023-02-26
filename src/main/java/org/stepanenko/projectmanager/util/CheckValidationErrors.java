package org.stepanenko.projectmanager.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.stepanenko.projectmanager.exceptions.EntityNotSavedException;

import java.util.List;

public final class CheckValidationErrors {

    private CheckValidationErrors() {

    }

    public static void check(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new EntityNotSavedException(errorMsg.toString());
        }
    }
}
