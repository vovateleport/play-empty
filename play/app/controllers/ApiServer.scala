package controllers

import pe.common.Api
import play.api.mvc._
import autowire.Error.InvalidInput
import upickle.Js
import upickle.Js.Obj
import upickle.default._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ApiServer extends Api {
  def doThing(s: String, i: Int): String = s"Hi $s ($i)"
}

trait ApiRouter extends autowire.Server[Js.Value, Reader, Writer] {
  def autowireApi(path: String): Action[AnyContent] = Action.async {
    implicit request =>
      val obj = upickle.json.read(request.body.asText.getOrElse("")).asInstanceOf[Obj].value.toMap
      val r = autowire.Core.Request(path.split('/').toSeq, obj)
      try {
        val rv = route[Api](new ApiServer)(r).map(upickle.json.write(_))
        rv.map { t => Results.Ok(t) }
      } catch {
        case e: InvalidInput =>
          val t = e.exs.map(_.toString).mkString(", ")
          Future(Results.InternalServerError(t))
      }
  }

  def read[Result: Reader](p: Js.Value) = upickle.default.readJs[Result](p)
  def write[Result: Writer](r: Result) = upickle.default.writeJs(r)
}
