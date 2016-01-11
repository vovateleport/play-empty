package pe.client

import scala.scalajs.js
import pe.common.{Robo, Api}
import org.querki.jquery._

import autowire._
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends js.JSApp{
  var i = 0

  def main() = {
    println(Robo.say())

    val pre1 = $("#pre1")
    $("#button1").click( () => {
      pre1.append("button1 click...")

      ApiClient[Api].doThing("ratata", {i=i+1;i}).call().foreach(x=>pre1.append(s"$x\n"))
    })
  }
}
