package breakout
import java.awt.Color._
import java.awt.Font.PLAIN
import javax.swing.JPanel
import java.awt._
import java.awt.Point
import java.awt.Toolkit.getDefaultToolkit
import java.awt.event.{KeyAdapter, KeyEvent}
import java.util.{Timer, TimerTask}

class Game extends JPanel {
  val BRICK_NUM = 80
  val DELAY = 1000
  val PERIOD = 5
  val WIDTH = 400
  val HEIGHT = 400
  val BOTTOM = 390
  var timer: Timer = _
  var ball: Projectile = _
  var bat: Bat = _
  var bricks: Array[Brick] = _
  var score: Int = 100
  var counter: Int = _
  var in_game: Boolean = true
  var win: Boolean = false
//  var level: Array[(Int, Int, Int, Int, Int, Int, Int, Int)] = _
  var i_bricks: Int = 0
  initFrame()
  def initFrame() {
    addKeyListener(new TAdapter())
    setFocusable(true)
//    val level_gen: Level = new Level
//    level = level_gen.generateLevel() //
    score = 100
    counter = 0
    bricks = new Array[Brick](BRICK_NUM)
    setBackground(WHITE)
    setDoubleBuffered(true)
    timer = new Timer()
    timer scheduleAtFixedRate(new ScheduleTask, DELAY, PERIOD)
  }
  override def addNotify(): Unit = {
    super.addNotify()
    gameInit()
  }
  def gameInit(): Unit = {
    ball = new Projectile()
    bat = new Bat()
    var k = 0
    for (i <- 0 to 9) for (j <- 0 to 7) {
        bricks(k) = new Brick(false, true, j * 40 + 30, i * 10 + 50)
        k += 1
      }
  }
  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    val g2d: Graphics2D = g.asInstanceOf[Graphics2D]
    if (in_game) drawObjects(g2d) else gameOver(g2d)
    getDefaultToolkit sync()
  }
  def drawObjects(g2d: Graphics2D): Unit = {
    val fm: FontMetrics = getFontMetrics(new Font("Courier New", PLAIN, 12))
    val str = "Score: " + score toString()
    g2d.setColor(BLACK)
    g2d.drawString(str, (WIDTH / (fm stringWidth str)) / 2, HEIGHT / 2 + 160)
    g2d.drawImage(ball image, ball x, ball y, ball i_width, ball i_height, this)
    g2d.drawImage(bat image, bat x, bat y, bat i_width, bat i_height, this)
    for (i <- 0 until BRICK_NUM)
      if (!(bricks(i) destroyed))
        g2d.drawImage(bricks(i) image, bricks(i) x, bricks(i) y, bricks(i) i_width, bricks(i) i_height, this)
  }
  def gameOver(g2d: Graphics2D) {
    val ss = "Final score: %s" format Integer.toString(score)
    val fm: FontMetrics = getFontMetrics(new Font("Courier New", PLAIN, 12))
    g2d.setColor(BLACK)
    g2d.drawString(ss, (WIDTH - fm.stringWidth(ss)) / 2, HEIGHT / 2)
  }
  class TAdapter extends KeyAdapter {
    override def keyReleased(e: KeyEvent): Unit = bat.keyReleased(e)
    override def keyPressed(e: KeyEvent): Unit = bat.keyPressed(e)
  }
  class ScheduleTask extends TimerTask {
    def run(): Unit = {
      ball move()
      bat move()
      checkCollision()
      repaint()
      counter += 1
      if (counter == 350) {
        score -= 1
        counter = 0
      }
    }
    def stopGame(): Unit = {
      in_game = false
      timer cancel()
    }
    def checkCollision(): Unit = {
      var j = 0
      val (bl, bh, bw, bt) = (
        (ball rectangle() getMinX).toInt, (ball rectangle() getHeight).toInt,
        (ball rectangle() getWidth).toInt, (ball rectangle() getMinY).toInt
      )
      val (pr, pl, pt, pb) = (
        new Point(bl + bw + 1, bt), new Point(bl - 1, bt), new Point(bl, bt - 1), new Point(bl, bt + bh + 1)
      )
      if ((ball rectangle() getMaxY) > BOTTOM) stopGame()
      for (i <- 0.until(BRICK_NUM)) {
        if (bricks(i) destroyed)
          j += 1
        if (j + i_bricks == BRICK_NUM) {
          win = true
          stopGame()
        }
      }
      if (ball rectangle() intersects bat.rectangle()) {
        val (bat_lpos, ball_lpos) = (bat rectangle() getMinX, ball rectangle() getMinX)
        val (first, second, third, fourth) = (bat_lpos + 8, bat_lpos + 16, bat_lpos + 24, bat_lpos + 32)
        if (ball_lpos < first) {
          ball x_dir = -1; ball y_dir = -1
        }
        if (Range(start = first toInt, end = second toInt) contains ball_lpos) {
          ball x_dir = -1; ball y_dir = -1 * (ball y_dir)
        }
        if (Range(start = second.toInt, end = third.toInt) contains ball_lpos) {
          ball x_dir = 0; ball y_dir = -1
        }
        if (Range(start = third.toInt, end = fourth.toInt) contains ball_lpos) {
          ball x_dir = 1; ball y_dir = -1 * (ball y_dir)
        }
        if (ball_lpos > fourth) {
          ball x_dir = 1; ball y_dir = -1
        }
      }
      for (i <- 0.until(BRICK_NUM)) {
        if (ball rectangle() intersects (bricks(i) rectangle())) {
          if (!(bricks(i) destroyed)) {
            if (bricks(i) rectangle() contains pr) ball x_dir = -1
            else if (bricks(i) rectangle() contains pl) ball x_dir = 1
            else if (bricks(i) rectangle() contains pt) ball y_dir = 1
            else if (bricks(i) rectangle() contains pb) ball y_dir = -1
            if (bricks(i) destroyable) bricks(i) destroyed = true
          }
        }
      }
    }
  }
}