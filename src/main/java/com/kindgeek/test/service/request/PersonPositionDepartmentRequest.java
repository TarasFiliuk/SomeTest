package com.kindgeek.test.service.request;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonPositionDepartmentRequest {
    private int positionId;
    private String positionName;
    private String departmentName;
}
