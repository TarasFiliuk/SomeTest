package com.kindgeek.test.service.impl;

import com.kindgeek.test.exeption.TestNotFoundExeption;
import com.kindgeek.test.entity.Person;
import com.kindgeek.test.entity.Position;
import com.kindgeek.test.entity.Project;
import com.kindgeek.test.repository.ProjectRepository;
import com.kindgeek.test.service.PersonService;
import com.kindgeek.test.service.ProjectService;
import com.kindgeek.test.service.request.ProjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final PersonService personService;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project createProject() {
        Project project = new Project();
        Person person = new Person();
        person.setPosition(Collections.singletonList(new Position("Junior Java Developer")));
        project.setPerson(Collections.singletonList(person));
        project.setProjectName("FaceBook");
        log.info("Project successfully created.");
        return this.save(project);
    }

    @Override
    public Project getProject(int projectId) {
        return projectRepository.findById(projectId)
                .orElseThrow(() -> new TestNotFoundExeption("Project", format("Project with ID %s not found", projectId)));
    }
    @Override
    public void deleteProject(int projectId){
        projectRepository.deleteById(projectId);
        log.info("Position with ID {} deleted",projectId);
    }

    @Override
    public Project updateProject(int projectId, ProjectRequest projectRequest){
        Project project = this.getProject(projectId);
        Person person = personService.getPersonById(projectRequest.getPersonId());
        List<Person> people = new ArrayList<>();
        people.add(person);
        project.setPerson(people);
        project.setProjectName(projectRequest.getProjectName());
        log.info("Project with ID {},Person ID {} updated",projectId,projectRequest.getPersonId());
        return this.save(project);
    }
}
