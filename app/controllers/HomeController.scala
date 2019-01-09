package controllers

import javax.inject._
import play.api._
import play.api.libs.json.{JsObject, JsValue, Json}
import play.api.mvc._

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page.
    *
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index() = Action { implicit request: Request[AnyContent] =>


    val data: JsValue = Json.parse(
      """[
        |  {
        |    "projectId": "157cc423-27a6-4908-8675-d63be79ba2ad",
        |    "timestamp": 1545256800000,
        |    "pivots": {
        |      "ticketCount": 2255,
        |      "initialSubmit": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "initialPR": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "initialQA": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "reworkAfterPR": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "reworkAfterQA": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "prReviewed": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "qaReviewed": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      }
        |    }
        |  },
        |  {
        |    "projectId": "157cc423-27a6-4908-8675-d63be79ba2ad",
        |    "timestamp": 1545357600000,
        |    "pivots": {
        |      "ticketCount": 2255,
        |      "initialSubmit": {
        |        "percentage": 0.44,
        |        "count": 10,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "initialPR": {
        |        "percentage": 0.08,
        |        "count": 2,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "initialQA": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "reworkAfterPR": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "reworkAfterQA": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "prReviewed": {
        |        "percentage": 0.08,
        |        "count": 2,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      },
        |      "qaReviewed": {
        |        "percentage": 0,
        |        "count": 0,
        |        "todoCount": 37172,
        |        "todoPercentage": 32.94
        |      }
        |    }
        |  }
        |]""".stripMargin)


    val result = data.as[List[JsValue]].map { a =>
      val r = (a \ "pivots").as[Map[String, JsValue]]
      val j = r.map {
        case (milestone, status) =>
          println(status)

//          val updatedStatus = status.as[Map[String, JsValue]].filterKeys { key => key != "todoCount" || key != "todoPercentage" }

          milestone -> Json.toJson(Map("a" -> 1))
      }

      j
    }

//    println(Json.prettyPrint())

    Ok(Json.toJson(result))
  }

  def roundedToPrecision(value: Double, precision: Int = 2): Double = {
    val w = scala.math.pow(10, precision)
    (value * w).toLong.toDouble / w
  }

}
