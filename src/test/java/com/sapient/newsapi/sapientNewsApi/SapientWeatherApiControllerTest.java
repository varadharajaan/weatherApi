package com.sapient.newsapi.sapientNewsApi;


import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.newsapi.sapientNewsApi.controller.NewsApiController;
import com.sapient.newsapi.sapientNewsApi.service.NewsApiService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SapientWeatherApiControllerTest {
	
	@Mock
    private NewsApiService newsApiService;
    @Mock
    private ObjectMapper objectMapper;
    
    @InjectMocks
    @Spy
    private NewsApiController newsApiController;
    
    @SuppressWarnings(value = { "unchecked" })
    @Test
    public void getWeatherApivalues () {
    	
    	when(newsApiService.getNewApiValues(Mockito.anyString())).thenReturn("Controller working fine");
    
    	newsApiController.getNewsApiStreams("london");
    	
    	
    	
    }

}
