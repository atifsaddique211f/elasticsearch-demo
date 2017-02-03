package com.aurora.person;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by atif on 2/3/17.
 */
@RestController
@RequestMapping
public class PersonController
{

    @Inject
    private PersonRepository personRepository;

    @RequestMapping(value = "/api/v1/persons",
            method = RequestMethod.GET)
    public List<Person> getPersons(@RequestParam String name,
                                   Pageable pageable)
    {
        return personRepository.getAllByName(name, pageable);
    }

}
