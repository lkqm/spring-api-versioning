package com.github.lkqm.spring.api.version.integration;

import com.github.lkqm.spring.api.version.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class, properties = {
        "api.version.type=header",
        "api.version.header=X-API-VERSION"
})
@AutoConfigureMockMvc
public class UserControllerHeaderTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listV1() throws Exception {
        mockMvc.perform(get("/user/list").header("X-API-VERSION", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("list1"));
    }

    @Test
    public void listV2() throws Exception {
        mockMvc.perform(get("/user/list").header("X-API-VERSION", "1.1"))
                .andExpect(status().isOk())
                .andExpect(content().string("list2"));
    }

}
