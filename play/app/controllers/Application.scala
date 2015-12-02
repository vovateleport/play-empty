package controllers

import play.api._
import play.api.mvc._
import pe.common.Robo

class Application extends Controller {

  def index = Action {
    Ok(views.html.index(Robo.say()))
  }
}
