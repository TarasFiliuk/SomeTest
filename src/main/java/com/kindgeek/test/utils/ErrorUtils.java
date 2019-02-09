package com.kindgeek.test.utils;


import com.kindgeek.test.error.Error;
import com.kindgeek.test.error.Errors;

import static java.util.Arrays.asList;

public class ErrorUtils {
    public static Error newError(String field, String message) {
        return new Error(field, message);
    }

    public static Errors newErrorsList(Error... errors) {
        return new Errors(asList(errors));
    }
}


