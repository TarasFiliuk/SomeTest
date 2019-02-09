package com.kindgeek.test.service.impl;

import com.kindgeek.test.exeption.TestNotFoundExeption;
import com.kindgeek.test.entity.Department;
import com.kindgeek.test.entity.Position;
import com.kindgeek.test.repository.DepartmentRepository;
import com.kindgeek.test.service.DepartmentService;
import com.kindgeek.test.service.PersonService;
import com.kindgeek.test.service.request.PersonPositionDepartmentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final PersonService personService;

    private Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department createDepartment() {
        Department department = new Department();
        department.setDepartmentName("Java Department");
        department.setPosition(Collections.singletonList(new Position("Junior Java Developer")));
        log.info("Department successfully created.");
        return this.save(department);
    }

    @Override
    public Department getDepartment(int departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(() -> new TestNotFoundExeption("Department", format("Department with ID %s not found", departmentId)));
    }

    @Override
    public void deleteDepartment(int departmentId) {

        departmentRepository.deleteById(departmentId);
        log.info("Deleted department with ID {}" ,departmentId);
    }

    @Override
    public Department updateDepartment(int departmentId, PersonPositionDepartmentRequest departmentRequest) {
        Department department = this.getDepartment(departmentId);
       ofNullable(departmentRequest.getDepartmentName()).ifPresent(department::setDepartmentName);

        List<Position> positions = department.getPosition().stream().map(position -> {
            if (position.getId() == departmentRequest.getPositionId()) {
                position.setNamePosition(departmentRequest.getPositionName());
            }
            return position;
        }).collect(Collectors.toList());
        department.setPosition(positions);
        log.info("Department with ID {} updated",departmentId);
        return this.save(department);
    }
}
