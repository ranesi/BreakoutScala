package breakout

/**
  * Created by richa on 5/15/2017.
  */

import java.awt._

object Main {
  def main(args: Array[String]) {
    EventQueue.invokeLater(() => {
      def game() = {
        val game = new Breakout
        game.setVisible(true)
      }

      game()
    })
  }
}
