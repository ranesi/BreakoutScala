package breakout
import java.awt.Image
import javax.swing.ImageIcon

class Brick(d1: Boolean, d2: Boolean, px: Int, py: Int) extends Sprite {

  var ii: ImageIcon = new ImageIcon("brick.png")

  var destroyed: Boolean = d1
  var destroyable: Boolean = d2

  if (!d2) ii = new ImageIcon("ibrick.png")

  override var x: Int = px
  override var y: Int = py
  override var image: Image = ii.getImage
  override var i_width: Int = image.getWidth(null)
  override var i_height: Int = image.getHeight(null)

}
