package breakout

import java.awt.Dimension
import javax.swing.JFrame

/**
  * Created by richa on 5/16/2017.
  */
class Breakout extends JFrame {

  val WIDTH: Int = 400
  val HEIGHT: Int = 400

  add(new Game())
  setTitle("Breakout 2k7")
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  setSize(new Dimension(WIDTH, HEIGHT))
  setLocationRelativeTo(null)
  setResizable(false)
  setVisible(true)


}
