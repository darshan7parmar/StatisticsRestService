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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import api.rest.entity.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTests {

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
    public void createSuccessTransaction() throws Exception {

        Transaction transaction = new Transaction(10.10,System.currentTimeMillis());

        this.mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(transaction))).andExpect(status().isCreated());
    }

    @Test
    public void createWrongContentType() throws Exception {

        Transaction transaction = new Transaction(10.10,System.currentTimeMillis());

        this.mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_XML).
                content(asJsonString(transaction))).andExpect(status().is4xxClientError());
    }

    @Test
    public void createExpiredTransaction() throws Exception {

        Transaction transaction = new Transaction(10.10,System.currentTimeMillis()-61000);

        this.mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(transaction))).andExpect(status().isNoContent());
    }

    @Test
    public void createFutureTransaction() throws Exception {

        Transaction transaction = new Transaction(10.10,System.currentTimeMillis()+60000);

        this.mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON).
                content(asJsonString(transaction))).andExpect(status().isNoContent());
    }
}
