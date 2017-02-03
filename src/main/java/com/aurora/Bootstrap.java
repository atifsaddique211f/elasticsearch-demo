package com.aurora;

import com.aurora.person.Address;
import com.aurora.person.Company;
import com.aurora.person.Person;
import com.aurora.person.PersonRepository;
import com.aurora.product.Product;
import com.aurora.product.ProductRepository;
import io.codearte.jfairy.Fairy;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by atif on 2/3/17.
 */

@Service
public class Bootstrap
{
    @Inject
    private ProductRepository productRepository;

    @Inject
    private PersonRepository personRepository;

    public void init()
    {
        addProducts();
        addPersons();
    }

    private void addProducts()
    {
        Product product1 = new Product("Lenovo Core i5 Laptop", 50000);
        Product product2 = new Product("Apple Macbook Dual Core", 139000);
        Product product3 = new Product("Apple MacBook Pro with Retina Core i7", 329000);
        Product product4 = new Product("HP Notebook Intel Core i7", 70000);
        productRepository.save(Arrays.asList(product1, product2, product3, product4));
    }

    private void addPersons()
    {
        Fairy fairy;
        io.codearte.jfairy.producer.person.Person person2;
        io.codearte.jfairy.producer.company.Company company2;
        io.codearte.jfairy.producer.person.Address address2;
        Person person1;
        Company company1;
        Address address1;

        List<Product> productList = productRepository.findAll();

        for (int i = 0; i < 100; i++)
        {
            fairy = Fairy.create();
            person2 = fairy.person();
            company2 = fairy.company();
            address2 = person2.getAddress();

            address1 = new Address(address2.getPostalCode(), address2.getCity(), address2.street(), address2.streetNumber(), address2.apartmentNumber());
            company1 = new Company(company2.name(), company2.domain(), company2.email(), company2.vatIdentificationNumber());
            person1 = new Person(person2.firstName(), person2.middleName(), person2.lastName(), address1,
                    person2.email(), person2.username(), person2.password(), Person.Sex.valueOf(person2.sex().toString()),
                    person2.telephoneNumber(), person2.dateOfBirth().toLocalDate(), person2.age(), person2.nationalIdentityCardNumber(),
                    person2.nationalIdentificationNumber(), person2.passportNumber(), company1, person2.companyEmail());

            personRepository.save(person1);

            for (int j = 0; j < (i % 4) + 1; j++)
            {
                person1.addToProducts(productList.get(j));
            }
            personRepository.save(person1);
        }
    }
}
