package com.example.javaelasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class JavaElasticSearchRetrieval {
    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // To retrieve data from test indices of elasticsearch
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices("test");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        // To retrieve data with employeeAge =24
        // searchSourceBuilder.query(QueryBuilders.termQuery("employeeAge", 24));

        // To retrieve data with employeeName = Roshan
        //searchSourceBuilder.query(QueryBuilders.matchQuery("employeeName","Roshan"));

        //To retrieve data with employeeAge from 20 to 24
        //searchSourceBuilder.query(QueryBuilders.rangeQuery("employeeAge").from(20).to(24));
        searchRequest.source(searchSourceBuilder);

        Map<String, Object> map = null;

        try {
            SearchResponse searchResponse = null;
            searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            if (searchResponse.getHits().getTotalHits().value > 0) {
                SearchHit[] searchHit = searchResponse.getHits().getHits();
                for (SearchHit hit : searchHit) {
                    map = hit.getSourceAsMap();
                    System.out.println("map:" + Arrays.toString(map.entrySet().toArray()));

                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        // To retrieve data of an particular id
      /*  GetRequest getRequest = new GetRequest("test");
        getRequest.id("af4pOXsBwqWRmZ0FfVJU");
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        System.out.println("Employee data of given ID :" + getResponse);

        // To retrieve data from age 20 to 24
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .postFilter(QueryBuilders.rangeQuery("employeeAge").from(20).to(24));
        SearchRequest searchRequestQuery = new SearchRequest();
        searchRequestQuery.searchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequestQuery.source(builder);
        SearchResponse response = client.search(searchRequestQuery, RequestOptions.DEFAULT);
        System.out.println(" Employee data with age between 20 t0 24 " + response);
*/

    }
}


