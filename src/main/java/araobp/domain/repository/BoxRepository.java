package araobp.domain.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import araobp.domain.entity.Box__c;

public interface BoxRepository extends CrudRepository<Box__c, Double> {

	@Modifying
	@Query("UPDATE box__c SET move__c = :move__c WHERE id__c = :id__c")
	public Integer updateBox(@Param("id__c") Double id__c, @Param("move__c") Boolean move__c);
	
	@Modifying
	@Query("UPDATE box__c SET count__c = :count__c WHERE id__c = :id__c")
	public Integer updateCount(@Param("id__c") Double id__c, @Param("count__c") Double count__c);
	
}
