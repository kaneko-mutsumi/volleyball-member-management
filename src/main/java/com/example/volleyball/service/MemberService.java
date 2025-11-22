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

  public void updateMember(Member member) {  // 追加
    memberMapper.update(member);
  }

  public void deleteMember(Integer id) {  // 追加
    memberMapper.delete(id);
  }
}