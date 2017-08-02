package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.EstudioRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class EstudioService {
   
   @Autowired
   private EstudioRepository estudioRepository;
}
