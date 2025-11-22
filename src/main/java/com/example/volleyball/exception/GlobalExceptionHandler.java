/*
 * 【クラスの基本形】
 * public class クラス名 { ... }
 *
 * @RestControllerAdvice
 * = 全ての @RestController に共通する
 *   例外処理を書くクラスですよ、という印。
 */

package com.example.volleyball.exception;



import java.util.LinkedHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {


  /*
   * 【メソッドの基本形】
   * public 戻り値の型 メソッド名(引数の型 引数名) { ... }
   *
   * @ExceptionHandler(例外クラス.class)
   * = このメソッドが「その例外」を処理する、と指定。
   *
   * 戻り値の型：ResponseEntity<Map<String, String>>
   * ・ResponseEntity<ボディの型>
   *   = HTTPレスポンス全体（ボディ＋ステータス）を表す。
   * ・Map<String, String>
   *   = 「キー：String」「値：String」のマップ。
   *     ここでは「フィールド名 → エラーメッセージ」。
   *
   * 引数：MethodArgumentNotValidException ex
   * ・実際に発生した例外オブジェクト。
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
      public ResponseEntity<Map<String, String>> handleValidationErrors(
      MethodArgumentNotValidException ex) {

    /*
     * 【Mapの基本形】
     * Map<キーの型, 値の型> 変数名 = new 実装クラス<>();
     *
     * ・Map<String, String>
     *   = 「キーも値も文字列」のマップ。
     * ・errors
     *   = 変数名。
     * ・new LinkedHashMap<>()
     *   = 順序を保つMapの実装クラスを生成。
     */
    Map<String, String> errors = new LinkedHashMap<>();  // 順序保持

    /*
     * 【ラムダ＋forEach の基本形】
     * コレクション.forEach(要素 -> { 処理 });
     *
     * error -> { ... }
     * = 各 error に対して { } の中を実行。
     *
     * (FieldError) error
     * = 型キャスト。
     *
     * errors.put(key, value);
     * = Map に 1件登録。
     */
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    /*
     * 【ResponseEntity の基本形】
     * return new ResponseEntity<>(ボディ, ステータス);
     *
     * ・errors
     *   = レスポンスボディ（Map<String, String>）
     * ・HttpStatus.BAD_REQUEST
     *   = ステータスコード 400
     */
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
}