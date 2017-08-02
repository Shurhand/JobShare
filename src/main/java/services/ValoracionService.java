package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ValoracionReporsitory;

import javax.transaction.Transactional;

@Service
@Transactional
public class ValoracionService {
   
   @Autowired
   private ValoracionReporsitory valoracionReporsitory;
}
