package com.sapient.newsapi.sapientNewsApi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.sapient.newsapi.sapientNewsApi.controller.NewsApiController;
import com.sapient.newsapi.sapientNewsApi.exception.IntegerPraserException;
import com.sapient.newsapi.sapientNewsApi.exception.ValueNotFoundException;

@Component
public class NewsApiServiceImpl implements NewsApiService{

	
	private static final Logger logger = LoggerFactory.getLogger(NewsApiController.class);
	
	@Autowired
	public RestTemplate restTemplate;
	
	@Override
	public String getNewApiValues(String queryValue) {
		
		if(queryValue.isEmpty()||queryValue.equals(null))
			throw new ValueNotFoundException("Query Value cannot be null..!,Enter Proper City/Country Name.");

        try {
            String uri = "http://api.apixu.com/v1/forecast.json/";

            HttpHeaders headers = new HttpHeaders();


            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
                    .queryParam("key", "d41eb19c8e944d8ca5054053191506")
                    .queryParam("q", queryValue);

            HttpEntity<?> entity = new HttpEntity<>(headers);
            System.out.println("final url-->" + builder.toUriString());
            HttpEntity<String> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    String.class);
            String val = response.getBody();
            String[] arrOfStr = val.split(",");
            String temperature = null;
            for (String temp : arrOfStr) {
                logger.debug("parsed string-->" + temp);
                if (temp.contains("maxtemp_c")) {
                    temperature = temp.substring(19, 21);
                    System.out.println(temperature);
                }
            }
            try {
                if (Integer.parseInt(temperature) > 40) {
                    logger.debug("working fine..!");
                    logger.debug("printed JSON response bocdy-->" + response.getBody());
                    return "Temperature is high. Take Umberella or use sun lotion..!";
                } else
                    return "Temperature forcasted to be normal. Good to Plan for Travel..!";
            } catch (Exception ex) {
                throw new IntegerPraserException("Parser Exception. Taken care, try after sometime..!");
            }
        } catch (Exception ex) {
            throw new IntegerPraserException("not found proper value.! Try again later..");
        }
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
