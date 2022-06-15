package araobp.domain.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Box_Stats {
	@Id
	private Double id__c;
	//private Integer id__c;

	private Double count;
}
