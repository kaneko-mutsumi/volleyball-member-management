/*
 * ・このクラスの「住所」
 * ・controllerパッケージ＝「画面／APIの入り口（Web層）」をまとめる場所
 */
package com.example.volleyball.controller;


/*
 * ・レスポンスやリクエストで使う「会員1人分」の型（エンティティ）
 */
import com.example.volleyball.entity.Member;


/*
 * ・ビジネスロジックをまとめたサービス層
 * ・ControllerはServiceに仕事をお願いする
 */
import com.example.volleyball.service.MemberService;


/*
 * ・@RestController, @GetMapping, @PostMapping, @RequestBody など
 *   Spring Web MVC のアノテーションを使うため
 */
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/*
 * ・複数件の Member を返したいので List を使う
 */
import java.util.List;



/*
 * @RestController
 * ・このクラスが「REST APIのコントローラ」であることを示す。
 * ・中のメソッドの戻り値が、そのままHTTPレスポンスのボディになる。
 *   例：List<Member> → JSON にシリアライズされて返る。
 *
 * @RequestMapping("/members")
 * ・このクラスが扱うURLの共通の先頭部分。
 * ・このクラスの全メソッドは「/members」から始まるパスを受け持つ。
 *   例：
 *     @GetMapping   → GET /members
 *     @PostMapping  → POST /members
 */
@RestController
@RequestMapping("/members")

public class MemberController {


  /*
   * ◆ 3. フィールドとコンストラクタ（DI）
   */

  /*
   * ・Service層（MemberService）への参照を持つ。
   * ・Controllerは直接DBに触らず、Serviceに「お願い」する役。
   * ・final：コンストラクタでセットしたら以後変えない。
   */
  private final MemberService memberService;


  /*
   * コンストラクタインジェクション
   * ・Springがアプリ起動時に
   *   「MemberService のインスタンス」をここに渡してくれる。
   * ・this.memberService = memberService;
   *   → フィールドに保存して、メソッド内で使えるようにする。
   */
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
}