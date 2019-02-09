package com.kindgeek.test.service;

import com.kindgeek.test.entity.Department;
import com.kindgeek.test.service.request.DepartmentRequest;

public interface DepartamentService {
    Department createDepartment();
    Department getDepartment(int departmentId);
    void deleteDepartment(int departmentId);
    Department updateDepartment(int departmentId, DepartmentRequest departmentRequest);
}
