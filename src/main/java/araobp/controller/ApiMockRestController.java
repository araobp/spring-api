package araobp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import araobp.domain.entity.Box;
import araobp.domain.service.ApiMockService;


@RestController
public class ApiMockRestController {

	@Autowired
	ApiMockService apiMockService;
	
	@GetMapping("/box")
	public Iterable<Box> move() {
		return apiMockService.getBoxes();
	}
}
