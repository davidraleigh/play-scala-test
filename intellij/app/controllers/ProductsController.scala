package controllers

import models.Product
import play.api.mvc.{Action, Controller}

/**
  * Created by davidraleigh on 11/30/15.
  */
object ProductsController extends Controller {
  def list = Action { implicit request =>
    val products = Product.findAll
    Ok(views.html.list(products))
  }

  def show(ean: Long) = Action { implicit request =>
    Product.findByEan(ean).map { product =>
      Ok(views.html.products.details(product))
    }.getOrElse(NotFound)
  }
}