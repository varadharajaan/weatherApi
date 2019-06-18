package com.sapient.newsapi.sapientNewsApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.sapient.newsapi.sapientNewsApi.exception.IntegerPraserException;
import com.sapient.newsapi.sapientNewsApi.exception.ValueNotFoundException;
import com.sapient.newsapi.sapientNewsApi.service.NewsApiServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class SapientWeathersServiceTest {
	
	@Autowired
    @Mock
    RestTemplate restTemplate;
	
	@InjectMocks
	@Spy
	private NewsApiServiceImpl newsApiServiceImpl = new NewsApiServiceImpl();
	
	
	@Test
	public void getWeatherApiForTempGreaterThanForty() throws Exception {
		
		ResponseEntity<String> myEntity = new ResponseEntity<>("\"day\":{\"maxtemp_c\":46.3",
                HttpStatus.ACCEPTED);

        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any()))
                        .thenReturn(myEntity);
        String value =newsApiServiceImpl.getNewApiValues("london");
        assertNotNull(value);
        assertEquals(value, "Temperature is high. Take Umberella or use sun lotion..!");
		 
	}
	
	@Test
	public void getWeatherApiForTempLessThanForty() throws Exception {
		
		ResponseEntity<String> myEntity = new ResponseEntity<>("\"day\":{\"maxtemp_c\":36.3",
                HttpStatus.ACCEPTED);

        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any()))
                        .thenReturn(myEntity);
        String value = newsApiServiceImpl.getNewApiValues("london");
        assertNotNull(value);
        assertEquals(value, "Temperature forcasted to be normal. Good to Plan for Travel..!");
		 
	}
	
	@Test(expected =IntegerPraserException.class )
	public void getWeatherApiwithParserException() throws Exception {
		
		ResponseEntity<String> myEntity = new ResponseEntity<>("\"day\":{\"maxtemp_c\":null",
                HttpStatus.ACCEPTED);

        when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any()))
                        .thenReturn(myEntity);
        newsApiServiceImpl.getNewApiValues("london");
		 
	}
	
	@Test(expected =ValueNotFoundException.class )
	public void getWeatherApiwithValueNotFoundException() throws Exception {
		
	
        newsApiServiceImpl.getNewApiValues("");
		 
	}
	
	

}
