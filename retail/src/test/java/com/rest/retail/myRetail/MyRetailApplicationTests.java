package com.rest.retail.myRetail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.rest.retail.myRetail.controller.ProductControllerTest;
import com.rest.retail.myRetail.service.ProductServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ProductControllerTest.class,ProductServiceTest.class
})


@SpringBootTest
@AutoConfigureMockMvc
public class MyRetailApplicationTests {
	@Test
	public void contextLoads() {
	}
	
	
	
}
