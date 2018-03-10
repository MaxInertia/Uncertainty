name := "Uncertainty"

version := "0.1"

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "com.lihaoyi" %% "utest" % "0.6.0" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")
