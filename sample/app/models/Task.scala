package models

import java.util.Date
import play.api.db._
import play.api.Play.current

import org.scalaquery.ql._
import basic.BasicTable
import extended.ExtendedColumnOption.AutoInc
import extended.ExtendedTable
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql.extended.{ExtendedTable => Table}

//import org.scalaquery.ql.extended.H2Driver.Implicit._

import org.scalaquery.session._

import org.scalaquery.ql.extended.MySQLDriver.Implicit._


//object Task extends Table[(Long, String, Date, Boolean)]("Task") {
case class Task(id: Long, label: String)

object Tasks extends Table[Task]("Task") {

  lazy val database = Database.forDataSource(DB.getDataSource())

  def id = column[Long]("id")

  def label = column[String]("label")

  def * = id ~ label <> (Task, Task.unapply _)

  def findAll = database.withSession {
    implicit db: Session =>
    ////      (for (t <- this) yield t.id ~ t.label).list
    //      val result = Query(Task)
    //
    //      println(result.selectStatement)
    //
    //      result.list()
      val q = for (t <- Tasks) yield t
          q.list() foreach println
          q.list()

    //      val hoge = selectAll.to[List]()
    //      selectAll.list() foreach println

  }

}