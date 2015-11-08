package controllers

import models.Task
import play.api.mvc._
/**
  * Created by davidraleigh on 11/8/15.
  */
object TaskController extends Controller {

  def tasks = Action {
    Ok(views.html.index(Task.all))
  }

  def newTask = Action(parse.urlFormEncoded) {
    implicit request =>
      Task.add(request.body.get("taskName").get.head)
      Redirect(routes.Application.index)
  }

  def deleteTask(id: Int) = Action {
    Task.delete(id)
    Ok
  }
}
