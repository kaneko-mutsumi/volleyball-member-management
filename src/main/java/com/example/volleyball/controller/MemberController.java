package com.example.volleyball.controller;

import com.example.volleyball.entity.Member;
import com.example.volleyball.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/members")
@Tag(name = "部員管理", description = "バレー部の部員情報を管理するAPI")
public class MemberController {

  private final MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }


  @GetMapping
  @Operation(summary = "部員一覧取得", description = "全部員の情報を取得します")
  public List<Member> getAllMembers() {
    return memberService.getAllMembers();
  }

  @PostMapping
  @Operation(summary = "部員登録", description = "新しい部員を登録します")
  public String registerMember(@Validated @RequestBody Member member) {
    memberService.registerMember(member);
    return "登録成功";
  }

  @PutMapping("/{id}")
  @Operation(summary = "部員更新", description = "部員情報を更新します")    public String updateMember(@PathVariable Integer id,
        @Validated @RequestBody Member member) {
      member.setId(id);
      memberService.updateMember(member);
      return "更新成功";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "部員削除", description = "部員を削除します（論理削除）")
    public String deleteMember(@PathVariable Integer id) {
      memberService.deleteMember(id);
      return "削除成功";
    }
  }