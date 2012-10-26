package controllers

import play.api.mvc.{Action, Controller}
import java.io.File
import akka.actor.ActorInitializationException

/**
 * Created with IntelliJ IDEA.
 * User: shinohara_wataru
 * Date: 12/10/26
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
object UploadController extends Controller{

  def index = Action {
    Ok(views.html.upload())
  }

  def upload = Action(parse.multipartFormData) { request =>
    request.body.file("file").map { file =>
      file.ref.moveTo(new File("/Users/a12581/tmp", file.filename))
      println("upload")
      Ok("file uploaded")
    }.getOrElse{
      BadRequest("missing file")
    }
  }
}
