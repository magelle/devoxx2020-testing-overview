package devoxxfr2020.cashregister.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CashRegisterResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testing_receipt() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/receipt")
                .content("[\n" +
                        "  {\n" +
                        "    \"name\": \"Pommes\",\n" +
                        "    \"quantity\": 5\n" +
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

        Assertions.assertNotNull(resultDOW);
        Assertions.assertEquals("{" +
                "\"items\":[" +
                "{\"fruit\":\"Pommes\",\"quantity\":5,\"total\":500}," +
                "{\"fruit\":\"Bananes\",\"quantity\":2,\"total\":150}" +
                "]," +
                "\"discounts\":[{\"name\":\"More than 4 Apples\",\"amount\":100},{\"name\":\"More than 5 fruits\",\"amount\":200}]," +
                "\"total\":350" +
                "}", resultDOW);
    }

}