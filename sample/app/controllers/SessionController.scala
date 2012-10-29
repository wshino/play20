package controllers

import play.api.mvc.{Action, Controller}
import java.io.File

/**
 * Created with IntelliJ IDEA.
 * User: shinohara_wataru
 * Date: 12/10/29
 * Time: 12:08
 * To change this template use File | Settings | File Templates.
 */
object SessionController extends Controller {

  def count = Action { implicit request =>
      val count = session.get("count") match {
        case Some(x) => x.toInt + 1
        case None => 1
      }

      Ok(<p>Count:{count}</p>).as(HTML).withSession(
        session + ("count" -> count.toString)
      )
  }

  def delete = Action{ implicit request =>
      val count = session.get("count") match {
      case Some(x) => x.toInt + 1
      case None => 1
    }

    Ok(<p>Count: {count}</p>).as(HTML).withSession(
    session - "count"
    )
  }

}
