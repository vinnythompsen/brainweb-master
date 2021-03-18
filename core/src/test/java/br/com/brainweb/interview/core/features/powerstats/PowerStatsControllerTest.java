package br.com.brainweb.interview.core.features.powerstats;
import br.com.brainweb.interview.core.features.hero.StrJson;
import br.com.brainweb.interview.model.PowerStats;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.core.Is.is;
import java.util.Date;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PowerStatsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testControllerPowerStats() throws Exception{
        PowerStats model = new PowerStats();
        model.setStrength(6);
        model.setAgility(4);
        model.setDexterity(3);
        model.setIntelligence(4);
        model.setCreated_at(new Date(2020, 04,04));
        model.setUpdated_at(new Date(2020,02,04));
        String json = StrJson.getJson(model);
        mockMvc.perform(MockMvcRequestBuilders.post("/stats")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.strength", is(6)))
                .andExpect(jsonPath("$.agility", is(4)))
                .andExpect(jsonPath("$.dexterity", is(3)))
                .andExpect(jsonPath("$.intelligence", is(4)));
    }
}
