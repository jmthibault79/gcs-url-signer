package signer

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential

// adapted from https://github.com/broadinstitute/firecloud-orchestration
// See also: https://cloud.google.com/storage/docs/access-control/create-signed-urls-program

object Signer {
  val secondsIn8Hours = 60 * 60 * 8

  def getSignedUrl(bucketName: String, objectKey: String, pemFile: String, clientId: String) = {
    val verb = "GET"
    val md5 = ""
    val contentType = ""
    val expireSeconds = (System.currentTimeMillis() / 1000) + secondsIn8Hours
    val objectPath = s"/$bucketName/$objectKey"

    getSignedUrlGeneric(pemFile, clientId, verb, md5, contentType, expireSeconds, objectPath)
  }

  // generic version that can sign anything

  private def getSignedUrlGeneric(pemFile: String, clientId: String, httpVerb: String, md5: String, contentType: String, expireSeconds: Long, objectPath: String) = {

    val signableString = s"$httpVerb\n$md5\n$contentType\n$expireSeconds\n$objectPath"

    val privateKey = new GoogleCredential.Builder()
      .setServiceAccountPrivateKeyFromPemFile(new java.io.File(pemFile))
      .getServiceAccountPrivateKey

    // sign the string
    val signature = java.security.Signature.getInstance("SHA256withRSA")
    signature.initSign(privateKey)
    signature.update(signableString.getBytes("UTF-8"))
    val signedBytes = signature.sign()

    // assemble the final url
    s"https://storage.googleapis.com$objectPath" +
      s"?GoogleAccessId=$clientId" +
      s"&Expires=$expireSeconds" +
      "&Signature=" + java.net.URLEncoder.encode(java.util.Base64.getEncoder.encodeToString(signedBytes), "UTF-8")
  }

}
