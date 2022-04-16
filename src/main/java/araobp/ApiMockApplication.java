package araobp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import araobp.domain.service.ApiMockService;

@SpringBootApplication
public class ApiMockApplication {

	@Autowired
	ApiMockService service;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiMockApplication.class, args);
	}

}
