package models

import play.api.db._
import play.api.Play.current

import org.scalaquery.ql._
import extended.{ExtendedTable => Table}
import org.scalaquery.ql.TypeMapper._
import play.api.Logger
import org.json4s.JsonAST.JValue

//import org.json4s.JsonAST.JValue


//import org.scalaquery.ql.extended.H2Driver.Implicit._

import org.scalaquery.session._

import org.scalaquery.ql.extended.MySQLDriver.Implicit._


//object Task extends Table[(Long, String, Date, Boolean)]("Task") {
case class Task(id: Long, label: String)

object Tasks extends Table[Task]("Task") {

  lazy val database = Database.forDataSource(DB.getDataSource())
  lazy val slave = Database.forDataSource(DB.getDataSource("slave"))

  def id = column[Long]("id", O AutoInc, O PrimaryKey, O NotNull)

  def label = column[String]("label", O NotNull)

  def * = id ~ label <>(Task, Task.unapply _)

  // 一件取得
  def find(id: Long) = database.withSession {
    implicit db: Session =>
      val q = Tasks.where(_.id is id)
      //      q.first() // 値がなかった場合は例外が飛ぶ
      q.firstOption // 値がなかった場合は None が返る
  }

  // 全件取得
  def findAll(limit: Int = 1000) = database.withSession {
    implicit db: Session =>
      if (limit == 1000) {
        val q = Query(Tasks)
        Logger.debug(this + ".findAll:query=" + q.selectStatement)
        q.list()
      } else {
        val q = Query(Tasks).take(limit)
        Logger.debug(this + ".findAll:query=" + q.selectStatement)
        q.list()
      }
  }

  // 件数を指定して取得
  //  def find(limit: Long) = database.withSession{
  //    implicit  db: Session =>
  //
  //  }

//  インサート
    def insert(task: Task) = database.withSession {
      implicit db: Session =>
        Tasks.label insert (task.label)
    }

  // インサート複数
  def insertAll(taskList: List[Task]) = database.withSession {
    implicit db: Session =>
      for (task <- taskList) {

        Tasks.insert(task)
      }
      taskList.size
  }


  // 変更
  def update(task: Task) = database.withSession {
    implicit db: Session =>

      val q = Tasks.where(_.id is task.id).map(_.label)
      q.update(task.label)
  }

  // 削除
  def delete(id: Long) {
    database.withSession {
      implicit db: Session =>
        val q = Tasks.where(_.id is id)
        val res = q.delete
        println(res)
    }
  }

}