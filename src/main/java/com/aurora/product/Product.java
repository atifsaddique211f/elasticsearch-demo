package com.aurora.product;

import javax.persistence.*;

/**
 * Created by atif on 1/27/17.
 */
@Entity
@Table(name = "test")
public class Product
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Product()
    {

    }

    public Product(String name)
    {
        this.name = name;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
