package com.app.service.example

import java.util.concurrent.atomic.AtomicInteger

import akka.actor.{ Actor, ActorLogging, ActorRefFactory, Props }
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.{ ActorMaterializer, ActorMaterializerSettings }
import akka.util.ByteString
import spray.json._
import spray.json.DefaultJsonProtocol

/**
 *
 */
object GetExampleActor {

  private val num = new AtomicInteger(0)

  def name(str: String): String = s"$str-actor-${num.getAndIncrement()}"

  def props(args: Any*) = Props(classOf[GetExampleActor], args)

  def actorOf(args: Any*)(implicit ref: ActorRefFactory) =
    ref.actorOf(props(args), name("GetExampleActor"))
}

class GetExampleActor
  extends Actor with ActorLogging {

  import akka.pattern.pipe
  import context.dispatcher

  final implicit val materializer: ActorMaterializer = ActorMaterializer(ActorMaterializerSettings(context.system))

  val http = Http(context.system)

  override def preStart() = {
    http.singleRequest(HttpRequest(uri = "http://akka.io"))
      .pipeTo(self)
  }

  def receive = {
    case HttpResponse(StatusCodes.OK, headers, entity, _) ⇒
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body ⇒
        log.info("Got response, body: " + body.utf8String.parseJson.toString())
      }
    case resp @ HttpResponse(code, _, _, _) ⇒
      log.info("Request failed, response code: " + code)
      resp.discardEntityBytes()
  }

}
