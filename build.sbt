import sbt.Project.projectToRef

lazy val scalaV = "2.11.7"

lazy val commonSettings = Seq(
  name := "play",
  version := "1.0",
  scalaVersion := scalaV
)

lazy val jsProjects = Seq(client)

lazy val play = (project in file("play"))
  .settings(commonSettings: _*)
  .enablePlugins(PlayScala)
  .settings(
    routesGenerator := InjectedRoutesGenerator,// Play provides two styles of routers, one expects its actions to be injected, the other, legacy style, accesses its actions statically.
    scalaJSProjects := jsProjects,
    pipelineStages := Seq(scalaJSProd),
    libraryDependencies ++= Seq("com.vmunier" %% "play-scalajs-scripts" % "0.3.0")
  )
  .aggregate(jsProjects.map(projectToRef): _*)
  .dependsOn(sharedJvm)


lazy val client = (project in file("client"))
  .enablePlugins(ScalaJSPlugin, ScalaJSPlay)
  .settings(commonSettings:_*)
  .settings(
    persistLauncher := true,
    persistLauncher in Test := false
  )
  .dependsOn(sharedJs)

lazy val shared = (crossProject.crossType(CrossType.Pure) in file("shared"))
  .settings(scalaVersion := scalaV)
  .jsConfigure(_ enablePlugins ScalaJSPlay)

lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .aggregate(play)
