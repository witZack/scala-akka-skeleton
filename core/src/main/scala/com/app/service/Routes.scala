package com.app.service

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.{ Directives, Route }
import akka.stream.Materializer
import com.app.service.model.ServiceJsonFormatters

import scala.concurrent.duration._

/**
 * Route definition of the services api
 */
trait Routes extends Directives with SprayJsonSupport with ServiceJsonFormatters {
  self: Dependencies ⇒

  implicit val system: ActorSystem
  implicit val materializer: Materializer
  implicit val timeout: FiniteDuration = 5.seconds

  val opsRoute =
    pathPrefix("ops") {
      path("status") {
        complete("ok")
      }
    }

  val route: Route =
    post {
      pathPrefix("v1") {
        opsRoute
      }
    }
}
