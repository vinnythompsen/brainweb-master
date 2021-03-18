package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.model.Hero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;
public interface HeroRepository extends CrudRepository<Hero, UUID> {
    @Query("SELECT h From Hero h where h.name like %?1%")
    List<Hero> findByName(String nome);
}
