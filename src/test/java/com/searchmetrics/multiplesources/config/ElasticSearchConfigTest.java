package com.searchmetrics.multiplesources.config;

import com.searchmetrics.multiplesources.Developer;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.GetQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;

import static org.assertj.core.api.Java6Assertions.assertThat;

@SpringBootTest
class ElasticSearchConfigTest {

    @Autowired
    @Qualifier(value = "smallElasticSearchTemplate")
    private ElasticsearchOperations smallEsTemplate;

    @Autowired
    @Qualifier(value = "bigElasticSearchTemplate")
    private ElasticsearchOperations bigEsTemplate;

    @Test
    void test(){

        // Save initial data
        Developer developer = new Developer("1", "name");
        IndexQuery query = new IndexQueryBuilder()
                .withId(developer.getId())
                .withObject(developer)
                .build();
        smallEsTemplate.index(query);


        GetQuery getQuery = new GetQuery();
        getQuery.setId("1");
        Developer foundDeveloper = smallEsTemplate.queryForObject(getQuery, Developer.class);

        assertThat(foundDeveloper).isEqualToComparingFieldByField(developer);

        // Save to new index
        IndexQuery queryBig = new IndexQueryBuilder()
                .withId(foundDeveloper.getId())
                .withObject(foundDeveloper)
                .build();
        bigEsTemplate.index(queryBig);

    }
}