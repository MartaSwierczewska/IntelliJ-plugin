name := "IntellijLog"

version := "0.1" + name.value

scalaVersion := "2.13.1"

resourceDirectory in Compile := baseDirectory.value / "resources"

enablePlugins(SbtIdeaPlugin)

