package com.example.volleyball.service;

import com.example.volleyball.entity.Member;
import com.example.volleyball.mapper.MemberMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @Mock
  private MemberMapper memberMapper;

  @InjectMocks
  private MemberService memberService;

  @Test
  void getAllMembers_正常() {
    // テスト実装
    Member member = new Member();
    member.setName("日向翔陽");
    List<Member> expected = Arrays.asList(member);

    when(memberMapper.findAll()).thenReturn(expected);

    List<Member> result = memberService.getAllMembers();

    assertEquals(1, result.size());
    verify(memberMapper, times(1)).findAll();
  }
}