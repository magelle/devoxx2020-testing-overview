package devoxxfr2020.cashregister.bdd;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CashRegisterResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/receipt")
                .content("[\n" +
                        "  {\n" +
                        "    \"name\": \"Pommes\",\n" +
                        "    \"quantity\": 2\n" +
                        "  },\n" +
                        "{\n" +
                        "    \"name\": \"Bananes\",\n" +
                        "    \"quantity\": 2\n" +
                        "  }\n" +
                        "]")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultDOW = result.getResponse().getContentAsString();

        assertNotNull(resultDOW);
        assertEquals("{" +
                "\"items\":[" +
                    "{\"fruit\":\"Pommes\",\"quantity\":2,\"total\":200}," +
                    "{\"fruit\":\"Bananes\",\"quantity\":2,\"total\":150}" +
                "]," +
                "\"discounts\":[]," +
                "\"total\":350" +
                "}", resultDOW);
     }

}