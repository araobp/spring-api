package araobp.domain.service;

import araobp.domain.entity.Box_Stats;
import araobp.domain.entity.Box__c;

public interface BoxApiService {

	Iterable<Box__c> getBoxes();
	
	Boolean updateBox(Box__c box);
	
	Boolean incrementCount(Double id);
	
	Boolean resetAll();
	
	Iterable<Box_Stats> getBoxStats();

}