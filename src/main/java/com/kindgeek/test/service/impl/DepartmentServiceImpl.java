package com.kindgeek.test.service.impl;

import com.kindgeek.test.exeption.TestNotFoundExeption;
import com.kindgeek.test.entity.Department;
import com.kindgeek.test.entity.Position;
import com.kindgeek.test.repository.DepartmentRepository;
import com.kindgeek.test.service.DepartamentService;
import com.kindgeek.test.service.request.DepartmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class DepartmentServiceImpl implements DepartamentService {

    private final DepartmentRepository departmentRepository;

    private Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department createDepartment() {
        Department department = new Department();
        department.setDepartmentName("Java Department");
        department.setPosition(Collections.singletonList(new Position("Junior Java Developer")));
        return this.save(department);
    }

    @Override
    public Department getDepartment(int departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new TestNotFoundExeption("Department", format("Department with ID %s not found", departmentId)));
    }

    @Override
    public void deleteDepartment(int departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(int departmentId, DepartmentRequest departmentRequest) {
        Department department = this.getDepartment(departmentId);
       ofNullable(departmentRequest.getDepartmentName()).ifPresent(department::setDepartmentName);

        List<Position> positions = department.getPosition().stream().map(position -> {
            if (position.getId() == departmentRequest.getPositionId()) {
                position.setNamePosition(departmentRequest.getPositionName());
            }
            return position;
        }).collect(Collectors.toList());
        department.setPosition(positions);
        return this.save(department);
    }
}
