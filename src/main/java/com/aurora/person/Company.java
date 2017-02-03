package com.aurora.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * Created by atif on 2/3/17.
 */
@Entity
@Table(name = "company")
public class Company
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String domain;
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

