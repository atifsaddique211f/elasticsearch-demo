package com.aurora.search;

import com.aurora.product.Product;
import com.aurora.shared.CustomLocalDateSerializer;
import com.aurora.shared.ISO8601LocalDateDeserializer;
import com.aurora.shared.LocalDatePersistenceConverter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by atif on 2/3/17.
 */
@Document(indexName = "provider", type = "provider", shards = 1, replicas = 0, refreshInterval = "-1")
@Setting(settingPath = "/es/settings.json")
public class Person
{
    @Id
    @Field(type = FieldType.Long,
            store = true,
            index = FieldIndex.not_analyzed)
    private Long id;

    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.analyzed)
    private String firstName;

    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.analyzed)
    private String middleName;

    @Field(type = FieldType.String,
            store = true,
            index = FieldIndex.analyzed)
    private String lastName;
    private String email;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private String telephoneNumber;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    private LocalDate dateOfBirth;

    private Integer age;
    private String companyEmail;
    private String nationalIdentityCardNumber;
    private String nationalIdentificationNumber;
    private String passportNumber;

    private Address address;

    private Company company;

    private Set<Product> products = new HashSet<>();

    public static enum Sex
    {
        MALE, FEMALE
    }

    public Person()
    {
    }

    public Person(String firstName, String middleName, String lastName, Address address, String email, String username,
                  String password, Sex sex, String telephoneNumber, LocalDate dateOfBirth, Integer age,
                  String nationalIdentityCardNumber, String nationalIdentificationNumber, String passportNumber, Company company, String companyEmail)
    {
        this.nationalIdentityCardNumber = nationalIdentityCardNumber;
        this.address = address;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.telephoneNumber = telephoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.nationalIdentificationNumber = nationalIdentificationNumber;
        this.company = company;
        this.companyEmail = companyEmail;
        this.passportNumber = passportNumber;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Sex getSex()
    {
        return sex;
    }

    public void setSex(Sex sex)
    {
        this.sex = sex;
    }

    public String getTelephoneNumber()
    {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber)
    {
        this.telephoneNumber = telephoneNumber;
    }

    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Company getCompany()
    {
        return company;
    }

    public void setCompany(Company company)
    {
        this.company = company;
    }

    public String getCompanyEmail()
    {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail)
    {
        this.companyEmail = companyEmail;
    }

    public String getNationalIdentityCardNumber()
    {
        return nationalIdentityCardNumber;
    }

    public void setNationalIdentityCardNumber(String nationalIdentityCardNumber)
    {
        this.nationalIdentityCardNumber = nationalIdentityCardNumber;
    }

    public String getNationalIdentificationNumber()
    {
        return nationalIdentificationNumber;
    }

    public void setNationalIdentificationNumber(String nationalIdentificationNumber)
    {
        this.nationalIdentificationNumber = nationalIdentificationNumber;
    }

    public String getPassportNumber()
    {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber)
    {
        this.passportNumber = passportNumber;
    }

    public void addToProducts(Product product)
    {
        this.products.add(product);
    }

    public void removeFromProduct(Product product)
    {
        this.products.remove(product);
    }
}
