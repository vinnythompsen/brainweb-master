package br.com.brainweb.interview.core.features.hero;


import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Date;
import java.util.UUID;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HeroControllerTest {
    @Autowired
    private PowerStatsService statsServices;
    @Autowired
    private HeroService heroService;
    @Autowired
    private MockMvc mockMvc;
    private static PowerStats stats;
    private static Hero hero1;
    @Test
    public void testControllerHeroA() throws Exception{
        PowerStats model = new PowerStats();
        model.setStrength(6);
        model.setAgility(4);
        model.setDexterity(3);
        model.setIntelligence(4);
        model.setCreated_at(new Date(2020, 04,04));
        model.setUpdated_at(new Date(2020,02,04));
        String json = StrJson.getJson(model);
        String strJson;
        strJson = mockMvc.perform(MockMvcRequestBuilders.post("/stats")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strength", is(6)))
                .andExpect(jsonPath("$.agility", is(4)))
                .andExpect(jsonPath("$.dexterity", is(3)))
                .andExpect(jsonPath("$.intelligence", is(4)))
                .andReturn().getResponse().getContentAsString();
        ObjJson<PowerStats> obj = new ObjJson<>();
        stats = obj.getObject(strJson, new PowerStats());
    }

    @Test
    public void testControllerHeroB() throws Exception{
        Hero hero = new Hero();
        hero.setPower_stats(stats);
        hero.setName("Volverine");
        hero.setRace(Race.HUMAN.name());
        hero.setEnabled(true);
        hero.setCreated_at(new Date(2020,9,04));
        hero.setUpdated_at(new Date(2020,9,04));
        String json = StrJson.getJson(hero);
        String strJson = mockMvc.perform(MockMvcRequestBuilders.post("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.power_stats.strength", is(6)))
                .andExpect(jsonPath("$.power_stats.agility", is(4)))
                .andExpect(jsonPath("$.power_stats.dexterity", is(3)))
                .andExpect(jsonPath("$.power_stats.intelligence", is(4)))
                .andExpect(jsonPath("$.race", is(Race.HUMAN.name())))
                .andExpect(jsonPath("$.name", is("Volverine")))
                .andReturn().getResponse().getContentAsString();
        ObjJson<Hero> objJson =new ObjJson<>();
        hero1 = objJson.getObject(strJson, new Hero());
    }

    @Test
    public void testControllerHeroC() throws Exception{
        hero1.setName("Volverine Logan");
        hero1.setRace(Race.HUMAN.name());
        hero1.setEnabled(true);
        hero1.setCreated_at(new Date(2020,9,04));
        hero1.setUpdated_at(new Date(2020,9,04));
        String json = StrJson.getJson(hero1);
        mockMvc.perform(MockMvcRequestBuilders.put("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.power_stats.strength", is(6)))
                .andExpect(jsonPath("$.power_stats.agility", is(4)))
                .andExpect(jsonPath("$.power_stats.dexterity", is(3)))
                .andExpect(jsonPath("$.power_stats.intelligence", is(4)))
                .andExpect(jsonPath("$.race", is(Race.HUMAN.name())))
                .andExpect(jsonPath("$.name", is("Volverine Logan")));
    }
    @Test
    public void testControllerHeroD() throws Exception{
        UUID id = UUID.randomUUID();
        Hero hero = new Hero();
        hero.setId(id);
        mockMvc.perform(MockMvcRequestBuilders.get("/hero/"+hero.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testControllerHeroF() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/findByName".concat("/").concat(hero1.getName()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testControllerHeroG() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void testControllerHeroH() throws Exception{
        Hero hero = getHero();
        mockMvc.perform(MockMvcRequestBuilders.get("/hero".concat("/").concat(hero1.getId().toString()
        ).concat("/").concat(hero.getId().toString()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Volverine Logan")));
    }
    @Test
    public void testControllerHeroI() throws Exception{
        String json = StrJson.getJson(hero1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/hero")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    private Hero getHero(){
        PowerStats stats = new PowerStats();
        stats.setStrength(6);
        stats.setAgility(3);
        stats.setDexterity(3);
        stats.setIntelligence(3);
        stats.setCreated_at(new Date(2020, 04,04));
        stats.setUpdated_at(new Date(2020,02,04));
        PowerStats s = statsServices.save(stats);
        Hero hero = new Hero();
        hero.setPower_stats(s);
        hero.setName("Iron Man");
        hero.setEnabled(true);
        hero.setRace(Race.HUMAN.name());
        hero.setCreated_at(new Date(2020,9,29));
        hero.setUpdated_at(new Date(2020,9,20));
        return heroService.save(hero);
    }
}
