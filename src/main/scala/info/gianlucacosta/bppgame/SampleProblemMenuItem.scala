package info.gianlucacosta.bppgame

import java.io.{BufferedReader, InputStreamReader}
import java.util.UUID

import info.gianlucacosta.twobinpack.core.Problem
import info.gianlucacosta.twobinpack.io.bpp.BppProblemReader

import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.MenuItem


/**
  * Menu item related to a sample problem: when clicked, it starts such problem
  */
private class SampleProblemMenuItem(
                                     problemName: String,
                                     bestBinsCount: Int,
                                     gameStage: GameStage
                                   ) extends MenuItem {
  text =
    s"${problemName} (${bestBinsCount} bins)"


  lazy val problem: Problem = {
    val problemResourceName =
      problemName
        .toLowerCase
        .replaceAll(" ", "_")

    val problemStream =
      getClass.getResourceAsStream(s"problems/${problemResourceName}.bpp")


    val problemReader =
      new BppProblemReader(
        new BufferedReader(
          new InputStreamReader(
            problemStream
          )
        )
      )


    try {
      problemReader.readBppProblem(
        initialFrameWidth = 1,
        timeLimitOption = None,
        problemName,
        UUID.randomUUID()
      )
    } finally {
      problemReader.close()
    }
  }


  handleEvent(ActionEvent.Action) {
    (event: ActionEvent) => {
      gameStage.startGame(problem)
    }
  }
}
