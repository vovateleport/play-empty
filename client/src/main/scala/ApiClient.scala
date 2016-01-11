package pe.client

import org.scalajs.dom
import upickle.Js
import upickle.default._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object ApiClient extends autowire.Client[Js.Value, Reader, Writer] {
  override def doCall(req: Request): Future[Js.Value] = {
    dom.ext.Ajax.post(
      url = "/ajax/" + req.path.mkString("/"),
      data = upickle.json.write(Js.Obj(req.args.toSeq: _*))
    ).map(_.responseText).map(upickle.json.read)
  }

  def read[Result: Reader](p: Js.Value) = readJs[Result](p)

  def write[Result: Writer](r: Result) = writeJs(r)
}
