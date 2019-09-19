package com.rest.retail.myRetail.controller;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rest.retail.myRetail.controller.ProductController;
import com.rest.retail.myRetail.exception.MyRetailException;
import com.rest.retail.myRetail.service.ProductService;
import com.rest.retail.myRetail.vo.response.CurrentPriceVO;
import com.rest.retail.myRetail.vo.response.ProductVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = ProductController.class, secure = true)
public class ProductControllerTest {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductControllerTest.class);

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ProductService productService;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getProductByIdTest() throws Exception{
		
		ProductVO product = new ProductVO();
		CurrentPriceVO currentPrice = new CurrentPriceVO();
		product.setProductId("13860428");
		product.setName("The Big Lebowski (Blu-ray)");
		currentPrice.setCurrencyCode("USD");
		currentPrice.setValue(13.49);
		product.setCurrentprice(currentPrice);
		
		Mockito.when(productService.getProductById(Mockito.anyString())).thenReturn(product);
		String prod_url = "/products/13860428";
		RequestBuilder builder = MockMvcRequestBuilders.get(prod_url).accept(MediaType.APPLICATION_JSON_VALUE);
		
		MvcResult actual = mockMvc.perform(builder).andReturn();
		
		String expected = "{\"id\":\"13860428\",\"name\":\"The Big Lebowski (Blu-ray)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";
		
		JSONAssert.assertEquals(expected, actual.getResponse().getContentAsString(), false);
	}

	@Test
	public void getProductByWrongIdTest() throws Exception{
		
		try {
			String actual = mockMvc.perform(get("/products/123456")).andReturn().getResponse().getContentAsString();
			assertEquals("", actual);;
		
		} catch (MyRetailException	 e) {
			LOGGER.debug("Product not found Exception test sucess.");
		}
		
	}
}
