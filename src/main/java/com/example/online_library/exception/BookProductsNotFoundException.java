package com.example.online_library.exception;

import org.springframework.core.NestedRuntimeException;

public class BookProductsNotFoundException extends NestedRuntimeException {
    public BookProductsNotFoundException(String s) {
        super(s);
    }
}
