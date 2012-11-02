package controllers

import play.api.data._
import play.api.data.Forms._

import play.api.mvc._
import models.Task
import models.Tasks


import com.github.tototoshi.play2.json.LiftJson
import net.liftweb.json._

object Application extends Controller with LiftJson {

  implicit val formats = DefaultFormats

//  case class Employee(name: String, mail: Option[String], age: Int)
//
//  val employeeForm = Form(
//    mapping(
//      "name" -> text,
//      "mail" -> optional(email),
//      "age" -> number
//    )(Employee.apply)(Employee.unapply)
//  )

  def hello = TODO

  //  def hello = Action { implicit request =>
  //    val employee: Employee = employeeForm.bindFromRequest().get
  //    Ok(views.html.index("Your new application is ready."))
  //  }

  def display = Action {
    val task = Tasks.findAll
    Ok(Extraction.decompose(task))
  }

  def find(id: Long) = Action{
    val task = Tasks.find(id)
    task match {
      case None => Ok("None")
      case _ => Ok(Extraction.decompose(task))
    }
  }

  def insert(label: String) = Action {
    val task = Task.apply(0, label)
    Tasks.insert(task)
    Ok("insert success")
  }

  def insertAll = Action {
    val task = Task.apply(0, "all1")
    val task2 = Task.apply(0, "all2")
    val task3 = Task.apply(0, "all3")

    Tasks.insertAll(task :: task2 :: task3 :: Nil)
    Ok("insert all")
  }

  def update(id: Long, label: String) = Action {
    val task = Tasks.findAll.head.copy(id = id, label = label)
    Tasks.update(task)
    Ok("update ok")
  }
}