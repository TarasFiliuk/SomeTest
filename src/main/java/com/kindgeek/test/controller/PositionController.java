package com.kindgeek.test.controller;

import com.kindgeek.test.entity.Position;
import com.kindgeek.test.error.Error;
import com.kindgeek.test.service.PositionService;
import com.kindgeek.test.service.request.PositionRequest;
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
@RequestMapping("/position")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PositionController {
    private final PositionService positionService;


    @ApiOperation("create department")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Create department"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PostMapping("/create")
    public ResponseEntity createDepartment() {
        Position position = positionService.createPosition();
        return ok().body(position);
    }

    @ApiOperation("get person")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Get person"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/get/{positionId}")
    public ResponseEntity getPerson(@PathVariable int positionId) {
        Position position = positionService.getPosition(positionId);
        if (nonNull(position)) {
            return ok().body(position);
        } else
            return badRequest().body(newErrorsList(new Error("Person", format("Person with ID %s not found", positionId))));
    }

    @ApiOperation("delete position")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Position successfully deleted"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @DeleteMapping("/delete/{positionId}")
    public ResponseEntity deletePerson(@PathVariable int positionId) {
        positionService.deletePosition(positionId);
        return ok().build();
    }

    @ApiOperation("update position")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Position successfully updated"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @PutMapping("/update")
    public ResponseEntity updatePerson(@RequestBody PositionRequest positionRequest) {
        int positionId = positionRequest.getPositionId();
        Position position = positionService.updatePosition(positionId, positionRequest);
        if (nonNull(position)) {
            return ok().body(position);
        } else
            return badRequest().body(newErrorsList(new Error("Position", format("Position with ID %s not found", positionId))));
    }
}
