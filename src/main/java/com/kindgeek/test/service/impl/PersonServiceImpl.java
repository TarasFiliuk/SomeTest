package com.kindgeek.test.service.impl;

import com.kindgeek.test.exeption.TestNotFoundExeption;
import com.kindgeek.test.service.request.PersonRequest;
import com.kindgeek.test.entity.Person;
import com.kindgeek.test.entity.Position;
import com.kindgeek.test.repository.PersonRepository;
import com.kindgeek.test.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public Person createPerson() {
        Person person = new Person();
        person.setPosition(Collections.singletonList(new Position("Junior Java Developer")));
        return personRepository.save(person);
    }

    @Override
    public Person getPersonById(int personId) {
        return personRepository.findById(personId)
                .orElseThrow(()->new TestNotFoundExeption("Person", format("Person with ID %s not found", personId)));
    }

    @Override
    public void deletePerson(int personId) {
        personRepository.deleteById(personId);
    }

    @Override
    public Person updatePerson(int personId, PersonRequest personRequest) {
        Person person = this.getPersonById(personId);
        List<Position> positions = person.getPosition().stream().map(position -> {
            if (position.getId() == personRequest.getPositionId()) {
                position.setNamePosition(personRequest.getPositionName());
            }
            return position;
        }).collect(Collectors.toList());
        person.setPosition(positions);
        return personRepository.save(person);
    }


}
