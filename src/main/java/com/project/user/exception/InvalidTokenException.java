package com.project.user.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvalidTokenException extends Exception {

    public InvalidTokenException(String msg) {
        super(msg);
    }
}
