package com.github.lkqm.spring.api.version.integration;

import com.github.lkqm.spring.api.version.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, properties = {
        "api.version.type=uri",
        "api.version.uri-location=end",
})
@AutoConfigureMockMvc
public class UserControllerUrIEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listV1() throws Exception {
        mockMvc.perform(get("/user/list/v1"))
                .andExpect(status().isOk())
                .andExpect(content().string("list1"));
    }

    @Test
    public void listV2() throws Exception {
        mockMvc.perform(get("/user/list/v1.1"))
                .andExpect(status().isOk())
                .andExpect(content().string("list2"));
    }

}
