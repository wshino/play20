package controllers

import play.api.data._
import play.api.data.Forms._

import play.api.mvc._
import models.Tasks

import com.github.tototoshi.play2.json.LiftJson
import net.liftweb.json._

object Application extends Controller with LiftJson{

  implicit val formats = DefaultFormats

  case class Employee(name: String, mail: Option[String], age: Int)

  val employeeForm = Form(
    mapping(
      "name" -> text,
      "mail" -> optional(email),
      "age" -> number
    )(Employee.apply)(Employee.unapply)
  )

  def hello = TODO

  //  def hello = Action { implicit request =>
  //    val employee: Employee = employeeForm.bindFromRequest().get
  //    Ok(views.html.index("Your new application is ready."))
  //  }

  def hoge = Action{
    val task = Tasks.findAll

    println(task.head)


    Ok(Extraction.decompose(task))
  }

  //  def index = Action {
  //    Ok(views.html.index("Your new application is ready."))
  //  }
  //
  //  def hello = Action { request =>
  //    val params: Map[String, Seq[String]] = request.queryString
  //    val name = params("name").head
  //
  //    Ok(<h1>hello {name}</h1>).as(HTML)
  //  }

  //  def hello = Action { request =>
  //    // POST
  //    val formBody: Option[Map[String, Seq[String]]] = request.body.asFormUrlEncoded
  //    val params: Map[String, Seq[String]] = formBody.get
  //    val name = params("name").head
  //
  //    Ok(<h1>hello {name}</h1>).as(HTML)
  //  }


}