package com.yjk.app.config;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SolrConfig {

	@Bean
	public SolrClient solrClient() {
		SolrClient httpSolrClient = new HttpSolrClient("http://42.159.95.63:8080/solr");
		return httpSolrClient;
	}
}
