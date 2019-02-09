package com.kindgeek.test.error;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Error {
    @ApiModelProperty("Field name")
    private final String field;
    @ApiModelProperty("error message")
    private final String message;
}
