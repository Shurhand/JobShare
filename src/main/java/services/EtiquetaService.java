package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.EtiquetaRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class EtiquetaService {
   
   @Autowired
   private EtiquetaRepository etiquetaRepository;
}
