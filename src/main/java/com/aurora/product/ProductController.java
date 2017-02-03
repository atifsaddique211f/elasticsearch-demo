package com.aurora.product;

import com.aurora.person.PersonRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by atif on 1/27/17.
 */
@RestController
@RequestMapping
public class ProductController
{
    @Inject
    private ProductRepository productRepository;
    @Inject
    private PersonRepository personRepository;

    @RequestMapping(value = "/api/v1/products",
            method = RequestMethod.GET)
    public Object helloWorld()
    {
        return productRepository.findAll();
    }
}
