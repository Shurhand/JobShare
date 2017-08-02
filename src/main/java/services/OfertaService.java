package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.OfertaRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class OfertaService {
   
   @Autowired
   private OfertaRepository ofertaRepository;
}
