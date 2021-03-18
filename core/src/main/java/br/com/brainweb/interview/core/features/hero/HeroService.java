package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class HeroService {
    @Autowired
    private HeroRepository repository;

    public Hero save(Hero entity){
        return repository.save(entity);
    }
    public List<Hero> findAll(){
        List<Hero> heroes = new ArrayList<>();
        repository.findAll().forEach(heroes::add);
        return heroes;
    }
    public Hero findById(UUID id){
        Optional<Hero> hero = repository.findById(id);
        if(hero.isPresent())
            return hero.get();
        else
            return null;
    }
    public List<Hero> findByName(String name){
       return repository.findByName(name);
    }
    public void delete(Hero entity){
        repository.delete(entity);
    }
}
