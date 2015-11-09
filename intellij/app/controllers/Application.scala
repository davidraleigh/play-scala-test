package controllers

import models.Artist
import play.api.libs.json.JsValue

//import com.esri.core.geometry.Polygon
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

  def listArtist = Action {
    Ok(views.html.artists(Artist.fetch))
  }

  def fetchArtistByName(name: String) = Action {
    Ok(views.html.artists(Artist.fetchByName(name)))
  }

  def search(name: String, country: String) = Action {
    val result = Artist.fetchByNameOrCountry(name, country)
    if (result.isEmpty) {
      NoContent
    } else {
      Ok(views.html.artists(result))
    }
  }

  def search2(name: Option[String], country: String) = Action {
    val result = name match {
      case Some(n) => Artist.fetchByNameOrCountry(n, country)
      case None => Artist.fetchByCountry(country)
    }
    if (result.isEmpty) {
      NoContent
    } else {
      Ok(views.html.artists(result))
    }
  }

//  def subscribe = Action {
//    request => {
//      val reqBody: AnyContent = request.body
//      val textContent: Option[String] = reqBody.asText
//
//      textContent.map {
//        emailId => Ok("added " + emailId + " to subscribers list\n")
//      }.getOrElse {
//        BadRequest("argh")
//      }
//    }
//  }

//  def subscribe = Action(parse.text) {
//    request => Ok("added " + request.body + " words mofo\n")
//  }

  def subscribe = Action(parse.json) {
    request => {
      val reqData: JsValue = request.body
      val emailId: String = (reqData \ "emailId").as[String]
      val interval: String = (reqData \ "interval").as[String]
      Ok(s"added $emailId to subscriber's list and will send updates every $interval\n")
    }
  }
}