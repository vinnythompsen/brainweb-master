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
	private Hero pantera;
	private Hero thor;
	private PowerStats panteraPower;
	private PowerStats thorPower;

	public HeroComparedIT() {
		this.pantera = new Hero();
		this.thor = new Hero();
		this.panteraPower = new PowerStats();
		this.thorPower = new PowerStats();
	}

	private Hero createPantera() {
		panteraPower.setAgility(5);
		panteraPower.setStrength(8);
		panteraPower.setDexterity(8);
		panteraPower.setIntelligence(9);
		panteraPower.setCreated_at(new Date(2020, 03, 15));
		panteraPower.setUpdated_at(new Date(2020, 03, 15));
		pantera.setName("Pantera Negra");
		pantera.setRace(Race.HUMAN.name());
		pantera.setPower_stats(panteraPower);
		pantera.setEnabled(true);
		pantera.setCreated_at(new Date(2020, 03, 15));
		pantera.setCreated_at(new Date(2020, 03, 15));
		return pantera;
	}

	private Hero createThor() {
		thorPower.setAgility(4);
		thorPower.setStrength(10);
		thorPower.setDexterity(8);
		thorPower.setIntelligence(8);
		thorPower.setCreated_at(new Date(2020, 03, 15));
		thorPower.setUpdated_at(new Date(2020, 03, 15));
		thor.setName("Thor");
		thor.setRace(Race.DIVINE.name());
		thor.setPower_stats(thorPower);
		thor.setEnabled(true);
		thor.setCreated_at(new Date(2020, 03, 15));
		thor.setCreated_at(new Date(2020, 03, 15));
		return thor;
	}

	@Test
	public void comparedHeroes() {
		Hero hero = this.createPantera();
		Hero hero1 = createThor();
		Hero hero2 = new HeroCompared().getHeroCompared(hero, hero1);
		assertEquals("Thor", hero2.getName());
		assertEquals(Race.DIVINE.name(), hero2.getRace());
	}
}
