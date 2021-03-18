package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.util.HeroCompared;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.*;
import java.util.Date;

@ActiveProfiles("it")
public class HeroComparedIT {
    private Hero capitao;
    private Hero thor;
    private PowerStats capitaoPower;
    private PowerStats thorPower;

    public HeroComparedIT() {
        this.capitao = new Hero();
        this.thor = new Hero();
        this.capitaoPower = new PowerStats();
        this.thorPower = new PowerStats();
    }

    private Hero createCapitao(){
        capitaoPower.setAgility(5);
        capitaoPower.setStrength(8);
        capitaoPower.setDexterity(8);
        capitaoPower.setIntelligence(9);
        capitaoPower.setCreated_at(new Date(2020,9,05));
        capitaoPower.setUpdated_at(new Date(2020,9,05));
        capitao.setName("Capitão America");
        capitao.setRace(Race.HUMAN.name());
        capitao.setPower_stats(capitaoPower);
        capitao.setEnabled(true);
        capitao.setCreated_at(new Date(2020,9,05));
        capitao.setCreated_at(new Date(2020,9,05));
        return capitao;
    }
    private Hero createThor(){
        thorPower.setAgility(4);
        thorPower.setStrength(10);
        thorPower.setDexterity(8);
        thorPower.setIntelligence(8);
        thorPower.setCreated_at(new Date(2020,9,05));
        thorPower.setUpdated_at(new Date(2020,9,05));
        thor.setName("Thor");
        thor.setRace(Race.DIVINE.name());
        thor.setPower_stats(thorPower);
        thor.setEnabled(true);
        thor.setCreated_at(new Date(2020,9,05));
        thor.setCreated_at(new Date(2020,9,05));
        return thor;
    }
    @Test
    public void comparedHeroes(){
        Hero hero = this.createCapitao();
        Hero hero1 = createThor();
        Hero hero2 = new HeroCompared().getHeroCompared(hero, hero1);
        assertEquals("Thor", hero2.getName());
        assertEquals(Race.DIVINE.name(), hero2.getRace());
    }
}
