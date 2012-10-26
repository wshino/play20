package controllers

import play.api.data._
import play.api.data.Forms._


import play.api.mvc._
import models.Employee


object EmployeeController extends Controller {

  //  case class Employee(name: String, mail: Option[String], age: Int)

  val employeeForm: Form[Employee] = Form(
    mapping(
      "name" -> text,
      "mail" -> optional(email),
      "age" -> number
    )(Employee.apply)(Employee.unapply)
  )

  def input = TODO
//  def input = Action {
//    Ok(views.html.form(employeeForm))
//  }

      def register = TODO
//  def register = Action {
//    implicit request =>
//      employeeForm.bindFromRequest.fold(
//        errors => BadRequest(views.html.form(errors)),
//        employee => {
//                    Ok(views.html.result(employee))
//          Ok(views.html.result())
//        }
//      )
//
//  }


}