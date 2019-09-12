# Retail REST service
# Prerequisites
	Java 8
	Maven

# Technology used
	Java 8
	Spring boot
	Swagger2
	Maven
	MongoDB

# Execution 
	Clone or download the project from https://github.com/ananthbabu/target.git
	Navigate inside to the folder retail
	
	Launch terminal on Mac or cmd prompt in windows
	Run mvn spring-boot:run
	
# Testing
	Launch http://localhost:8081/myRetail/swagger-ui.html 
	NOTE: Change the port if you are modifying application.properties
	
# Sample input
•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number. 

•	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 

•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail)  

•	Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics

•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response.  

•	BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store.  
