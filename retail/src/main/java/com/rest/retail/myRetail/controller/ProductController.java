/**
 * 
 */
package com.rest.retail.myRetail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.retail.myRetail.exception.MyRetailException;
import com.rest.retail.myRetail.service.ProductService;
import com.rest.retail.myRetail.vo.response.ProductResponse;
import com.rest.retail.myRetail.vo.response.ProductVO;

/**
 * @author ananth
 *
 */

@RequestMapping ("/products")
@RestController
public class ProductController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductVO> getProductById(@PathVariable("id") String productId) throws MyRetailException {
		logger.info("Inside getProductById :  " + productId);

		ProductVO productResponse= new ProductVO();
		productResponse = productService.getProductById(productId);

		logger.debug(" product Response " + productResponse);
		return new ResponseEntity<ProductVO>(productResponse, HttpStatus.OK);
	}

	@ExceptionHandler(MyRetailException.class)
	public ResponseEntity<ProductResponse> exceptionHandler(MyRetailException ex) {
		logger.debug("Inside Exception  " + ex);
		ProductResponse error = new ProductResponse(ex.getErrorCode(),ex.getMessage());
		logger.debug(ex.getErrorMessage(),ex);
		return new ResponseEntity<ProductResponse>(error,HttpStatus.valueOf(ex.getErrorCode()));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> updatePrice(@RequestBody ProductVO product,
			@PathVariable("id") String productId) throws MyRetailException {
		if (!product.getProductId().equalsIgnoreCase(productId)) {
			throw new MyRetailException(HttpStatus.BAD_REQUEST.value(),"Product Id in URL and JSON mismatch");
		}
		productService.updateProductById(product);
		return new ResponseEntity<ProductResponse>(HttpStatus.OK);
	}


}
