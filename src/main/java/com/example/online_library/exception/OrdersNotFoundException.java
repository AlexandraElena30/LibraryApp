package com.example.online_library.exception;

import org.springframework.core.NestedRuntimeException;

public class OrdersNotFoundException extends NestedRuntimeException {
    public OrdersNotFoundException(String s) {
        super(s);
    }
}
