package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.util.HeroCompared;
import br.com.brainweb.interview.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class HeroController {
    @Autowired
    private HeroService service;
    @GetMapping("/hero")
    public @ResponseBody ResponseEntity<List<Hero>> findAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @PostMapping("/hero")
    public @ResponseBody ResponseEntity<Hero> createHero(@Valid @RequestBody Hero hero){
        return new ResponseEntity<>(service.save(hero), HttpStatus.OK);
    }

    @GetMapping("/hero/{id}")
    public @ResponseBody ResponseEntity<Hero> findById(@PathVariable("id") UUID id){
        Hero hero = service.findById(id);
        if(hero != null)
            return new ResponseEntity<>(hero, HttpStatus.OK);
        else
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/hero/{heroId}/{hero1Id}")
    public @ResponseBody ResponseEntity<Hero> comparedHeroes(@PathVariable("heroId") UUID heroId,
                                                             @PathVariable("hero1Id") UUID hero1Id){
        Hero hero = service.findById(heroId);
        Hero  hero1 = service.findById(hero1Id);
        if(hero != null && hero1 != null)
            return new ResponseEntity<>(new HeroCompared().getHeroCompared(hero, hero1), HttpStatus.OK);
        else
            return  new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByName/{name}")
    public @ResponseBody ResponseEntity<List<Hero>> findByName(@PathVariable("name") String nome){
        List<Hero> list  =service.findByName(nome);
        if(list != null)
            return new ResponseEntity<>(list, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/hero")
    public @ResponseBody ResponseEntity<Hero> updateHero(@Valid @RequestBody Hero hero){
        Hero h = service.save(hero);
        if(h != null)
            return new ResponseEntity<>(h, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/hero")
    public @ResponseBody ResponseEntity<Void> removeHero(@RequestBody Hero hero){
        Hero h = service.findById(hero.getId());
        if(h != null){
            service.delete(h);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
