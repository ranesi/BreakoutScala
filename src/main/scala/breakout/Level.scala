package breakout

import scala.collection.mutable
import scala.util.Random

/**
  * Created by richa on 5/16/2017.
  */
class Level {
  val lvl_a = Array(
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1),
    (1, 1, 1, 1, 1, 1, 1, 1)
  )

  var levels = mutable.MutableList[Array[(Int, Int, Int, Int, Int, Int, Int, Int)]]()
  levels += lvl_a

  def generateLevel(): Array[(Int, Int, Int, Int, Int, Int, Int, Int)] = levels(Random nextInt(levels size))
}
