package com.aurora.search;


import org.springframework.data.annotation.Id;

/**
 * Created by atif on 1/27/17.
 */
public class Product
{
    @Id
    private Long id;

    private String name;

    private double price;

    public Product()
    {

    }

    public Product(String name, double price)
    {
        this.name = name;
        this.price = price;
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

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }
}
