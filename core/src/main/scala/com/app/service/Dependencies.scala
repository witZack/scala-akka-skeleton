package com.app.service

import akka.actor.ActorSystem
import com.app.service.db.{ExampleTableQueries, ExamplesDB}
import com.app.service.example.GetExampleActor

/**
 * Service Dependencies such as actors, clients, and interface implementations
 */
trait Dependencies {

  val queries = new ExampleTableQueries with ExamplesDB

  def getExampleActor(implicit system: ActorSystem) = GetExampleActor.actorOf()
}
