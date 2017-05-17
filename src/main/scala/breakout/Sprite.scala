package breakout

import java.awt.{Image, Rectangle}

abstract class Sprite() {
  var x: Int
  var y: Int
  var i_width: Int
  var i_height: Int
  var image: Image

  def rectangle(): Rectangle = new Rectangle(x, y, image getWidth null, image getHeight null)
}

