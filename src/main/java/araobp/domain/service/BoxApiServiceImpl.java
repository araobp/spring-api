package araobp.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import araobp.domain.entity.Box_Stats;
import araobp.domain.entity.Box__c;
import araobp.domain.repository.BoxRepository;
import araobp.domain.repository.BoxStatsRepository;

@Service
@Transactional
public class BoxApiServiceImpl implements BoxApiService {

	@Autowired
	BoxRepository boxRepository;
	
	@Autowired
	BoxStatsRepository boxStatsRepository;

	@Override
	public Iterable<Box__c> getBoxes() {
		return boxRepository.findAll();
	}

	@Override
	public Boolean updateBox(Box__c box) {
		try {
			if (boxRepository.existsById(box.getId__c())) {
				boxRepository.updateBox(box.getId__c(), box.getMove__c());
				return true;
			} else {
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	@Override
	public Boolean incrementCount(Double id__c) {
		boolean success = false;
		try {
			if (boxStatsRepository.existsById(id__c)) {
				Optional<Box_Stats> stats = boxStatsRepository.findById(id__c);
				if (stats.isPresent()) {
					Double count = stats.get().getCount();
					boxStatsRepository.updateCount(id__c, ++count);
					success = true;
				}
			}
		} catch (Exception e) {
			//
		}
		return success;
	}

	@Override
	public Boolean resetAll() {
		boolean success = false;
		try {
			Iterable<Box__c> boxes = boxRepository.findAll();
			boxes.forEach(b -> {
				Double id = b.getId__c();
				boxRepository.updateBox(id, false);
				boxStatsRepository.updateCount(id, 0D);
			});
			success = true;
		} catch (Exception e) {
			//
		}
		return success;
	}

}
