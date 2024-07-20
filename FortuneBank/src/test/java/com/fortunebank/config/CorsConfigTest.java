package com.fortunebank.config;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortunebank.user.dto.UserLoginDto;
import com.fortunebank.user.model.NetBankingUser;
import com.fortunebank.user.service.NetBankingService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CorsConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NetBankingService netBankingService;

    @Test
    public void testCorsHeaders() throws Exception {
        mockMvc.perform(get("/api/transaction/get/1")
                .header("Origin", "http://localhost:3000"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
    }

    @Test
    public void testCorsDisallowedOrigin() throws Exception {
        mockMvc.perform(get("/api/transaction/get/1")
                .header("Origin", "http://disallowed-origin.com"))
                .andExpect(status().isForbidden()) // 403 for disallowed origin
                .andExpect(header().doesNotExist("Access-Control-Allow-Origin"));
    }

    @Test
    public void testCorsPostRequest() throws Exception {
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setUserid("testUser");
        userLoginDto.setPassword("testPassword");

        NetBankingUser netBankingUser = new NetBankingUser();
        netBankingUser.setLoginPassword("testPassword");

        when(netBankingService.loginGetUser(userLoginDto.getUserid())).thenReturn(netBankingUser);

        mockMvc.perform(post("/api/login")
                .header("Origin", "http://localhost:3000")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userLoginDto)))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
    }

}
