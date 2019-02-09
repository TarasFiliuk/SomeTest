package com.kindgeek.test.controller;

import com.kindgeek.test.error.Error;
import com.kindgeek.test.service.request.PersonRequest;
import com.kindgeek.test.entity.Person;
import com.kindgeek.test.service.PersonService;
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
@RequestMapping("/person")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {
    private final PersonService personService;


    @PostMapping("/create")
    public Person createPerson() {
        return personService.createPerson();
    }


    @ApiOperation("get person")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Get person"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @GetMapping("/getPerson/{personId}")
    public ResponseEntity getPerson(@PathVariable int personId) {
        Person person = personService.getPersonById(personId);
        if (nonNull(person)) {
            return ok().body(person);
        } else
            return badRequest().body(newErrorsList(new Error("Person", format("Person with ID %s not found", personId))));
    }

    @ApiOperation("delete  person")
    @ApiResponses(value = {
            @ApiResponse(code = SC_OK, message = "Person successfully deleted"),
            @ApiResponse(code = SC_UNAUTHORIZED, message = OPERATION_NOT_ALLOWED_MESSAGE, response = Errors.class),
            @ApiResponse(code = SC_INTERNAL_SERVER_ERROR, message = INTERNAL_SERVER_ERROR_MESSAGE)})
    @DeleteMapping("/deletePerson/{personId}")
    public ResponseEntity deletePerson(@PathVariable int personId) {
        personService.deletePerson(personId);
        return ok().build();
    }

    @PutMapping("/update/{personId}")
    public ResponseEntity updatePerson(@PathVariable int personId,
                                       @RequestBody PersonRequest personRequest) {
        Person person = personService.updatePerson(personId, personRequest);
        if (nonNull(person)) {
            return ok().body(person);
        } else
            return badRequest().body(newErrorsList(new Error("Person", format("Person with ID %s not found", personId))));
    }
}
