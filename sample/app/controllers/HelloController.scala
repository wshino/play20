package controllers

import play.api.mvc._

object HelloController extends Controller {

  // 文字コードを指定する場合はCodec を暗黙の値として定義する
  //  implicit val myCustomCharset = Codec.javaSupported("Windows-31J")

  def index(name: String) = Action {
    println(name)
    Ok(<p>name</p>).as(HTML)
  }

//  def index = Action {
//    Ok(<p>こんちはす.</p>).as("text/html; charset=Windows-31J") // 直接 content-type を指定する場合
//  }

  def list(page: Int) = Action {
    println(page)
    Ok("list action").as(HTML)
  }


  def show(id: String) = Action {
    println(id)
//    Ok("show action").as(HTML)
    Ok(views.html.routing())
  }

  def test1 = Action {
    BadRequest("bad request")
  }

  def test2 = Action {
    Redirect("http://ameba.jp")
  }


}