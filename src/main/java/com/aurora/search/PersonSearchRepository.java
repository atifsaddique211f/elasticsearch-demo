package com.aurora.search;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by atif on 2/3/17.
 */
public interface PersonSearchRepository extends ElasticsearchRepository<Person, Long>
{
}
