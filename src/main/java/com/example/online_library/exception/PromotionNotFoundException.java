package com.example.online_library.exception;

import org.springframework.core.NestedRuntimeException;

public class PromotionNotFoundException extends NestedRuntimeException {
    public PromotionNotFoundException(String s) {
        super(s);
    }
}
