/*
 * package宣言
 *
 * 【基本形】
 * package パッケージ名;
 *
 * ・package com.example.volleyball.entity;
 *   = このクラスが所属する「住所」のようなもの
 *   = フォルダ構成と対応する（例：src/main/java/com/example/volleyball/entity）
 *   = 他のクラスから「どこにあるクラスか」を区別するためのグループ名
 */
package com.example.volleyball.entity;

/*
 * import文
 *
 * 【基本形】
 * import パッケージ名.クラス名;
 *
 * ・import lombok.Data;
 *   = Lombokライブラリの Data アノテーションを使うための宣言
 *
 * ・import java.time.LocalDate;
 *   = 日付（年月日だけ）を扱うクラスを使う宣言
 *     → DBの DATE 型 と対応させるイメージ
 *
 * ・import java.time.LocalDateTime;
 *   = 日付＋時刻 を扱うクラスを使う宣言
 *     → DBの DATETIME / TIMESTAMP と対応させるイメージ
 */

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
}
