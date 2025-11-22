package com.example.volleyball.controller;

import com.example.volleyball.entity.Member;
import com.example.volleyball.service.MemberService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public List<Member> getAllMembers() {
    return memberService.getAllMembers();
  }

  @PostMapping
  public String registerMember(@Validated @RequestBody Member member) {
    memberService.registerMember(member);
    return "登録成功";
  }

    @PutMapping("/{id}")
    public String updateMember(@PathVariable Integer id,
        @Validated @RequestBody Member member) {
      member.setId(id);
      memberService.updateMember(member);
      return "更新成功";
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Integer id) {
      memberService.deleteMember(id);
      return "削除成功";
    }
  }