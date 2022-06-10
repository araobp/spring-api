package araobp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import araobp.domain.entity.Box__c;
import araobp.domain.repository.BoxRepository;

@Service
@Transactional
public class ApiMockServiceImpl implements ApiMockService {

	@Autowired
	BoxRepository boxRepository;

	@Override
	public Iterable<Box__c> getBoxes() {
		return boxRepository.findAll();
	}

	@Override
	public Boolean updateBox(Box__c box) {
		try {
			if (boxRepository.existsById(box.getId__c())) {
				boxRepository.update(box.getId__c(), box.getMove__c());
				return true;
			} else {
				return false;
			}
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

}
