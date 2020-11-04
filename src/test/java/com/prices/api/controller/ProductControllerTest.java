package com.prices.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.prices.api.exception.RestControllerErrorHandler;
import com.prices.api.model.Brand;
import com.prices.api.model.PriceDTO;
import com.prices.api.service.PriceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
public class ProductControllerTest {

  private static final String BASE_PATH = "/products";

  private MockMvc mvc;

  @InjectMocks
  private ProductController productController;

  @Mock
  private PriceService priceService;


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mvc = MockMvcBuilders.standaloneSetup(productController)
        .setControllerAdvice(new RestControllerErrorHandler())
        .build();
  }

  @Test
  public void findActiveProductPrice_ok() throws Exception {
    doReturn(PriceDTO.builder().build())
        .when(priceService).findActivePrice(anyString(), anyLong(), any(Brand.class));

    mvc.perform(
        get(BASE_PATH + "/1234/price" )
            .param("date", "2000-10-31T01:30:00.000Z")
            .header("x-brand", "ZARA")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void findActiveProductPrice_withWrongBrand_fail() throws Exception {
    mvc.perform(
        get(BASE_PATH + "/1234/price" )
            .header("x-brand", "WORLD")
            .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().is4xxClientError());
  }
}
