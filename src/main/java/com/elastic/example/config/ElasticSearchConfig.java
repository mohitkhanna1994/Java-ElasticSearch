package com.elastic.example.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value="classpath:/${spring.profiles.active}/application.properties")
public class ElasticSearchConfig extends AbstractFactoryBean<RestHighLevelClient> {

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusterNodes;

	@Value("${spring.data.elasticsearch.cluster-name}")
	private String clusterName;

	@Value("${elasticsearch.host}")
	private String elasticHost;

	@Value("${elasticsearch.port}")
	private int elasticPort;

	private RestHighLevelClient restHighLevelClient;

	@Override
	public void destroy() {
		try {
			if (restHighLevelClient != null)
				restHighLevelClient.close();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	@Override
	protected RestHighLevelClient createInstance() throws Exception {
		return buildClient();
	}

	private RestHighLevelClient buildClient() {
		System.out.println("client built::"+ elasticHost);
		try {
			restHighLevelClient = new RestHighLevelClient(
					RestClient.builder(new HttpHost(elasticHost, elasticPort, "http")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rest CLient"+restHighLevelClient);
		return restHighLevelClient;
	}

	@Override
	public Class<RestHighLevelClient> getObjectType() {
		return RestHighLevelClient.class;
	}

}
