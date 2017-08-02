package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TrabajoRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class TrabajoService {
   
   @Autowired
   private TrabajoRepository trabajoRepository;
}
