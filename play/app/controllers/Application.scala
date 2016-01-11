package controllers

import play.api.mvc._
import pe.common.Robo

class Application extends Controller with ApiRouter {

  def index = Action {
    Ok(views.html.index(Robo.say()))
  }
}

