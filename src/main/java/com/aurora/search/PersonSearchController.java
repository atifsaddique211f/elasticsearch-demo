package com.aurora.search;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

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

}


