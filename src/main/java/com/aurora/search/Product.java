package com.aurora.search;


import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by atif on 1/27/17.
 */
public class Product
{
    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.not_analyzed)
    private Long id;

    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.analyzed)
    private String name;

    @Field(type = FieldType.Double,
            store = true,
            index = FieldIndex.analyzed)
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
