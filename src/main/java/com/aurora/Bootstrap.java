package com.aurora;

import com.aurora.person.PersonRepository;
import com.aurora.search.*;
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
        Pageable pageable = new PageRequest(1, 5);
        List<com.aurora.person.Person> personList = personRepository.findAll(pageable).getContent();

        personList.forEach(p ->
        {
            if (personSearchRepository.findOne(p.getId()) == null)
            {
                Person person = new Person();
                com.aurora.person.Address ad = p.getAddress();
                Address address = new Address();
                address.setApartmentNumber(ad.getApartmentNumber());
                address.setCity(ad.getCity());
                address.setId(ad.getId());
                address.setPostalCode(ad.getPostalCode());
                address.setStreet(ad.getStreet());
                address.setStreetNumber(ad.getStreetNumber());
                person.setAddress(address);
                Company company = new Company();
                com.aurora.person.Company persistedCompany = p.getCompany();
                company.setId(persistedCompany.getId());
                company.setDomain(persistedCompany.getDomain());
                company.setEmail(persistedCompany.getEmail());
                company.setName(persistedCompany.getName());
                company.setVatIdentificationNumber(persistedCompany.getVatIdentificationNumber());
                person.setCompany(company);
                p.getProducts().forEach(product ->
                {
                    Product product1 = new Product();
                    product1.setId(product.getId());
                    product1.setPrice(product.getPrice());
                    product1.setName(product.getName());
                    person.addToProducts(product1);
                });
                person.setId(p.getId());
                person.setFirstName(p.getFirstName());
                person.setLastName(p.getLastName());
                person.setEmail(p.getEmail());
                person.setAge(p.getAge());
                person.setCompanyEmail(p.getCompanyEmail());
                person.setDateOfBirth(p.getDateOfBirth());
                person.setMiddleName(p.getMiddleName());
                person.setNationalIdentificationNumber(p.getNationalIdentificationNumber());
                person.setPassportNumber(p.getPassportNumber());
                person.setPassword(p.getPassword());
                person.setSex(Person.Sex.valueOf(p.getSex().name()));
                person.setTelephoneNumber(p.getTelephoneNumber());
                person.setUsername(p.getUsername());
                personSearchRepository.save(person);
            }
        });
    }

}
