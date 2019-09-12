/**
 * 
 */
package com.rest.retail.myRetail.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.retail.myRetail.entity.CurrentPrice;
import com.rest.retail.myRetail.entity.Product;
import com.rest.retail.myRetail.exception.MyRetailException;
import com.rest.retail.myRetail.repository.ProductRepository;

/**
 * @author ananth
 *
 */
@Service
public class SampleDataLoaderService {

	@Autowired
	private ProductRepository productRepository;

	//constructor
	public SampleDataLoaderService() {};

	@PostConstruct
	public void init() throws MyRetailException{
		loadProductPriceInDB();
	}

	//Load the products in DB
	private void loadProductPriceInDB() {


		if(productRepository != null) {

			List<Product> prodList = new ArrayList<Product>();
			CurrentPrice currentPriceObj1 = new CurrentPrice();
			currentPriceObj1.setCurrencyCode("USD");
			currentPriceObj1.setValue(13.49);
			Product product1 = new Product("13860428",currentPriceObj1);
			prodList.add(product1);

			CurrentPrice currentPriceObj2 = new CurrentPrice();
			currentPriceObj2.setCurrencyCode("USD");
			currentPriceObj2.setValue(18.99);
			Product product2 = new Product("16752456",currentPriceObj2);
			prodList.add(product2);

			CurrentPrice currentPriceObj3 = new CurrentPrice();
			currentPriceObj3.setCurrencyCode("USD");
			currentPriceObj3.setValue(29.99);
			Product product3 = new Product("16752457",currentPriceObj3);
			prodList.add(product3);

			//Deleting any data before load
			productRepository.deleteAll();

			productRepository.save(prodList);
		}
	}

}