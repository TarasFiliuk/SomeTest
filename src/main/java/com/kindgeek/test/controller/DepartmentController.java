package com.kindgeek.test.controller;

import com.kindgeek.test.entity.Department;
import com.kindgeek.test.error.Error;
import com.kindgeek.test.service.DepartmentService;
import com.kindgeek.test.service.request.PersonPositionDepartmentRequest;
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
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @ApiOperation("create department")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Create department"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PostMapping("/create")
    public ResponseEntity createDepartment() {
        Department department = departmentService.createDepartment();
        return ok().body(department);
    }

    @ApiOperation("get department")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Get department"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/getDepartment/{departmentId}")
    public ResponseEntity getDepartment(@PathVariable int departmentId) {
        Department department = departmentService.getDepartment(departmentId);
        if (nonNull(department)) {
            return ok().body(department);
        } else
            return badRequest().body(newErrorsList(new Error("Person", format("Person with ID %s not found", departmentId))));
    }

    @ApiOperation("delete department")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Department successfully deleted"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @DeleteMapping("/delete/{departmentId}")
    public ResponseEntity deletePerson(@PathVariable int departmentId) {
        departmentService.deleteDepartment(departmentId);
        return ok().build();
    }

    @ApiOperation("update department")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Department successfully updated"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PutMapping("/update/{departmentId}")
    public ResponseEntity updatePerson(@PathVariable int departmentId,
                                       @RequestBody PersonPositionDepartmentRequest departmentRequest) {
        Department department = departmentService.updateDepartment(departmentId, departmentRequest);
        if (nonNull(department)){
            return ok().body(department);
        }else return  badRequest().body(newErrorsList(new Error("Person",format("Person with ID %s not found", departmentId))));
    }
}
