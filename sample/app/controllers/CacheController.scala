package controllers

import play.api.mvc.{Action, Controller}
import play.api.cache.{Cached, Cache}
import play.api.Play.current

/**
 * Created with IntelliJ IDEA.
 * User: shinohara_wataru
 * Date: 12/10/29
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
object CacheController extends Controller {

  //  def date = Action {
  //    val date = Cache.getOrElse[java.util.Date]("date"){
  //      // ない場合
  //      new java.util.Date()
  //    }
  //    Ok(date.toString)
  //  }

  def date = {
    Cached("hello") {
      Action {
        Ok(new java.util.Date().toString)
      }
    }
  }
}
