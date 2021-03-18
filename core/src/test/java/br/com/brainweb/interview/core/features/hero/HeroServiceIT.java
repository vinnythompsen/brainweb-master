package br.com.brainweb.interview.core.features.hero;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsRepository;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;




@ActiveProfiles("it")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HeroServiceIT {
	
	@Autowired
	private PowerStatsRepository repository;
	
	@Autowired
	private HeroRepository heroRepository;

	@Test
	public void createHero() {
		PowerStats model = new PowerStats();
		model.setStrength(4);
		model.setAgility(4);
		model.setDexterity(3);
		model.setIntelligence(4);
		model.setCreated_at(new Date(2020, 03, 15));
		model.setUpdated_at(new Date(2020, 03, 16));
		PowerStats stats = repository.save(model);
		Hero hero = new Hero();
		hero.setName("Iron Man");
		hero.setPower_stats(stats);
		hero.setRace(Race.HUMAN.name());
		hero.setEnabled(true);
		hero.setCreated_at(new Date(2020, 03, 15));
		hero.setUpdated_at(new Date(2020, 03, 16));
		heroRepository.save(hero);
	}
}
