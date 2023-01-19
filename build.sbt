ThisBuild / scalaVersion     := "2.13.9"
ThisBuild / version          := "0.1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .settings(
    name := "zio-db-reactor",
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio" % "2.0.5",
      "io.d11"  %% "zhttp" % "2.0.0-RC10",
      "io.getquill" %% "quill-jdbc-zio" % "4.6.0",
      "dev.zio" %% "zio-jdbc" % "0.0.1",
      "org.postgresql" % "postgresql" % "42.3.1",
      "dev.zio" %% "zio-json" % "0.4.2",
      "dev.zio" %% "zio-test" % "2.0.5" % Test
    ),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
