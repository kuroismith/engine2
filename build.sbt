name := "engine2"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.3.1"
libraryDependencies += "org.locationtech.geotrellis" %% "geotrellis-spark" % "2.0.0"
libraryDependencies += "org.locationtech.geotrellis" %% "geotrellis-s3" % "2.0.0"
libraryDependencies += "org.locationtech.geotrellis" %% "geotrellis-raster" % "2.0.0"
libraryDependencies += "org.locationtech.geotrellis" %% "geotrellis-vector" % "2.0.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
