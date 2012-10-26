package controllers

import play.api._
import play.api.db._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.User
import anorm.NotAssigned

object UserController extends Controller {

  val userForm: Form[User] = Form(
    // Userフォームマッピング
    mapping(
      "name" -> text(minLength = 4),
      "email" -> email,
      "password" -> tuple("main" -> text(minLength = 6), "confirm" -> text).verifying(
        // パスワードの入力ルール定義
        "Passwords don't match", passwords => passwords._1 == passwords._2)) {
      // Userフォームバインド
      (name, email, passwords) => User(NotAssigned, name, email, passwords._1)
    } {
      // Usetフォームアンバインド
      user => Some(user.name, user.email, (user.password, ""))
    }.verifying(
      "This username is not available",
      user => !Seq("admin", "guest").contains(user.name)))

  /**
   * ユーザー登録画面初期処理
   */
  def initEntry = Action { implicit request =>
    Ok(views.html.entry(userForm))
  }

  /**
   * ユーザー登録処理.(入力エラーがなければそのまま返す)
   */
  def submitEntry = Action { implicit request =>
    userForm.bindFromRequest.fold(
      errors => BadRequest(views.html.entry(errors)),
      user => {
        Ok(views.html.result(user))
      })
  }
}