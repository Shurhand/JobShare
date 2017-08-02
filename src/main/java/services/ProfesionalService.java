package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ProfesionalRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProfesionalService {
   
   @Autowired
   private ProfesionalRepository profesionalRepository;
}
