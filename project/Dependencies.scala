package example

import sbt._
import sbt.Keys._
import scala.language.implicitConversions

object Dependencies {

  val junitVersion = "4.12"
  val h2specVersion = "1.5.0"

  lazy val akkaVersion = settingKey[String]("The version of Akka to use.")
  lazy val akkaHttpVersion = settingKey[String]("The version of Akka-Http to use.")
  lazy val scalaTestVersion = settingKey[String]("The version of ScalaTest to use.")
  lazy val specs2Version = settingKey[String]("The version of Specs2 to use")
  lazy val scalaStmVersion = settingKey[String]("The version of ScalaSTM to use.")
  lazy val scalaCheckVersion = settingKey[String]("The version of ScalaCheck to use.")
  lazy val java8CompatVersion = settingKey[String]("The version of scala-java8-compat to use.")
  lazy val enumeratumVersion = settingKey[String]("The version of scala-java8-compat to use.")

  val Versions = Seq(
    akkaVersion := "2.4.17",
    akkaHttpVersion := "10.0.5",
    crossScalaVersions := Seq("2.11.8"),
    scalaVersion := crossScalaVersions.value.head,
    scalaCheckVersion := sys.props.get("akka.build.scalaCheckVersion").getOrElse("1.13.4"),
    scalaTestVersion := "3.0.0",
    specs2Version := "3.8.6",
    java8CompatVersion := "0.8.0",
    enumeratumVersion := "1.5.10"
  )
  import Versions._


  object Compile {
    // Compile

    // For akka http cause we want to use akka http
    val akkaHttp        = Def.setting { "com.typesafe.akka"             %% "akka-http"                    % akkaHttpVersion.value }
    val akkaSJson       = Def.setting { "com.typesafe.akka"             %  "akka-http-spray-json_2.11"    % akkaHttpVersion.value }
    val akkaHttpCore    = Def.setting { "com.typesafe.akka"             %% "akka-http-core"               % akkaHttpVersion.value }
    val sprayCaching    = "io.spray"                                    % "spray-caching_2.11"            % "1.3.4"

    // For enumeration cause gotta have those types
    val enumeration     = Def.setting { "com.beachape"    %% "enumeratum"                   % enumeratumVersion.value }
    // Joda DateTime for... DateTime stuff
    val jodaTime        = "joda-time"                     %  "joda-time"                    % "2.9.9"

    val logbackClassic  = "ch.qos.logback"                % "logback-classic"               % "1.2.3"
    val slf4jNop        = "org.slf4j"                     % "slf4j-nop"                     % "1.6.4" // Include this to disable logging if you need to

    object Test { // All our testing dependencies
      val junit        = "junit"                        % "junit"                        % junitVersion             % "test" // Common Public License 1.0
      val logback      = "ch.qos.logback"               % "logback-classic"              % "1.1.3"                  % "test" // EPL 1.0 / LGPL 2.1
      val mockito      = "org.mockito"                  % "mockito-all"                  % "1.10.19"                % "test" // MIT
      val scalatest    = Def.setting { "org.scalatest"  %% "scalatest"                   % scalaTestVersion.value   % "test" }  // ApacheV2
      val specs2       = Def.setting { "org.specs2"     %% "specs2-core"                 % specs2Version.value      % "test" }  // MIT
      val scalacheck   = Def.setting { "org.scalacheck" %% "scalacheck"                  % scalaCheckVersion.value  % "test" }  // New BSD
      val log4j        = "log4j"                        % "log4j"                        % "1.2.14"                 % "test" // ApacheV2
    }

    object Database { // Database dependencies for mysql using slick
      val slick           = "com.typesafe.slick"            %% "slick"                        % "3.2.0" exclude("org.slf4j","slf4j-api")
      val slickHikari     = "com.typesafe.slick"            %% "slick-hikaricp"               % "3.2.0" exclude("org.slf4j","slf4j-api")
      val mysql           = "mysql"                         % "mysql-connector-java"          % "6.0.6"
    }

  }

  import Compile._

  lazy val l = libraryDependencies

  lazy val core = l ++= Seq(
    akkaHttp.value,
    akkaSJson.value,
    enumeration.value,
    sprayCaching,
    jodaTime,
    logbackClassic,
    Database.slick,
    Database.slickHikari,
    Database.mysql,
    Test.scalatest.value,
    Test.scalacheck.value,
    Test.junit)

  lazy val coreTest = l ++= Seq(
    Test.junit,
    Test.scalatest.value.copy(configurations = Some("provided; test")),
    Test.specs2.value.copy(configurations = Some("provided; test"))
  )

}