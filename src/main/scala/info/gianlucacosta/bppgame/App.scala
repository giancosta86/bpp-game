package info.gianlucacosta.bppgame

import javafx.stage.Stage

import info.gianlucacosta.bppgame.icons.MainIcon
import info.gianlucacosta.helios.apps.{AppInfo, AuroraAppInfo}
import info.gianlucacosta.helios.fx.application.{AppBase, SplashStage}

import scalafx.Includes._
import scalafx.application.Platform

/**
  * Application's entry point
  */
class App extends AppBase(AuroraAppInfo(ArtifactInfo, MainIcon)) {
  override def startup(appInfo: AppInfo, splashStage: SplashStage, primaryStage: Stage): Unit = {
    Platform.runLater {
      primaryStage.scene =
        new MainScene(appInfo, primaryStage)
    }


    Platform.runLater {
      primaryStage.sizeToScene()

      primaryStage.title =
        appInfo.name
    }


    Platform.runLater {
      primaryStage.setResizable(false)
      primaryStage.show()

      primaryStage.centerOnScreen()
    }
  }
}