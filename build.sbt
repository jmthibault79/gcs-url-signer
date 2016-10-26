name := "gcs-url-signer"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-json" % "1.3.1",
  "com.google.api-client" % "google-api-client" % "1.22.0"
)

mainClass in (Compile, run) := Some("signer.Main")
