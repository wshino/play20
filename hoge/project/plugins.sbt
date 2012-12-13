// Comment to get more information during initialization
logLevel := Level.Warn

// The Typesafe repository 
resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += ("amateras repository" at "http://amateras.sourceforge.jp/mvn/")

// Use the Play sbt plugin for Play projects
addSbtPlugin("play" % "sbt-plugin" % "2.0.3")

addSbtPlugin("jp.sf.amateras.scalagen" % "scalagen-sbtplugin" % "0.2")




libraryDependencies ++= Seq(
  // for ScalaQuery
  "jp.sf.amateras.scalagen" %% "scalagen-scalaquery" % "0.2",
)