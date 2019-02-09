package com.kindgeek.test.service;

import com.kindgeek.test.service.request.PersonRequest;
import com.kindgeek.test.entity.Person;

public interface PersonService {
    Person createPerson();
    Person getPersonById(int personId);
    void deletePerson(int personId);
    Person updatePerson(int personId, PersonRequest personRequest);

}
