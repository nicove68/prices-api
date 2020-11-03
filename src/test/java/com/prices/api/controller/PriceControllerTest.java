package com.prices.api.controller;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.prices.api.exception.RestControllerErrorHandler;
import com.prices.api.model.Brand;
import com.prices.api.service.PriceService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class PriceControllerTest {

  private static final String BASE_PATH = "/prices";

  private MockMvc mvc;

  @InjectMocks
  private PriceController priceController;

  @Mock
  private PriceService priceService;

  @Rule
  public ExpectedException exceptionRule = ExpectedException.none();


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(priceController)
        .setControllerAdvice(new RestControllerErrorHandler())
        .build();
  }

  @Test
  public void findAllPricesByBrand_ok() throws Exception {
    doReturn(emptyList())
        .when(priceService).findByBrand(any(Brand.class));

    mvc.perform(
        get(BASE_PATH)
            .header("x-brand", "ZARA")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void findAllPricesByBrand_withWrongBrand_fail() throws Exception {
    mvc.perform(
        get(BASE_PATH)
            .header("x-brand", "WORLD")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }
}
