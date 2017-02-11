package com.aurora.search;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.highlight.HighlightField;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;

/**
 * Created by atif on 2/11/17.
 */
@Service
public class PersonSearchService
{
    @Inject
    private Client esClient;

    public Set<String> getResultsListForTypeAHead(String index, String query, Pageable pageable, String... fields)
    {
        SearchRequestBuilder searchRequestBuilder = esClient.prepareSearch(index)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(multiMatchQuery(query, fields)
                        .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                .setHighlighterFilter(true)
                .setHighlighterQuery(multiMatchQuery(query, fields)
                        .type(MultiMatchQueryBuilder.Type.BEST_FIELDS))
                .setHighlighterRequireFieldMatch(true)
                .setFrom(pageable.getPageSize() * pageable.getPageNumber())
                .setSize(pageable.getPageSize());

        for (int i = 0; i < fields.length; i++)
        {
            searchRequestBuilder.addHighlightedField(new HighlightBuilder.Field(fields[i]));
        }

        SearchResponse response = searchRequestBuilder.execute().actionGet();
        SearchHits hits = response.getHits();

        Set<String> suggestions = new HashSet<>();
        for (SearchHit hit : hits)
        {
            Map<String, HighlightField> map = hit.getHighlightFields();
            for (int i = 0; i < fields.length; i++)
            {
                HighlightField field = map.get(fields[i]);
                if (field != null)
                {
                    String text = field.getFragments()[0].toString();
                    if (text != null && !suggestions.contains(text))
                    {
                        text=text.replace("</em>","");
                        text=text.replace("<em>","");
                        suggestions.add(text);
                    }
                }
            }
        }

        return suggestions;
    }

}
