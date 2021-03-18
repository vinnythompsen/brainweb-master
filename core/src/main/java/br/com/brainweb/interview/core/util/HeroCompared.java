package br.com.brainweb.interview.core.util;

import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;

public class HeroCompared {
    public Hero getHeroCompared(Hero hero, Hero hero1){
        if(sumPower(hero) > sumPower(hero1))
            return hero;
        else
            return hero1;
    }
    private Integer sumPower(Hero hero){
        PowerStats stats = hero.getPower_stats();
        return stats.getAgility()+stats.getDexterity()+stats.getIntelligence()+stats.getStrength();
    }
}
