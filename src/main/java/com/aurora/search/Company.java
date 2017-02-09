package com.aurora.search;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by atif on 2/3/17.
 */

public class Company
{
    @Id
    private Long id;

    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.analyzed)
    private String name;

    private String domain;

    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.analyzed)
    private String email;
    private String vatIdentificationNumber;

    public Company()
    {
    }

    public Company(String name, String domain, String email, String vatIdentificationNumber)
    {
        this.name = name;
        this.domain = domain;
        this.email = email;
        this.vatIdentificationNumber = vatIdentificationNumber;
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

    public String getDomain()
    {
        return domain;
    }

    public void setDomain(String domain)
    {
        this.domain = domain;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getVatIdentificationNumber()
    {
        return vatIdentificationNumber;
    }

    public void setVatIdentificationNumber(String vatIdentificationNumber)
    {
        this.vatIdentificationNumber = vatIdentificationNumber;
    }
}

