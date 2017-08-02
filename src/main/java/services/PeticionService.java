package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PeticionRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class PeticionService {
   
   @Autowired
   private PeticionRepository peticionRepository;
}
