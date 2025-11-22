package com.example.volleyball.mapper;

import com.example.volleyball.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface MemberMapper {

  List<Member> findAll();

  Member findById(Integer id);

  void insert(Member member);

  void update(Member member);

  void delete(Integer id);
}