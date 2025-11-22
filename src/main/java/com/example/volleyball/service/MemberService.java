package com.example.volleyball.service;

import com.example.volleyball.entity.Member;
import com.example.volleyball.mapper.MemberMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

  private final MemberMapper memberMapper;

  public MemberService(MemberMapper memberMapper) {
    this.memberMapper = memberMapper;
  }

  public List<Member> getAllMembers() {
    return memberMapper.findAll();
  }

  public void registerMember(Member member) {
    memberMapper.insert(member);
  }
}