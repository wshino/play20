package controllers

import play.api.mvc.{Action, Controller}
import play.api.cache.Cache
import play.api.Play.current

/**
 * Created with IntelliJ IDEA.
 * User: shinohara_wataru
 * Date: 12/11/07
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
object RedisController extends Controller {
  def index = Action {
    // set
    try {
      val valueSet = Cache.set("item.key", "item.value", 30)
    } catch {
      case _ => println("set error")
    }


    // get pattern 1
    val valueFromCache1: Option[String] = Cache.getAs[String]("item.key")
    println(valueFromCache1.getOrElse("no key = item.key"))

    // get pattern 2
    val valueFromCache2: String = Cache.getOrElse[String]("item.key") {
      "no key = item.key"
    }
    println(valueFromCache2)

    Ok("ok")
  }
}
