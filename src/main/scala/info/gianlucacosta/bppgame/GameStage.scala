package info.gianlucacosta.bppgame

import javafx.scene.layout.BorderPane

import info.gianlucacosta.helios.apps.AppInfo
import info.gianlucacosta.helios.fx.Includes._
import info.gianlucacosta.helios.fx.dialogs.InputDialogs
import info.gianlucacosta.helios.fx.scene.fxml.FxmlScene
import info.gianlucacosta.helios.fx.stage.StackedStage
import info.gianlucacosta.twobinpack.core.Problem

import scalafx.Includes._
import scalafx.stage.WindowEvent


/**
  * Stage where the game actually takes place
  */
private class GameStage(
                         appInfo: AppInfo,
                         val previousStage: javafx.stage.Stage
                       ) extends StackedStage {

  private var gameController: GameController = _


  title =
    appInfo.name

  this.setMainIcon(appInfo)

  /**
    * Starts a new game
    *
    */
  def startGame(problem: Problem): Unit = {
    val fxmlScene =
      new FxmlScene[GameController, BorderPane](classOf[GameController]) {
        override protected def preInitialize(): Unit = {
          gameController =
            controller

          controller.stage =
            GameStage.this

          controller.problemOption() =
            Some(problem)
        }
      }


    scene =
      fxmlScene

    maximized =
      true

    show()
  }


  handleEvent(WindowEvent.WindowCloseRequest) {
    (event: WindowEvent) => {
      if (!canClose) {
        event.consume()
      }
    }
  }


  private def canClose: Boolean =
    InputDialogs.askYesNoCancel(
      "Return to the main screen?"
    ).contains(true)
}
