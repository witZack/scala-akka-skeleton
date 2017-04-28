import example.{Dependencies, Formatting, Version}

inThisBuild(Def.settings(
  organization := "example.organization",
  organizationName := "Organization",
  organizationHomepage := Some(url("https://www.organization.com")),
  homepage := Some(url("https://www.github.com")),
  startYear := Some(2017),
  licenses := Seq("Apache-2.0" -> url("https://opensource.org/licenses/Apache-2.0")),
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-unchecked",
    "-Xlint",
    "-Ywarn-dead-code"
  ),
  javacOptions ++= Seq(
    "-encoding", "UTF-8"
  ),
  testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v"),
  Dependencies.Versions,
  Formatting.formatSettings,
  shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
))

lazy val root = Project(
  id = "root",
  base = file(".")
)
  .enablePlugins(ScalaUnidocPlugin, NoPublish)
  .disablePlugins(RevolverPlugin)
  .aggregate(
    core,
    coreTest
  )

lazy val core = project("core")
  .settings(Dependencies.core)
  .enablePlugins(RevolverPlugin)

lazy val coreTest = project("core-test")
  .settings(Dependencies.coreTest)
  .settings(Version.versionSettings)
  .dependsOn(core)

def project(name: String) =
  Project(id = name, base = file(name))