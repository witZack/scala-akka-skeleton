package com.app.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import org.slf4j.LoggerFactory

/**
 * App
 */
object Bootstrap extends App
  with Dependencies
  with Routes {

  implicit val system = ActorSystem("example-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val LOG = LoggerFactory.getLogger(this.getClass)

  val bindingFuture = Http().bindAndHandle(route, "localhost", 3000)

  LOG.debug(s"Server online at http://localhost:3000")

}
