package araobp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import araobp.domain.entity.Box_Stats;
import araobp.domain.entity.Box__c;
import araobp.domain.service.BoxApiService;


@RestController
public class BoxApiServiceController {

	static final String NOT_FOUND_REASON = "ID not found";
	
	@Autowired
	BoxApiService boxApiService;
	
	@GetMapping("/box")
	public Iterable<Box__c> getBoxes() {
		return boxApiService.getBoxes();
	}
	
	@PatchMapping("/box/{id}")
	public void updateBox(@PathVariable Double id, @RequestBody Box__c box) {
		box.setId__c(id);
		Boolean success = boxApiService.updateBox(box);
		if (!success) throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_REASON);
	}
	
	@PatchMapping("/box/{id}/count")
	public void updateCount(@PathVariable Integer id) {
		boolean success = boxApiService.incrementCount(id);
		if (!success) throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_REASON);		
	}
	
	@PatchMapping("/reset")
	public void reset() {
		boolean success = boxApiService.resetAll();
		if (!success) throw new ResponseStatusException(HttpStatus.NOT_FOUND, NOT_FOUND_REASON);		
	}
	
	@GetMapping("/stats")
	public Iterable<Box_Stats> getBoxStats() {
		return boxApiService.getBoxStats();
	}
}
