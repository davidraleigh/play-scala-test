package controllers

import java.util.Date
import java.util.concurrent.TimeUnit

import models.{Subscription, User, Artist}
import play.api.libs.json.{Json, JsValue}

import scala.concurrent.Future

//import scala.concurrent.Future
import scala.xml.Node

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

//  def subscribe = Action(parse.json) {
//    request =>
//      val reqData: JsValue = request.body
//      val emailId: String = (reqData \ "emailId").as[String]
//      val interval: String = (reqData \ "interval").as[String]
//      Ok(s"added $emailId to subscriber's list and will send updates every $interval\n")
//  }

  def subscribe = Action(parse.tolerantJson) {
    request =>
      val reqData: JsValue = request.body
      val emailId: String = (reqData \ "emailId").as[String]
      val interval: String = (reqData \ "interval").as[String]
      Ok(s"added $emailId to subscriber's list and will send updates every $interval\n")
  }

  val toImplement = Action {
    NotImplemented[play.twirl.api.Html](views.html.defaultpages.todo())
  }

  import java.io.File
  import play.api.libs.concurrent.Execution.Implicits._

//  def getReport(fileName: String) = Action.async {
//    Future {
//      val file: File = new File(fileName)
//      if (file.exists()) {
//        val info = file.lastModified()
//        Ok(s"lastModified on ${new Date(info)}")
//      } else {
//        NoContent
//      }
//    }
//  }

  def getReport(fileName:String) = Action.async {

    val maybeFile = Future {
      new File(fileName)
    }

    val timeout = play.api.libs.concurrent.Promise.timeout("PastTime", 10, TimeUnit.SECONDS)
    Future.firstCompletedOf(Seq(maybeFile, timeout)).map {
      case f: File =>
        if (f.exists()) {
          val info = f.lastModified()
          Ok(s"last modified ${new Date(info)}")
        } else {
          NoContent
        }
      case t: String => InternalServerError(t)
    }
  }


  def getConfig = Action {
    implicit request =>
      val xmlResponse: Node = <metadata>
      <company>TinySensors</company>
        <batch>md2907</batch>
      </metadata>

      val jsonResonse = Json.obj("metadata" -> Json.arr(
        Json.obj("company" -> "TinySensors"),
        Json.obj("batch" -> "md2907")
      ))
      render {
        case Accepts.Xml() => Ok(xmlResponse)
        case Accepts.Json() => Ok(jsonResonse)
      }
  }

//  import java.io.File

//  def createProfile = Action(parse.multipartFormData) {
//    request =>
//      val formData = request.body.asFormUrlEncoded
//      val email: String = formData.get("email").get(0)
//      val name: String = formData.get("name").get(0)
//      val userId: Long = User(email, name).save
//      request.body.file("displayPic").map {
//        picture =>
//          val path = "/socialize/user/"
//          if (!picture.filename.isEmpty) {
//            picture.ref.moveTo(new File(path + userId + ".jpeg"))
//          }
//          Ok("successfully added user")
//      }.getOrElse {
//        BadRequest("failed to add user")
//      }
//  }
//
//  val parseAsSubscription = parse.using {
//    request =>
//      parse.json.map {
//        body =>
//          val emailId: String = (body \ "emailId").as[String]
//          val fromDate: String = (body \ "fromDate").as[String]
//          Subscription(emailId, fromDate)
//      }
//  }
//
//  implicit val subWrites = Json.writes[Subscription]
//  def getSub = Action(parseAsSubscription) {
//    request =>
//      val subscription: Subscription = request.body
//      Ok(Json.toJson(subscription))
//  }
}