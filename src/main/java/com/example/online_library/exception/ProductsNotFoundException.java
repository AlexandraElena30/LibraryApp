package com.example.online_library.exception;

import org.springframework.core.NestedRuntimeException;

public class ProductsNotFoundException extends NestedRuntimeException {
    public ProductsNotFoundException(String s) {
        super(s);
    }
}
