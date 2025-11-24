package com.example.volleyball.controller;

import com.example.volleyball.entity.Member;
import com.example.volleyball.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/view/members")
public class MemberViewController {

  private final MemberService memberService;

  public MemberViewController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public String list(Model model) {
    List<Member> members = memberService.getAllMembers();
    model.addAttribute("members", members);
    return "member/list";
  }

  @GetMapping("/new")
  public String newForm(Model model) {
    model.addAttribute("member", new Member());
    return "member/form";
  }

  @PostMapping
  public String create(@Validated @ModelAttribute Member member,
      BindingResult result) {
    if (result.hasErrors()) {
      return "member/form";
    }
    memberService.registerMember(member);
    return "redirect:/view/members";
  }

  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable Integer id, Model model) {
    Member member = memberService.getAllMembers().stream()
        .filter(m -> m.getId().equals(id))
        .findFirst()
        .orElseThrow();
    model.addAttribute("member", member);
    return "member/edit";
  }

  @PostMapping("/{id}")
  public String update(@PathVariable Integer id,
      @Validated @ModelAttribute Member member,
      BindingResult result) {
    if (result.hasErrors()) {
      return "member/edit";
    }
    member.setId(id);
    memberService.updateMember(member);
    return "redirect:/view/members";
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Integer id) {
    memberService.deleteMember(id);
    return "redirect:/view/members";
  }
}