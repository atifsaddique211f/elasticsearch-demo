package com.aurora.search;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by atif on 2/3/17.
 */
public class Address
{

    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.not_analyzed)
    private Long id;

    private String postalCode;

    @Field(type = FieldType.String, store = true, index = FieldIndex.analyzed, searchAnalyzer = "whitespace_analyzer", analyzer = "edge_nGram_analyzer")
    private String city;

    @Field(type = FieldType.String, store = true, index = FieldIndex.analyzed, searchAnalyzer = "whitespace_analyzer", analyzer = "edge_nGram_analyzer")
    private String street;

    private String streetNumber;

    private String apartmentNumber;

    public Address()
    {
    }

    public Address(String postalCode, String city, String street, String streetNumber, String apartmentNumber)
    {
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getPostalCode()
    {
        return postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getStreetNumber()
    {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber()
    {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber)
    {
        this.apartmentNumber = apartmentNumber;
    }

    @Override
    public String toString()
    {
        return postalCode + " " + city;
    }
}

