package araobp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import araobp.domain.entity.Box;
import araobp.domain.service.ApiMockService;


@RestController
public class ApiMockRestController {

	static final String NOT_FOUND_REASON = "ID not found";
	
	@Autowired
	ApiMockService apiMockService;
	
	@GetMapping("/box")
	public Iterable<Box> getBoxes() {
		return apiMockService.getBoxes();
	}
	
	@PatchMapping("/box/{id}")
	public void updateBox(@PathVariable Integer id, @RequestBody Box box) {
		box.setId(id);
		Boolean success = apiMockService.updateBox(box);
		if (!success) throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_REASON);
	}
}
