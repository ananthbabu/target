/**
 * 
 */
package com.rest.retail.myRetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rest.retail.myRetail.entity.Product;

/**
 * @author ananth
 *
 */
public interface ProductRepository extends MongoRepository<Product,String> {
	
	
	/**
	 * @param productId
	 * @return
	 */
	public Product findProductByproductId(String productId);

}
