package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ActorRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class ActorService {
   
   @Autowired
   private ActorRepository actorRepository;
}
