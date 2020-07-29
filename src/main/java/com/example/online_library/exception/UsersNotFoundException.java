package com.example.online_library.exception;

import org.springframework.core.NestedRuntimeException;

public class UsersNotFoundException extends NestedRuntimeException {
    public UsersNotFoundException(String s) {
        super(s);
    }
}
