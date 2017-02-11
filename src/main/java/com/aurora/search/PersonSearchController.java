package com.aurora.search;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/**
 * Created by atif on 2/3/17.
 */
@RestController
@RequestMapping
public class PersonSearchController
{

    @Inject
    private PersonSearchRepository personRepository;

    @RequestMapping(value = "/api/v2/persons",
            method = RequestMethod.GET)
    public List<Person> getPersons(@RequestParam String name,
                                   Pageable pageable)
    {
        BoolQueryBuilder builder = boolQuery();
        BoolQueryBuilder builder1 = boolQuery();
        builder1.should(matchQuery("firstName", name));
        builder1.should(matchQuery("lastName", name));
        builder.must(builder1);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .build();
        return personRepository.search(searchQuery).getContent();
    }

    // search person by company domain
    @RequestMapping(value = "/api/v2/person1",
            method = RequestMethod.GET)
    public List<Person> getPerson1(@RequestParam String companyDomain,
                                   Pageable pageable)
    {
        BoolQueryBuilder builder = boolQuery();
        BoolQueryBuilder builder1 = boolQuery();
        builder1.should(matchQuery("company.domain", companyDomain));
        builder.must(builder1);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .build();
        return personRepository.search(searchQuery).getContent();
    }

    // search all person between age 20-40
    @RequestMapping(value = "/api/v2/person3",
            method = RequestMethod.GET)
    public List<Person> getPerson3(Pageable pageable)
    {

      RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("age").to(40).from(20);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageable)
                .build();
        return personRepository.search(searchQuery).getContent();
    }

    //search all products whose price between 0-100000
    @RequestMapping(value = "/api/v2/person4",
            method = RequestMethod.GET)
    public List<Person> getPerson4(Pageable pageable)
    {

      RangeQueryBuilder queryBuilder = QueryBuilders.rangeQuery("products.price").to(100000).from(0);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .withPageable(pageable)
                .build();
        return personRepository.search(searchQuery).getContent();
    }

    //search all persons with most sales (still needs to be fixed)
    @RequestMapping(value = "/api/v2/person6",
            method = RequestMethod.GET)
    public List<Person> getPerson6(Pageable pageable)
    {
        TermsBuilder termsBuilder = AggregationBuilders.terms("prices")
                .field("products.price").order(Terms.Order.aggregation("_count",true));
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(matchAllQuery())
                .addAggregation(termsBuilder)
                .withPageable(pageable)
                .build();
        return personRepository.search(searchQuery).getContent();
    }

    //return all person of a specific gender
    @RequestMapping(value = "/api/v2/person5",
            method = RequestMethod.GET)
    public List<Person> getPerson5(@RequestParam Person.Gender gender,
                                   Pageable pageable)
    {
        BoolQueryBuilder builder = boolQuery();
        BoolQueryBuilder builder1 = boolQuery();
        builder1.must(matchQuery("gender", gender));
        builder.must(builder1);

        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(builder)
                .withPageable(pageable)
                .build();
        return personRepository.search(searchQuery).getContent();
    }

    //multisearch query
    @RequestMapping(value = "/api/v2/person7",
            method = RequestMethod.GET)
    public List<Person> getPerson7(@RequestParam String query, Pageable pageable)
    {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(multiMatchQuery(query)
                        .field("email")
                        .field("firstName")
                        .field("lastName")
                        .field("middleName")
                        .field("telephoneNumber")
                        .field("products.name")
                        .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                .withPageable(pageable)
                .build();
        return personRepository.search(searchQuery).getContent();
    }
}


