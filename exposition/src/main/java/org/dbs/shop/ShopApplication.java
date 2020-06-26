package org.dbs.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopApplication {

	private static final Logger LOG = LoggerFactory.getLogger(ShopApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(ShopApplication.class, args);
		LOG.info("Application is running!\n look at http://localhost:9095/swagger-ui.html");
	}
}
