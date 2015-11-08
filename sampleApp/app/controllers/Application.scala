package controllers

import play.api.mvc._
import play.api.templates.Html

object Application extends Controller {
def index = Action {
       val content = Html("<div>This is the content for the sampleapp<div>")
           Ok(views.html.main("Home")(content))	
  // def index = Action {
  // 	val content = Html("<div>This is the content div</div>")
  //   Ok(views.html.main("Home")(content))
  }
}
