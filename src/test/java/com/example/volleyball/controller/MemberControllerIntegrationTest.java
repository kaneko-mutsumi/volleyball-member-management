package com.example.volleyball.controller;

import com.example.volleyball.entity.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MemberControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void 部員登録_正常() throws Exception {
    Member member = new Member();
    member.setName("日向翔陽");
    member.setUniformNumber(10);
    member.setJoinDate(LocalDate.of(2025, 4, 1));

    mockMvc.perform(post("/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(member)))
        .andExpect(status().isOk());
  }

  @Test
  void 部員登録_名前空欄でエラー() throws Exception {
    Member member = new Member();
    member.setName("");
    member.setUniformNumber(10);

    mockMvc.perform(post("/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(member)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.name").value("名前は必須です"));
  }

  @Test
  void 部員登録_背番号範囲外でエラー() throws Exception {
    Member member = new Member();
    member.setName("日向翔陽");
    member.setUniformNumber(100);

    mockMvc.perform(post("/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(member)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.uniformNumber").exists());
  }
}