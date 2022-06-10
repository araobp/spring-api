package araobp.domain.service;

import araobp.domain.entity.Box__c;

public interface ApiMockService {

	Iterable<Box__c> getBoxes();
	
	Boolean updateBox(Box__c box);

}