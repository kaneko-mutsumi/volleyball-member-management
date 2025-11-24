package com.example.volleyball.entity;


import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Member {

  private Integer id;

  @NotBlank(message = "名前は必須です")
  private String name;

  @Min(value = 1, message = "背番号は1以上")
  @Max(value = 20, message = "背番号は20以下")
  private Integer uniformNumber;

  private LocalDate joinDate;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private Boolean deleted;
}
