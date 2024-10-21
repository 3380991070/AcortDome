name := "AcortDome"

version := "1.0"

scalaVersion := "2.13.14"

resolvers += "Akka library repository".at("https://repo.akka.io/maven")

lazy val akkaVersion = sys.props.getOrElse("akka.version", "2.9.6")

// Run in a separate JVM, to make sure sbt waits until all threads have
// finished before returning.
// If you want to keep the application running while executing other
// sbt tasks, consider https://github.com/spray/sbt-revolver/
fork := true

//lazy val root = (project in file("."))
//    .aggregate(Master_Dome, Worker_Dome,eum_object) // 聚合子项目，以便可以一起构建
//    .settings(
//        name := "RootProject"
//    )

lazy val Master_Dome = (project in file("Master_Dome"))
    .dependsOn(eum_object)
    .settings(
        name := "Master_Dome",
        // 其他设置
    )

lazy val eum_object = (project in file("eum_object"))
    .settings(
        name := "eum_object"
    )

lazy val Worker_Dome = (project in file("Worker_Dome"))
    .dependsOn(eum_object) // 设置子模块B依赖于子模块A
    .settings(
        name := "Worker_Dome",
    )