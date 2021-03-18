package br.com.brainweb.interview.core.features.powerstats;

import br.com.brainweb.interview.model.PowerStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerStatsService {
    @Autowired
    private PowerStatsRepository repository;
    public PowerStats save(PowerStats entity){
        return repository.save(entity);
    }
}
