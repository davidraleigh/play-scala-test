package controllers

import com.esri.core.geometry.Polygon
import play.api.mvc._
import play.twirl.api.Html

object Application extends Controller {

  def index = Action {
//    val poly = new Polygon
//    poly.startPath(0, 1)
//    poly.lineTo(2, 3)
//    poly.lineTo(3, 3)
//    poly.closeAllPaths()

    Redirect(routes.TaskController.tasks)
    //Ok(views.html.index(poly.toString()))//
  }

  def bananas = Action {
    val stuff = Html(" <div id=\"map\"></div>")
    Ok(views.html.bananas("Your new Bananas! application is ready.")(stuff))
  }
}