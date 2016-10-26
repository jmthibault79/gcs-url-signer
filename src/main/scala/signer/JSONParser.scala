package signer

import spray.json._
import spray.json.DefaultJsonProtocol._

object JSONParser {
  def parse(filename: String): Map[String, String] = {
    val json = io.Source.fromFile(filename).mkString.parseJson

    json match {
      case JsObject(map) => map mapValues { _.convertTo[String] }
      case _ => throw new Exception("Invalid JSON input")
    }
  }
}
