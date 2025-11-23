package com.example.volleyball.service;

import com.example.volleyball.entity.Member;
import java.util.List;
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
    member.setName("月島蛍");
    member.setUniformNumber(11);
    member.setJoinDate(LocalDate.of(2025, 4, 1));

    assertDoesNotThrow(() -> memberService.registerMember(member));
  }

  @Test
  void トランザクション_ロールバック確認() {
    int sizeBefore = memberService.getAllMembers().size();

    assertThrows(Exception.class, () -> {
      Member member1 = new Member();
      member1.setName("田中太郎");
      member1.setUniformNumber(99);
      memberService.registerMember(member1);

      Member member2 = new Member();
      member2.setName("鈴木次郎");
      member2.setUniformNumber(99);  // 同じ背番号
      memberService.registerMember(member2);
    });

    int sizeAfter = memberService.getAllMembers().size();
    assertEquals(sizeBefore, sizeAfter);
  }
}