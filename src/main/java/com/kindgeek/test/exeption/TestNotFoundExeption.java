package com.kindgeek.test.exeption;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestNotFoundExeption extends RuntimeException {
    private String field;

    public TestNotFoundExeption() {
        super();
    }

    public TestNotFoundExeption(String field, String message) {
        super(message);
        this.field = field;
    }
}
