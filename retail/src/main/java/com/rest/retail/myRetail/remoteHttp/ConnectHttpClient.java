/**
 * 
 */
package com.rest.retail.myRetail.remoteHttp;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.rest.retail.myRetail.exception.MyRetailException;

/**
 * @author ananth
 * class to make external rest connection to redsky.target to get product title
 */
@Component
public class ConnectHttpClient {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RestTemplate restTemplate;

	@Value("${product-api-endpoint}")
	private String apiEndpointURL;

	private String product_URI = "/v2/pdp/tcin/";
	private String productName = null;

	public ConnectHttpClient() {
	}

	public String getProductNameByRemoteCall(String productId) throws MyRetailException {

		try {
			logger.info("Inside ConnectHttpClient().getProductNameByRemoteCall");
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiEndpointURL + product_URI + productId)
					.queryParam("excludes",
							"taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics");

			// Send request with GET method, and Headers.
			String jsonResponse = restTemplate.getForObject(builder.build().encode().toUri(), String.class);

			if (jsonResponse != null) {
				JSONObject jsonObject = new JSONObject(jsonResponse);
				logger.debug("JSON Response from Remote Client  :" + jsonResponse.toString());

				if (jsonObject.getJSONObject("product").getJSONObject("item")
						.getJSONObject("product_description") != null) {
					JSONObject productDescription = jsonObject.getJSONObject("product").getJSONObject("item")
							.getJSONObject("product_description");
					productName = productDescription.getString("title");
				} else {
					logger.debug("Product title JSON value Unavailable in Product API");
					throw new MyRetailException(HttpStatus.NO_CONTENT.value(),
							"The title does not exists for the product");
				}
			}
		} catch (RestClientException e) {
			logger.debug("Product API unavailable  :" + apiEndpointURL + product_URI + productId);
			throw new MyRetailException(HttpStatus.NOT_FOUND.value(),
					"Product id: " + productId + " unavailable in Remote API ");
		}
		return productName;
	}

}
