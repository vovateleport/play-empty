package pe.client

import scala.scalajs.js
import pe.common.Robo

object Main extends js.JSApp{
  def main() = {
    println(Robo.say())
  }
}