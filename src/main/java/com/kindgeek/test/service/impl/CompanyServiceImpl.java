package com.kindgeek.test.service.impl;

import com.kindgeek.test.exeption.TestNotFoundExeption;
import com.kindgeek.test.entity.*;
import com.kindgeek.test.repository.CompanyRepository;
import com.kindgeek.test.service.CompanyService;
import com.kindgeek.test.service.PersonService;
import com.kindgeek.test.service.ProjectService;
import com.kindgeek.test.service.request.CompanyRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ProjectService projectService;
    private final PersonService personService;


    @Override
    public Company createCompany() {

        Company company = new Company();
        Department department = new Department();
        Project project = new Project();
        Person person = new Person();
        Position position = new Position();

        List<Project> projects = new ArrayList<>();

        department.setDepartmentName("Java Department");
        department.setPosition(Collections.singletonList(position));
        project.setProjectName("FaceBook");
        position.setNamePosition("Junior Java Developer");
        person.setPosition(Collections.singletonList(position));
        project.setPerson(Collections.singletonList(person));
        projects.add(project);

        company.setDepartment(Collections.singletonList(department));
        company.setPosition(Collections.singletonList(position));
        company.setProject(projects);
        log.info("Company successfully created.");
        return companyRepository.save(company);
    }

    @Override
    public Company getCompany(int companyId) {
        return companyRepository.findById(companyId).orElseThrow(TestNotFoundExeption::new);
    }

    @Override
    public Company addPersonToCompany(CompanyRequest companyRequest) {
        Company company = companyRepository.findById(companyRequest.getCompanyId()).orElseThrow(TestNotFoundExeption::new);
        List<Project> collect = company.getProject().stream().map(p -> {
            int projectId = companyRequest.getProjectId();
            Project project = projectService.getProject(projectId);
            if (p.getId() == projectId) {
                int personId = companyRequest.getPersonId();
                Person personById = personService.getPersonById(personId);
                List<Person> people = new ArrayList<>();
                people.add(personById);
                project.setPerson(people);
                log.info("Person with ID {} added to company", personId);
            }
            return p;
        }).collect(Collectors.toList());
        company.setProject(collect);
        return company;
    }
}
