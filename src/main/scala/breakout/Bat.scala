package breakout
import java.awt.Image
import java.awt.event.KeyEvent
import javax.swing.ImageIcon

class Bat extends Sprite {

  val width: Int = 400 // frame width
  val bat_init_x: Int = 200 // bat initial x position
  val bat_init_y: Int = 340 // bat initial y position
  val ii: ImageIcon = new ImageIcon("paddle.png") // image for bat

  var dx: Int = 0
  override var x: Int = bat_init_x
  override var y: Int = bat_init_y
  override var image: Image = ii getImage
  override var i_width: Int = image getWidth null
  override var i_height: Int = image getHeight null

  def move() {
    x += dx
    if (x <= 0) x = 0
    if (x >= width - i_width) x = width - i_width
  }

  def keyPressed(e: KeyEvent) {
    if (e.getKeyCode == KeyEvent.VK_LEFT) dx = -1
    if (e.getKeyCode == KeyEvent.VK_RIGHT) dx = 1
  }

  def keyReleased(e: KeyEvent) = if (e.getKeyCode == KeyEvent.VK_LEFT || e.getKeyCode == KeyEvent.VK_RIGHT) dx = 0

}
