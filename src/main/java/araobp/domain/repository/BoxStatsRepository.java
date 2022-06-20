package araobp.domain.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import araobp.domain.entity.Box_Stats;

public interface BoxStatsRepository extends CrudRepository<Box_Stats, Integer> {

	@Modifying
	@Query("UPDATE box_stats SET count = :count WHERE id = :id")
	public Integer updateCount(@Param("id") Integer id, @Param("count") Integer count);
	
}
