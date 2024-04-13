package com.load.rating.serivce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RatingServiceApplicationTests {

	@Test
    void contextLoads() {
        try {
            // Add any additional debugging information here
            System.out.println("Attempting to load application context...");
            // Call the Spring Boot application context loading code here
            // For example: SpringApplication.run(RatingServiceApplication.class);
        } catch (Exception ex) {
        	System.out.println("Attempting to load application context error solving...");
            // Log any exceptions that occur during context loading
            ex.printStackTrace();
        }
    }

}
