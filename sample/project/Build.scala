import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "sample"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.scalaquery" % "scalaquery_2.9.0-1" % "0.9.5",
      "mysql" % "mysql-connector-java" % "5.1.18"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here
      resolvers += "tototoshi.github.com maven-repo/releases" at "http://tototoshi.github.com/maven-repo/releases",
      libraryDependencies ++= Seq(
        "com.github.tototoshi" %% "lift-json-play-module" % "0.1"
      )
    )

}
