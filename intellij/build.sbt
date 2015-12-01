name := "intellij"

version := "1.0"

lazy val `intellij` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test, "net.sf.barcode4j" % "barcode4j" % "2.0" )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  