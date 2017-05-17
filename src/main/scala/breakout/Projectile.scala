package breakout
import java.awt.Image
import javax.swing.ImageIcon

class Projectile extends Sprite {

  val width: Int = 400
  val p_init_x: Int = 230
  val p_init_y: Int = 300
  val ii: ImageIcon = new ImageIcon("ball.png")

  var x_dir: Int = 1
  var y_dir: Int = -1
  override var x: Int = p_init_x
  override var y: Int = p_init_y
  override var image: Image = ii getImage
  override var i_width: Int = image getWidth null
  override var i_height: Int = image getHeight null

  def move(): Unit = {
    x += x_dir
    y += y_dir
    if (x == 0) x_dir = 1
    if (x == width - i_width) x_dir = -1
    if (y == 0) y_dir = 1
  }
}
