package models
import anorm._

/**
 * Created with IntelliJ IDEA.
 * User: a12581
 * Date: 12/10/24
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
case class Employee(name: String, mail: Option[String], age: Int)

