package controllers

import play.api.mvc.{Action, Controller}
import play.api.libs.json.{JsNull, Json}
import play.api.libs.ws.WS

/**
 * Created with IntelliJ IDEA.
 * User: shinohara_wataru
 * Date: 12/10/29
 * Time: 14:03
 * To change this template use File | Settings | File Templates.
 */
object JsonController extends Controller{

  def json = Action(parse.json) { request =>
    (request.body \ "name").asOpt[String].map{ name =>
      Ok(Json.toJson(Map("status" -> "OK", "message" -> ("Hello" + name))))
    }.getOrElse{
      BadRequest(Json.toJson(Map("status" -> "NG", "message" -> "Missing parameter [name]")))
    }
  }

  def postJson(name: String) = Action{
    Async(
          WS.url("http://localhost:9000/json/json")
    .withHeaders("Content-Type" -> "application/json")
    .post(Json.toJson(Map("name" -> name))).map { response =>
            (response.json \ "status").as[String] match {
              case "OK" => Ok((response.json \ "message").as[String])
              case "NG" => BadRequest((response.json \ "message").as[String])
            }
          }
    )
  }

}
