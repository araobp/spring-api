package araobp.domain.service;

import araobp.domain.entity.Box__c;

public interface BoxApiService {

	Iterable<Box__c> getBoxes();
	
	Boolean updateBox(Box__c box);
	
	Boolean incrementCount(Double id__c);

}