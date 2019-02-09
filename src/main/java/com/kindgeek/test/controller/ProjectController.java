package com.kindgeek.test.controller;

import com.kindgeek.test.entity.Project;
import com.kindgeek.test.error.Error;
import com.kindgeek.test.service.ProjectService;
import com.kindgeek.test.service.request.ProjectRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import static com.kindgeek.test.utils.Constants.INTERNAL_SERVER_ERROR_MESSAGE;
import static com.kindgeek.test.utils.Constants.OPERATION_NOT_ALLOWED_MESSAGE;
import static com.kindgeek.test.utils.ErrorUtils.newErrorsList;
import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static javax.servlet.http.HttpServletResponse.*;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectController {
    private final ProjectService projectService;

    @ApiOperation("create project")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Create project"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PostMapping("/create")
    public ResponseEntity createDepartment() {
        Project project = projectService.createProject();
        return ok().body(project);
    }

    @ApiOperation("get project")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Get project"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/get/{projectId}")
    public ResponseEntity getPerson(@PathVariable int projectId) {
        Project project = projectService.getProject(projectId);
        if (nonNull(project)) {
            return ok().body(project);
        } else
            return badRequest().body(newErrorsList(new Error("Person", format("Person with ID %s not found", projectId))));
    }

    @ApiOperation("delete project")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Project successfully deleted"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity deletePerson(@PathVariable int projectId) {
        projectService.deleteProject(projectId);
        return ok().build();
    }

    @ApiOperation("update project")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Project successfully updated"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PutMapping("/update/{projectId}")
    public ResponseEntity updatePerson(@PathVariable int projectId,
                                       @RequestBody ProjectRequest projectRequest) {
        Project project = projectService.updateProject(projectId, projectRequest);
        if (nonNull(project)) {
            return ok().body(project);
        } else
            return badRequest().body(newErrorsList(new Error("Project", format("Project with ID %s not found", projectId))));
    }

}
