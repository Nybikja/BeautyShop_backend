package com.company.beatyclub.exception;

public class IncorrectInputException extends IllegalArgumentException {
    private static final long serialVersionUID = 1L;
    private static final String INCORRECT_INPUT_EXCEPTION = "Incorrect data inputted";

    public IncorrectInputException(String message) {
        super(message.isEmpty() ? INCORRECT_INPUT_EXCEPTION : message);
    }
}

