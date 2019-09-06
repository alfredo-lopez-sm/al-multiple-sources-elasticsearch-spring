package com.searchmetrics.multiplesources.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class ElasticSearchConfig {

    @Bean
    public ElasticsearchOperations smallElasticSearchTemplate() throws UnknownHostException {

        Settings settings = Settings.builder()
                .put("cluster.name", "small")
                .build();

        Client client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9301));

        return new ElasticsearchTemplate(client);
    }

    @Bean
    public ElasticsearchOperations bigElasticSearchTemplate() throws UnknownHostException {

        Settings settings = Settings.builder()
                .put("cluster.name", "big")
                .build();

        Client client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9302));

        return new ElasticsearchTemplate(client);
    }
}
