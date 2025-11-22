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


/*
 * @Data アノテーション
 *
 * 【役割】
 * ・Lombokが自動で以下を生成してくれる
 *   - 全フィールドの getter
 *   - 全フィールドの setter
 *   - toString()
 *   - equals(), hashCode()
 *
 * 【メリット】
 * ・フィールドだけ書けばよく、ボイラープレート（同じような定型コード）を省略できる
 * ・クラスが「ただのデータの入れ物（エンティティ）」であることがわかりやすい
 */
@Data

/*
 * クラス宣言
 *
 * 【基本形】
 * public class クラス名 {
 *   フィールド定義...
 * }
 *
 * ・public class Member {
 *   = Member という名前のクラス
 *   = membersテーブルの「1行分のデータ」を表現するエンティティクラス
 */
public class Member {

  /*
   * フィールド定義
   *
   * 【基本形】
   * private 型 フィールド名;
   *
   * ・private
   *   = クラスの外から直接触れないようにする（カプセル化）
   *   = 外からは getter/setter 経由でアクセスさせる
   *
   * ▼ 各フィールドの意味
   *
   * private Integer id;
   *   = 主キーID
   *   = DB: id INT PRIMARY KEY AUTO_INCREMENT
   *
   * private String name;
   *   = メンバーの名前
   *   = DB: name VARCHAR(50)
   *
   * private Integer uniformNumber;
   *   = 背番号・ユニフォーム番号
   *   = DB: uniform_number INT
   *   ※ DBのカラム名（uniform_number）と
   *      Javaのフィールド名（uniformNumber）は
   *      スネークケース ⇔ キャメルケースの関係
   *
   * private LocalDate joinDate;
   *   = 参加日・入会日など「日付だけ」の情報
   *   = DB: join_date DATE
   *
   * private LocalDateTime createdAt;
   *   = レコードが「作成された日時」
   *   = DB: created_at DATETIME DEFAULT CURRENT_TIMESTAMP
   *
   * private LocalDateTime updatedAt;
   *   = レコードが「最後に更新された日時」
   *   = DB: updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP

   **Postmanの出力 = Entityのフィールド名

   */

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


/*
 * ◆ 原則：テーブルとエンティティ（Member）の対応
 *
 * 【基本イメージ】
 * CREATE TABLE members (
 *   id             INT,
 *   name           VARCHAR(50),
 *   uniform_number INT,
 *   join_date      DATE,
 *   created_at     DATETIME,
 *   updated_at     DATETIME
 * );
 *
 * public class Member {
 *   private Integer id;
 *   private String  name;
 *   private Integer uniformNumber;
 *   private LocalDate joinDate;
 *   private LocalDateTime createdAt;
 *   private LocalDateTime updatedAt;
 * }
 *
 * → 「1カラム ⇔ 1フィールド」で対応させるのが基本。
 */


/*
 * ◆ 1. カラム名とフィールド名
 *
 * ・英単語の「意味」は合わせる
 *   DB: uniform_number   → Java: uniformNumber
 *   DB: join_date        → Java: joinDate
 *
 * ・DBは snake_case（uniform_number）
 *   Javaは camelCase（uniformNumber）
 *   というように「書き方のルール」は違ってOK。
 *
 * ・MyBatis / JPA などで
 *   「どのカラム ↔ どのフィールド」を
 *   マッピングしてあげれば名前は多少違っても大丈夫。
 */

/*
 * ◆ 2. 型の対応（とても大事）
 *
 * ・INT        ↔ Integer / int
 * ・VARCHAR    ↔ String
 * ・DATE       ↔ LocalDate
 * ・DATETIME   ↔ LocalDateTime
 *
 * ・型の“意味”はそろえること。
 *   例：DATEなのに Java側で String にしてしまうと面倒が増える。
 *
 * ・NOT NULL のカラムは
 *   → できれば Java側も null にならない設計にするか、
 *      バリデーションで必須チェックする。
 */

/*
 * ◆ 3. NULL とラッパー型の注意
 *
 * ・DBで NULL がありうるカラム
 *   → Java側は ラッパー型（Integer / Long / Boolean など）を使う
 *
 *   例：uniform_number INT  （NULL許可）
 *       → private Integer uniformNumber;
 *
 * ・プリミティブ型（int / boolean）は null を持てないので、
 *   「NULLかもしれない」カラムには使わない方が安全。
 */

/*
 * ◆ 4. 自動採番・自動日時カラムの扱い
 *
 * ・id INT PRIMARY KEY AUTO_INCREMENT
 *   → INSERT時は Java側のidは null のままにして、
 *     DBにおまかせすることが多い。
 *
 * ・created_at / updated_at
 *   DATETIME DEFAULT CURRENT_TIMESTAMP
 *   ON UPDATE CURRENT_TIMESTAMP
 *
 *   → INSERT/UPDATE SQL では、わざとカラム指定しないで
 *     DBに自動で入れてもらうパターンが多い。
 *   → SELECTしたときに、Java側の createdAt / updatedAt に値が入る。
 */

/*
 * ◆ 【参考】 なぜ int ではなく Integer を使うのか
 *
 * private Integer uniformNumber;
 *
 * 【結論】
 * ・一番大きな理由は
 *   「null を入れられるようにするため」。
 *
 * 【ポイント】
 * ・int（プリミティブ型）
 *   - null を入れられない（必ず0, 1, 2…のどれか）
 *   - フィールドの初期値は勝手に 0 になる
 *
 * ・Integer（ラッパー型）
 *   - オブジェクトなので null を入れられる
 *   - 「値が入っていない状態」と「0」を区別できる
 *
 * 【DBとの関係】
 * ・DBのカラムが NULL を許可する場合：
 *
 *   uniform_number INT  -- NULL可
 *
 *   → Java側で int にしてしまうと
 *     - DBがNULLなのに、Java側では 0 になってしまう
 *     - 「本当に背番号0なのか？ まだ決まってないだけか？」が判別できない
 *
 * ・Integer なら：
 *   - DBがNULL → Javaも null
 *   - 「まだ未設定」という状態を表現できる
 *
 * → だから、DBと連携するエンティティでは
 *    int より Integer を使うことが多い。
 */


/*
 * ◆ 【参考】 カラム（column）とは？
 *
 * 【イメージ】
 * ・テーブル（表）の「縦1列」のこと。
 *   Excelでいうと、「A列」「B列」のようなもの。
 *
 * 【例：membersテーブル】
 *
 * CREATE TABLE members (
 *   id             INT,
 *   name           VARCHAR(50),
 *   uniform_number INT,
 *   join_date      DATE
 * );
 *
 * ・id             → 1つの「カラム」
 * ・name           → 1つの「カラム」
 * ・uniform_number → 1つの「カラム」
 * ・join_date      → 1つの「カラム」
 *
 * 【用語整理】
 * ・テーブル（table）
 *   = 全体の「表」
 *
 * ・カラム（column）
 *   = 表の「縦の項目」
 *   = 「この列は何の情報を持つか」を決めるもの
 *   （id列・name列・uniform_number列…）
 *
 * ・行（row, レコード）
 *   = 表の横1行分
 *   = 1人の会員・1件のデータなど
 *
 * → 「カラム = 項目名の列」
 *    「行 = 1件分のデータ」
 */

/*
 * ◆ 【参考】 DBテーブル側の「nullを入れられない」
 *
 * 例：
 *   uniform_number INT          -- NULL許可
 *   name           VARCHAR(50) NOT NULL  -- NULL禁止
 *
 * ・NOT NULL がついているカラム
 *   = 「この列には必ず値を入れてください。
 *       NULLはダメです」というルール。
 *
 * ・NOT NULL がないカラム
 *   = NULLを入れてもOK。
 *
 * 【ポイント】
 * ・DBテーブルが「nullを入れてよいかどうか」は
 *   → DDL（CREATE TABLE）の定義で決まる。
 *
 * ここまでは「DB側の事情」。
 */


/*
 * ◆ 【参考】 Java側の「nullを入れられない / 入れられる」
 *
 * 【プリミティブ型（int, boolean など）】
 * ・int uniformNumber;
 *   → この変数には null は入れられない。
 *   → 「0, 1, 2…」のような値しか持てない。
 *
 * 【ラッパー型（Integer, Boolean など）】
 * ・Integer uniformNumber;
 *   → この変数には null を入れられる。
 *   → 値がない状態を null で表現できる。
 *
 * → ここは「Java言語の仕様」の問題。
 */
