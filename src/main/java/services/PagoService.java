package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PagoRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class PagoService {
   
   @Autowired
   private PagoRepository pagoRepository;
}
