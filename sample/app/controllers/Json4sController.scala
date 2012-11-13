package controllers

import play.api.mvc.{Action, Controller}
import models.{Task, Tasks}

import org.json4s._
import org.json4s.native.Serialization.{read, write}
import org.json4s.native.Serialization

/**
 * Created with IntelliJ IDEA.
 * User: shinohara_wataru
 * Date: 12/11/09
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
object Json4sController extends Controller {

  implicit val formats = Serialization.formats(NoTypeHints)

  def index = TODO
//  def index = Action{
//    val task = Tasks.findAll()
//    case class Project(name: String, startDate: String)
//    val project = Project("hoge", "kyou")
//    Ok("ok")
//  }
}
