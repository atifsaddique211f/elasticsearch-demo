package com.aurora;

import com.aurora.person.PersonRepository;
import com.aurora.search.Person;
import com.aurora.search.PersonSearchRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by atif on 2/3/17.
 */

@Service
public class Bootstrap
{
    @Inject
    private PersonRepository personRepository;
    @Inject
    private PersonSearchRepository personSearchRepository;

    public void init()
    {
        Pageable pageable = new PageRequest(1, 1000);
        List<com.aurora.person.Person> personList = personRepository.findAll(pageable).getContent();

        personList.forEach(p ->
        {
            Person person = new Person();
            person.setId(p.getId());
            person.setFirstName(p.getFirstName());
            person.setLastName(p.getLastName());
            personSearchRepository.save(person);

        });
    }

}
