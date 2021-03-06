package services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AbstractServiceImpl {
   public List<String> getListaErrores(BindingResult binding) {
      return binding.getFieldErrors().stream().map(x -> x.getField()).collect(Collectors.toList());
      
   }
}
