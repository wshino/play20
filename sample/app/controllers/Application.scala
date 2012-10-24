package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {
  
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

//  def hello = Action { request =>
//    val params: Map[String, Seq[String]] = request.queryString
//    val name = params("name").head
//
//    Ok(<h1>hello {name}</h1>).as(HTML)
//  }

  def hello = Action { request =>
    // POST
    val formBody: Option[Map[String, Seq[String]]] = request.body.asFormUrlEncoded
    val params: Map[String, Seq[String]] = formBody.get
    val name = params("name").head

    Ok(<h1>hello {name}</h1>).as(HTML)
  }


}