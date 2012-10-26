package models
import anorm.Pk

case class User(id: Pk[Long], name: String, email: String, password: String)