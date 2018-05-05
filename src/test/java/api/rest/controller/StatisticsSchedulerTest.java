/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package api.rest.controller;

import api.rest.entity.Transaction;
import api.rest.utils.Config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatisticsSchedulerTest {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void createMultipleTransactionDifferentTimeStamp() throws Exception {

        Transaction transaction = new Transaction(10.0, System.currentTimeMillis());
        this.mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(transaction))).andExpect(status().isCreated());

        Thread.sleep(30000);

        Transaction transaction1 = new Transaction(20.0, System.currentTimeMillis());
        this.mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(transaction1))).andExpect(status().isCreated());
        // Sleeping the thread for 60 Secs so that transaction object will get removed from transactionDB
        Thread.sleep(60000);
        this.mockMvc.perform(get("/statistics")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("count", is(1)))
                .andExpect(jsonPath("sum", is(20.0)))
                .andExpect(jsonPath("max", is(20.0)))
                .andExpect(jsonPath("min", is(20.0)))
                .andExpect(jsonPath("average", is(20.0)));



    }
}
