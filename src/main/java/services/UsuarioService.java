package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.UsuarioRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
   
   @Autowired
   private UsuarioRepository usuarioRepository;
}
