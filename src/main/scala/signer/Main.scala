package signer

object Main {

  def main(args : Array[String]) {
    val toSign = JSONParser.parse(args(0))
    val url = Signer.getSignedUrl(toSign("bucketName"), toSign("objectKey"), toSign("pemFile"), toSign("clientId"))
    println(url)
  }
}
