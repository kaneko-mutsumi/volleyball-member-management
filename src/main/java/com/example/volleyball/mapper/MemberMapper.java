/*
 * package宣言
 *
 * 【基本形】
 * package パッケージ名;
 *
 * ・package com.example.volleyball.mapper;
 *   = このファイル（MemberMapper）が所属するグループ名
 *   = フォルダ構成と対応（例：src/main/java/com/example/volleyball/mapper）
 *   = 「これは mapper 層のクラスですよ」という目印にもなっている
 */
package com.example.volleyball.mapper;


/*
 * import文
 *
 * 【基本形】
 * import パッケージ名.クラス名;
 *
 * ・import com.example.volleyball.entity.Member;
 *   = 同じプロジェクト内の Member エンティティクラスを使う宣言
 *   = このMapperが「membersテーブルの1行分（Member）を扱う」ことを表す
 *
 * ・import org.apache.ibatis.annotations.Mapper;
 *   = MyBatis の @Mapper アノテーションを使う宣言
 *   = 「このインターフェースは MyBatis の Mapper です」とSpringに教えるために必要
 *
 * ・import java.util.List;
 *   = List 型（複数件のデータの入れ物）を使うための宣言
 */
import com.example.volleyball.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/*
 * @Mapper アノテーション
 * MyBatis のアノテーション
 *
 * 【役割】
 * ・このインターフェースが「MyBatisのMapper（DBアクセス用の窓口）」であることを示す。
 *
 * ・Spring Boot と一緒に使うとき：
 *   - @Mapper が付いたインターフェースを自動でスキャンして
 *   - 実装クラス（Proxy）を自動生成してくれる
 *
 * 【イメージ】
 * ・自分で
 *   public class MemberMapperImpl implements MemberMapper { ... }
 *   と書かなくても、
 *   MyBatis + Spring が「中身」を作ってくれるためのマーク。
 */
@Mapper

/*
 * インターフェース宣言（MemberMapper）
 *
 * 【基本形】
 * public interface インターフェース名 {
 *     メソッド定義...（中身は書かない）
 * }
 *
 * ・public interface MemberMapper {
 *   = DBの members テーブルにアクセスするための「窓口インターフェース」
 *   = DAO層（データアクセス層）の役割を持つ。
 *
 * 【ポイント】
 * ・interface なので、ここには「処理の中身」は書かない。
 * ・ここで定義したメソッドと、
 *   対応するSQL（XMLやアノテーション）がMyBatisで結びつけられる。
 */
public interface MemberMapper {


  /*
   * メソッド① findAll
   *
   * 【定義】
   *   List<Member> findAll();
   *
   * 【基本形】
   *   戻り値の型 メソッド名();
   *
   * ・List<Member>
   *   = 戻り値の型　
   * 　→Memberクラス名と同じにする
   *   DBから取ってくる1行分の形が Member クラスだから、その型名を戻り値にしている
   *
   *   = Memberオブジェクトを複数まとめたリスト
   *   = DBの「複数行」の結果を表す
   *
   * ・findAll
   *   = メソッド名
   *   = 「全件取得する」イメージの名前
   *
   * 【意味】
   * ・membersテーブルの全レコードを取得し、
   *   1行1行をMemberにマッピングして List<Member> にして返す想定。
   */
  List<Member> findAll();


  /*
   * メソッド② findById
   *
   * 【定義】
   *   Member findById(Integer id);
   *
   * 【基本形】
   *   戻り値の型 メソッド名(引数の型 引数名);
   *
   * ・Member
   *   = 戻り値の型
   *   = 1件分のMemberデータ
   *
   * ・findById
   *   = 「主キーidで1件検索する」イメージの名前
   *
   * ・(Integer id)
   *   = 検索条件として渡す引数
   *   = DBの WHERE id = ? に対応する値
   *
   * 【意味】
   * ・指定されたidに一致する membersテーブルの1行を
   *   Memberオブジェクトとして返すメソッド。
   * ・見つからない場合は null を返したり、例外にしたり、
   *   そこは実装や設計次第。
   */
  Member findById(Integer id);

  /*
   * メソッド③ insert
   *
   * 【定義】
   *   void insert(Member member);
   *
   * 【基本形】
   *   戻り値の型 メソッド名(引数の型 引数名);
   *
   * ・void
   *   = 戻り値なし
   *   = 「処理はするが、何も返さない」メソッド
   *
   * ・insert
   *   = 「新規登録する」イメージの名前
   *
   * ・(Member member)
   *   = 登録したいデータを詰めた Memberオブジェクト
   *   = DBの INSERT 文の VALUES 部分の元ネタになる
   *
   * 【意味】
   * ・Memberのフィールド（name, uniformNumber, joinDate など）を使って
   *   membersテーブルに1行 INSERT する処理を表現するメソッド。
   *
   * ・実際の SQL は、対応する Mapper XML などに
   *   <insert id="insert">...</insert>
   *   のように書く。
   */
    void insert(Member member);
}