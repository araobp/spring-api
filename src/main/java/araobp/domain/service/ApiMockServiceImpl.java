package araobp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import araobp.domain.entity.Box;
import araobp.domain.repository.BoxRepository;

@Service
@Transactional
public class ApiMockServiceImpl implements ApiMockService {

	@Autowired
	BoxRepository boxRepository;
	
	@Override
	public Iterable<Box> getBoxes() {
		return boxRepository.findAll();
	}

	@Override
	public Boolean updateBox(Box box) {
		try {
			boxRepository.save(box);
			return true;
		} catch(IllegalArgumentException e) {
			return false;
		}
	}

}
