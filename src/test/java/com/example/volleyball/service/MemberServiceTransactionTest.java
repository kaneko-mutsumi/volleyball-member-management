package com.example.volleyball.service;

import com.example.volleyball.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTransactionTest {

  @Autowired
  private MemberService memberService;

  @Test
  void トランザクション_正常登録() {
    Member member = new Member();
    member.setName("日向翔陽");
    member.setUniformNumber(10);
    member.setJoinDate(LocalDate.of(2025, 4, 1));

    assertDoesNotThrow(() -> memberService.registerMember(member));
  }
}