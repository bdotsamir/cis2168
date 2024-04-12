package util;

/******************************************************************************
 *  Compilation:  javac Draw.java
 *  Execution:    java Draw
 *  Dependencies: none
 *
 *  A small drawing library. This class provides a capability for drawing basic
 *  shapes. It uses a simple graphics model that allows you to create drawings
 *  consisting of geometric shapes (e.g.,points, lines, circles, rectangles) in
 *  a window on your computer and to save the drawings to a file.
 *
 ******************************************************************************/

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import java.awt.image.BufferedImage;
import java.awt.image.DirectColorModel;
import java.awt.image.WritableRaster;

import java.io.File;
import java.io.IOException;

import java.util.LinkedList;
import java.util.TreeSet;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 * The {@code Draw} class provides a basic capability for drawing basic
 * geometric shapes. It uses a simple graphics model that
 * allows you to create drawings consisting of points, lines, squares,
 * circles, and other geometric shapes in a window on your computer and
 * to save the drawings to a file. Standard drawing also includes
 * facilities for text, color, and simple animation.
 * <p>
 * <b>Getting started.</b>
 * Type the following short program into your editor:
 * <pre>
 *   public class TestStdDraw {
 *       public static void main(String[] args) {
 *           Draw.setPenRadius(0.05);
 *           Draw.setPenColor(Draw.BLUE);
 *           Draw.point(0.5, 0.8);
 *
 *           Draw.setPenRadius(0.0025);
 *           Draw.setPenColor(Draw.TEMPLE_CHERRY);
 *           Draw.line(0.2, 0.2, 0.8, 0.2);
 *
 *           Draw.setFont(new Font("Arial", Font.BOLD, 60));
 *           Draw.text(0.5, 0.5, "Hello, World");
 *       }
 *   }
 *  </pre>
 * If you compile and execute the program, you should see a window
 * appear with a thick cherry line and a blue point. "Hellow, World"
 * should be printed in large letters in the middle of the screen.
 * This program illustrates the two main types of methods in standard
 * drawing—methods that draw geometric shapes and methods that
 * control drawing parameters.
 * The methods {@code Draw.line()} and {@code Draw.point()}
 * draw lines and points; the methods {@code Draw.setPenRadius()}
 * and {@code Draw.setPenColor()} control the line thickness and color.
 * <p>
 * <b>Points and lines.</b>
 * You can draw points and line segments with the following methods:
 * <ul>
 * <li> {@link #point(double x, double y)}
 * <li> {@link #line(double x1, double y1, double x2, double y2)}
 * </ul>
 * p>
 * The <em>x</em>- and <em>y</em>-coordinates must be in the drawing area
 * (between 0 and 1 and by default) or the points and lines will not be visible.
 * p>
 * <b>Squares, circles, rectangles, and ellipses.</b>
 * You can draw squares, circles, rectangles, and ellipses using
 * the following methods:
 * <ul>
 * <li> {@link #circle(double x, double y, double radius)}
 * <li> {@link #ellipse(double x, double y, double semiMajorAxis, double semiMinorAxis)}
 * <li> {@link #square(double x, double y, double halfLength)}
 * <li> {@link #rectangle(double x, double y, double halfWidth, double halfHeight)}
 * </ul>
 * p>
 * All of these methods take as arguments the location and size of the shape.
 * The location is always specified by the <em>x</em>- and <em>y</em>-coordinates
 * of its <em>center</em>.
 * The size of a circle is specified by its radius and the size of an ellipse is
 * specified by the lengths of its semi-major and semi-minor axes.
 * The size of a square or rectangle is specified by its half-width or half-height.
 * The convention for drawing squares and rectangles is parallel to those for
 * drawing circles and ellipses, but may be unexpected to the uninitiated.
 * p>
 * The methods above trace outlines of the given shapes. The following methods
 * draw filled versions:
 * <ul>
 * <li> {@link #filledCircle(double x, double y, double radius)}
 * <li> {@link #filledEllipse(double x, double y, double semiMajorAxis, double semiMinorAxis)}
 * <li> {@link #filledSquare(double x, double y, double radius)}
 * <li> {@link #filledRectangle(double x, double y, double halfWidth, double halfHeight)}
 * </ul>
 * p>
 * <b>Circular arcs.</b>
 * You can draw circular arcs with the following method:
 * <ul>
 * <li> {@link #arc(double x, double y, double radius, double angle1, double angle2)}
 * </ul>
 * p>
 * The arc is from the circle centered at (<em>x</em>, <em>y</em>) of the specified radius.
 * The arc extends from angle1 to angle2. By convention, the angles are
 * <em>polar</em> (counterclockwise angle from the <em>x</em>-axis)
 * and represented in degrees. For example, {@code Draw.arc(0.0, 0.0, 1.0, 0, 90)}
 * draws the arc of the unit circle from 3 o'clock (0 degrees) to 12 o'clock (90 degrees).
 * p>
 * <b>Polygons.</b>
 * You can draw polygons with the following methods:
 * <ul>
 * <li> {@link #polygon(double[] x, double[] y)}
 * <li> {@link #filledPolygon(double[] x, double[] y)}
 * </ul>
 * p>
 * The points in the polygon are ({@code x[i]}, {@code y[i]}).
 * For example, the following code fragment draws a filled diamond
 * with vertices (0.1, 0.2), (0.2, 0.3), (0.3, 0.2), and (0.2, 0.1):
 * <pre>
 *  double[] x = { 0.1, 0.2, 0.3, 0.2 };
 *  double[] y = { 0.2, 0.3, 0.2, 0.1 };
 *  Draw.filledPolygon(x, y);
 * </pre>
 * p>
 * <b>Pen size.</b>
 * The pen is circular, so that when you set the pen radius to <em>r</em>
 * and draw a point, you get a circle of radius <em>r</em>. Also, lines are
 * of thickness 2<em>r</em> and have rounded ends. The default pen radius
 * is 0.005 and is not affected by coordinate scaling. This default pen
 * radius is about 1/200 the width of the default canvas, so that if
 * you draw 100 points equally spaced along a horizontal or vertical line,
 * you will be able to see individual circles, but if you draw 200 such
 * points, the result will look like a line.
 * <ul>
 * <li> {@link #setPenRadius(double radius)}
 * </ul>
 * p>
 * For example, {@code Draw.setPenRadius(0.025)} makes
 * the thickness of the lines and the size of the points to be five times
 * the 0.005 default.
 * To draw points with the minimum possible radius (one pixel on typical
 * displays), set the pen radius to 0.0.
 * p>
 * <b>Pen color.</b>
 * All geometric shapes (such as points, lines, and circles) are drawn using
 * the current pen color. By default, it is black.
 * You can change the pen color with the following methods:
 * <ul>
 * <li> {@link #setPenColor(int red, int green, int blue)}
 * <li> {@link #setPenColor(Color color)}
 * </ul>
 * p>
 * The first method allows you to specify colors using the RGB color system.
 * This <a href = "http://johndyer.name/lab/colorpicker/">color picker</a>
 * is a convenient way to find a desired color.
 * The second method allows you to specify colors using the
 * {@link Color} data type. You can use this method with one of these predefined colors in standard drawing:
 * {@link #BLACK}, {@link #BLUE}, {@link #DARK_GRAY}, {@link #GRAY},
 * {@link #GREEN}, {@link #LIGHT_GRAY},  {@link #ORANGE}, {@link #RED},
 * {@link #WHITE}, {@link #YELLOW}, {@link #BLUE}, {@link #LIGHT_BLUE},
 * {@link #RED}, {@link #TEMPLE_CHERRY}, and {@link #ORANGE}.
 * <p>
 * For example, {@code Draw.setPenColor(Draw.BLUE)} sets the
 * pen color to blue.
 * p>
 * <b>Canvas size.</b>
 * By default, all drawing takes places in a 512-by-512 canvas.
 * The canvas does not include the window title or window border.
 * You can change the size of the canvas with the following method:
 * <ul>
 * <li> {@link #setCanvasSize(int width, int height)}
 * </ul>
 * p>
 * This sets the canvas size to be <em>width</em>-by-<em>height</em> pixels.
 * It also erases the current drawing and resets the coordinate system,
 * pen radius, pen color, and font back to their default values.
 * Ordinarly, this method is called once, at the very beginning of a program.
 * For example, {@code Draw.setCanvasSize(800, 800)}
 * sets the canvas size to be 800-by-800 pixels.
 * p>
 * <b>Canvas scale and coordinate system.</b>
 * By default, all drawing takes places in the unit square, with (0, 0) at
 * lower left and (1, 1) at upper right. You can change the default
 * coordinate system with the following methods:
 * <ul>
 * <li> {@link #setXscale(double xmin, double xmax)}
 * <li> {@link #setYscale(double ymin, double ymax)}
 * <li> {@link #setScale(double min, double max)}
 * </ul>
 * p>
 * The arguments are the coordinates of the minimum and maximum
 * <em>x</em>- or <em>y</em>-coordinates that will appear in the canvas.
 * For example, if you  wish to use the default coordinate system but
 * leave a small margin, you can call {@code Draw.setScale(-.05, 1.05)}.
 * p>
 * These methods change the coordinate system for subsequent drawing
 * commands; they do not affect previous drawings.
 * These methods do not change the canvas size; so, if the <em>x</em>-
 * and <em>y</em>-scales are different, squares will become rectangles
 * and circles will become ellipses.
 * p>
 * <b>Text.</b>
 * You can use the following methods to annotate your drawings with text:
 * <ul>
 * <li> {@link #text(double x, double y, String text)}
 * <li> {@link #text(double x, double y, String text, double degrees)}
 * <li> {@link #textLeft(double x, double y, String text)}
 * <li> {@link #textRight(double x, double y, String text)}
 * </ul>
 * p>
 * The first two methods write the specified text in the current font,
 * centered at (<em>x</em>, <em>y</em>).
 * The second method allows you to rotate the text.
 * The last two methods either left- or right-align the text at (<em>x</em>, <em>y</em>).
 * p>
 * The default font is a Sans Serif font with point size 16.
 * You can use the following method to change the font:
 * <ul>
 * <li> {@link #setFont(Font font)}
 * </ul>
 * p>
 * You use the {@link Font} data type to specify the font. This allows you to
 * choose the face, size, and style of the font. For example, the following
 * code fragment sets the font to Arial Bold, 60 point.
 * <pre>
 *  Font font = new Font("Arial", Font.BOLD, 60);
 *  Draw.setFont(font);
 *  Draw.text(0.5, 0.5, "Hello, World");
 * </pre>
 * p>
 * <b>Clearing the canvas.</b>
 * To clear the entire drawing canvas, you can use the following methods:
 * <ul>
 * <li> {@link #clear()}
 * <li> {@link #clear(Color color)}
 * </ul>
 * p>
 * The first method clears the canvas to white; the second method
 * allows you to specify a color of your choice. For example,
 * {@code Draw.clear(Draw.LIGHT_GRAY)} clears the canvas to a shade
 * of gray.
 * p>
 * <b>Computer animations and double buffering.</b>
 * Double buffering is one of the most powerful features of standard drawing,
 * enabling computer animations.
 * The following methods control the way in which objects are drawn:
 * <ul>
 * <li> {@link #enableDoubleBuffering()}
 * <li> {@link #disableDoubleBuffering()}
 * <li> {@link #show()}
 * <li> {@link #pause(int t)}
 * </ul>
 * p>
 * By default, double buffering is disabled, which means that as soon as you
 * call a drawing
 * method—such as {@code point()} or {@code line()}—the
 * results appear on the screen.
 * p>
 * When double buffering is enabled by calling {@link #enableDoubleBuffering()},
 * all drawing takes place on the <em>offscreen canvas</em>. The offscreen canvas
 * is not displayed. Only when you call
 * {@link #show()} does your drawing get copied from the offscreen canvas to
 * the onscreen canvas, where it is displayed in the standard drawing window. You
 * can think of double buffering as collecting all of the lines, points, shapes,
 * and text that you tell it to draw, and then drawing them all
 * <em>simultaneously</em>, upon request.
 * p>
 * The most important use of double buffering is to produce computer
 * animations, creating the illusion of motion by rapidly
 * displaying static drawings. To produce an animation, repeat
 * the following four steps:
 * <ul>
 * <li> Clear the offscreen canvas.
 * <li> Draw objects on the offscreen canvas.
 * <li> Copy the offscreen canvas to the onscreen canvas.
 * <li> Wait for a short while.
 * </ul>
 * p>
 * The {@link #clear()}, {@link #show()}, and {@link #pause(int t)} methods
 * support the first, third, and fourth of these steps, respectively.
 * p>
 * For example, this code fragment animates two balls moving in a circle.
 * <pre>
 *  Draw.setScale(-2, +2);
 *  Draw.enableDoubleBuffering();
 *
 *  for (double t = 0.0; true; t += 0.02) {
 *      double x = Math.sin(t);
 *      double y = Math.cos(t);
 *      Draw.clear();
 *      Draw.filledCircle(x, y, 0.05);
 *      Draw.filledCircle(-x, -y, 0.05);
 *      Draw.show();
 *      Draw.pause(20);
 *  }
 * </pre>
 * p>
 * If the user types lots of keys, they will be saved in a list until you process them.
 * The first method tells you whether the user has typed a key (that your program has
 * not yet processed).
 * The second method returns the next key that the user typed (that your program has
 * not yet processed) and removes it from the list of saved keystrokes.
 * The third method tells you whether a key is currently being pressed.
 * p>
 * <b>Accessing control parameters.</b>
 * You can use the following methods to access the current pen color, pen radius,
 * and font:
 * <ul>
 * <li> {@link #getPenColor()}
 * <li> {@link #getPenRadius()}
 * <li> {@link #getFont()}
 * </ul>
 * p>
 * These methods are useful when you want to temporarily change a
 * control parameter and reset it back to its original value.
 * p>
 *
 * <b>Performance tricks.</b>
 * Standard drawing is capable of drawing large amounts of data.
 * Here are a few tricks and tips:
 * <ul>
 * <li> Use <em>double buffering</em> for static drawing with a large
 *      number of objects.
 *      That is, call {@link #enableDoubleBuffering()} before
 *      the sequence of drawing commands and call {@link #show()} afterwards.
 *      Incrementally displaying a complex drawing while it is being
 *      created can be intolerably inefficient on many computer systems.
 * <li> When drawing computer animations, call {@code show()}
 *      only once per frame, not after drawing each individual object.
 * <li> If you call {@code picture()} multiple times with the same filename,
 *      Java will cache the image, so you do not incur the cost of reading
 *      from a file each time.
 * </ul>
 */
public final class Draw implements ActionListener {

  /**
   * The color black.
   */
  public static final Color BLACK = Color.BLACK;

  /**
   * The color dark gray.
   */
  public static final Color DARK_GRAY = Color.DARK_GRAY;

  /**
   * The color gray.
   */
  public static final Color GRAY = Color.GRAY;

  /**
   * The color green.
   */
  public static final Color GREEN = Color.GREEN;

  /**
   * The color light gray.
   */
  public static final Color LIGHT_GRAY = Color.LIGHT_GRAY;


  /**
   * The color white.
   */
  public static final Color WHITE = Color.WHITE;

  /**
   * The color yellow.
   */
  public static final Color YELLOW = Color.YELLOW;

  /**
   * Shade of blue. The RGB values are approximately (9, 90, 166).
   */
  public static final Color BLUE = new Color(9, 90, 166);

  /**
   * Temple University cherry. The RGB values are approximately (157, 34, 53)
   */
  public static final Color TEMPLE_CHERRY = new Color(157, 34, 53);

  /**
   * Temple University silver. The RGB values are approximately (193, 198, 200)
   */
  public static final Color TEMPLE_SILVER = new Color(193, 198, 200);

  /**
   * Metallic silver. The RGB values are approximately (138, 141, 143)
   */
  public static final Color METALLIC_SILVER = new Color(138, 141, 143);

  /**
   * Shade of light blue. The RGB values are approximately (103, 198, 243).
   */
  public static final Color LIGHT_BLUE = new Color(103, 198, 243);

  /**
   * Shade of red. The RGB values are approximately (150, 35, 31).
   */
  public static final Color RED = new Color(150, 35, 31);

  /**
   * Shade of orange used in Princeton University's identity.
   * It is PMS 158. The RGB values are approximately (245, 128, 37).
   */
  public static final Color ORANGE = new Color(245, 128, 37);

  // default colors
  private static final Color DEFAULT_PEN_COLOR = BLACK;
  private static final Color DEFAULT_CLEAR_COLOR = WHITE;

  // current pen color
  private static Color penColor;

  // default canvas size is DEFAULT_SIZE-by-DEFAULT_SIZE
  private static final int DEFAULT_SIZE = 512;
  private static int width = DEFAULT_SIZE;
  private static int height = DEFAULT_SIZE;

  // default pen radius
  private static final double DEFAULT_PEN_RADIUS = 0.002;

  // current pen radius
  private static double penRadius;

  // show we draw immediately or wait until next show?
  private static boolean defer = false;

  // boundary of drawing canvas, 0% border
  // private static final double BORDER = 0.05;
  private static final double BORDER = 0.00;
  private static final double DEFAULT_XMIN = 0.0;
  private static final double DEFAULT_XMAX = 1.0;
  private static final double DEFAULT_YMIN = 0.0;
  private static final double DEFAULT_YMAX = 1.0;
  private static double xmin, ymin, xmax, ymax;

  // for synchronization
  private static Object mouseLock = new Object();
  private static Object keyLock = new Object();

  // default font
  private static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN, 16);

  // current font
  private static Font font;

  // double buffered graphics
  private static BufferedImage offscreenImage, onscreenImage;
  private static Graphics2D offscreen, onscreen;

  // singleton for callbacks: avoids generation of extra .class files
  private static Draw std = new Draw();

  // the frame for drawing to the screen
  private static JFrame frame;

  // mouse state
  private static boolean isMousePressed = false;
  private static double mouseX = 0;
  private static double mouseY = 0;

  // queue of typed key characters
  private static LinkedList<Character> keysTyped = new LinkedList<Character>();

  // set of key codes currently pressed down
  private static TreeSet<Integer> keysDown = new TreeSet<Integer>();

  // singleton pattern: client can't instantiate
  private Draw() {
  }


  // static initializer
  static {
    init();
  }

  /**
   * Sets the canvas (drawing area) to be 512-by-512 pixels.
   * This also erases the current drawing and resets the coordinate system,
   * pen radius, pen color, and font back to their default values.
   * Ordinarly, this method is called once, at the very beginning
   * of a program.
   */
  public static void setCanvasSize() {
    setCanvasSize(DEFAULT_SIZE, DEFAULT_SIZE);
  }

  /**
   * Sets the canvas (drawing area) to be <em>width</em>-by-<em>height</em> pixels.
   * This also erases the current drawing and resets the coordinate system,
   * pen radius, pen color, and font back to their default values.
   * Ordinarly, this method is called once, at the very beginning
   * of a program.
   *
   * @param canvasWidth  the width as a number of pixels
   * @param canvasHeight the height as a number of pixels
   * @throws IllegalArgumentException unless both {@code canvasWidth} and
   *                                  {@code canvasHeight} are positive
   */
  public static void setCanvasSize(int canvasWidth, int canvasHeight) {
    if (canvasWidth <= 0) throw new IllegalArgumentException("width must be positive");
    if (canvasHeight <= 0) throw new IllegalArgumentException("height must be positive");
    width = canvasWidth;
    height = canvasHeight;
    init();
  }

  // init
  private static void init() {
    if (frame != null) frame.setVisible(false);
    frame = new JFrame();
    offscreenImage = new BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_ARGB);
    onscreenImage = new BufferedImage(2 * width, 2 * height, BufferedImage.TYPE_INT_ARGB);
    offscreen = offscreenImage.createGraphics();
    onscreen = onscreenImage.createGraphics();
    offscreen.scale(2.0, 2.0);  // since we made it 2x as big

    setXscale();
    setYscale();
    offscreen.setColor(DEFAULT_CLEAR_COLOR);
    offscreen.fillRect(0, 0, width, height);
    setPenColor();
    setPenRadius();
    setFont();
    clear();

    // add antialiasing
    RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    offscreen.addRenderingHints(hints);

    // frame stuff
    RetinaImageIcon icon = new RetinaImageIcon(onscreenImage);
    JLabel draw = new JLabel(icon);

    frame.setContentPane(draw);
    frame.setFocusTraversalKeysEnabled(false);  // allow VK_TAB with isKeyPressed()
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // closes all windows
    // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      // closes only current window
    frame.setTitle("Draw");
    frame.setJMenuBar(createMenuBar());
    frame.pack();
    frame.requestFocusInWindow();
    frame.setVisible(true);
  }

  // create the menu bar (changed to private)
  private static JMenuBar createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("File");
    menuBar.add(menu);
    JMenuItem menuItem1 = new JMenuItem(" Save...   ");
    menuItem1.addActionListener(std);
    // Java 10+: replace getMenuShortcutKeyMask() with getMenuShortcutKeyMaskEx()
    menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
            Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
    menu.add(menuItem1);
    return menuBar;
  }

  /***************************************************************************
   *  User and screen coordinate systems.
   ***************************************************************************/

  // throw an IllegalArgumentException if x is NaN or infinite
  private static void validate(double x, String name) {
    if (Double.isNaN(x)) throw new IllegalArgumentException(name + " is NaN");
    if (Double.isInfinite(x)) throw new IllegalArgumentException(name + " is infinite");
  }

  // throw an IllegalArgumentException if s is null
  private static void validateNonnegative(double x, String name) {
    if (x < 0) throw new IllegalArgumentException(name + " negative");
  }

  // throw an IllegalArgumentException if s is null
  private static void validateNotNull(Object x, String name) {
    if (x == null) throw new IllegalArgumentException(name + " is null");
  }


  /**
   * Sets the <em>x</em>-scale to be the default (between 0.0 and 1.0).
   */
  public static void setXscale() {
    setXscale(DEFAULT_XMIN, DEFAULT_XMAX);
  }

  /**
   * Sets the <em>y</em>-scale to be the default (between 0.0 and 1.0).
   */
  public static void setYscale() {
    setYscale(DEFAULT_YMIN, DEFAULT_YMAX);
  }

  /**
   * Sets the <em>x</em>-scale and <em>y</em>-scale to be the default
   * (between 0.0 and 1.0).
   */
  public static void setScale() {
    setXscale();
    setYscale();
  }

  /**
   * Sets the <em>x</em>-scale to the specified range.
   *
   * @param min the minimum value of the <em>x</em>-scale
   * @param max the maximum value of the <em>x</em>-scale
   * @throws IllegalArgumentException if {@code (max == min)}
   * @throws IllegalArgumentException if either {@code min} or {@code max} is either NaN or infinite
   */
  public static void setXscale(double min, double max) {
    validate(min, "min");
    validate(max, "max");
    double size = max - min;
    if (size == 0.0) throw new IllegalArgumentException("the min and max are the same");
    synchronized (mouseLock) {
      xmin = min - BORDER * size;
      xmax = max + BORDER * size;
    }
  }

  /**
   * Sets the <em>y</em>-scale to the specified range.
   *
   * @param min the minimum value of the <em>y</em>-scale
   * @param max the maximum value of the <em>y</em>-scale
   * @throws IllegalArgumentException if {@code (max == min)}
   * @throws IllegalArgumentException if either {@code min} or {@code max} is either NaN or infinite
   */
  public static void setYscale(double min, double max) {
    validate(min, "min");
    validate(max, "max");
    double size = max - min;
    if (size == 0.0) throw new IllegalArgumentException("the min and max are the same");
    synchronized (mouseLock) {
      ymin = min - BORDER * size;
      ymax = max + BORDER * size;
    }
  }

  /**
   * Sets both the <em>x</em>-scale and <em>y</em>-scale to the (same) specified range.
   *
   * @param min the minimum value of the <em>x</em>- and <em>y</em>-scales
   * @param max the maximum value of the <em>x</em>- and <em>y</em>-scales
   * @throws IllegalArgumentException if {@code (max == min)}
   * @throws IllegalArgumentException if either {@code min} or {@code max} is either NaN or infinite
   */
  public static void setScale(double min, double max) {
    validate(min, "min");
    validate(max, "max");
    double size = max - min;
    if (size == 0.0) throw new IllegalArgumentException("the min and max are the same");
    synchronized (mouseLock) {
      xmin = min - BORDER * size;
      xmax = max + BORDER * size;
      ymin = min - BORDER * size;
      ymax = max + BORDER * size;
    }
  }

  // helper functions that scale from user coordinates to screen coordinates and back
  private static double scaleX(double x) {
    return width * (x - xmin) / (xmax - xmin);
  }

  private static double scaleY(double y) {
    return height * (ymax - y) / (ymax - ymin);
  }

  private static double factorX(double w) {
    return w * width / Math.abs(xmax - xmin);
  }

  private static double factorY(double h) {
    return h * height / Math.abs(ymax - ymin);
  }

  private static double userX(double x) {
    return xmin + x * (xmax - xmin) / width;
  }

  private static double userY(double y) {
    return ymax - y * (ymax - ymin) / height;
  }


  /**
   * Clears the screen to the default color (white).
   */
  public static void clear() {
    clear(DEFAULT_CLEAR_COLOR);
  }

  /**
   * Clears the screen to the specified color.
   *
   * @param color the color to make the background
   * @throws IllegalArgumentException if {@code color} is {@code null}
   */
  public static void clear(Color color) {
    validateNotNull(color, "color");
    offscreen.setColor(color);
    offscreen.fillRect(0, 0, width, height);
    offscreen.setColor(penColor);
    draw();
  }

  /**
   * Returns the current pen radius.
   *
   * @return the current value of the pen radius
   */
  public static double getPenRadius() {
    return penRadius;
  }

  /**
   * Sets the pen size to the default size (0.002).
   * The pen is circular, so that lines have rounded ends, and when you set the
   * pen radius and draw a point, you get a circle of the specified radius.
   * The pen radius is not affected by coordinate scaling.
   */
  public static void setPenRadius() {
    setPenRadius(DEFAULT_PEN_RADIUS);
  }

  /**
   * Sets the radius of the pen to the specified size.
   * The pen is circular, so that lines have rounded ends, and when you set the
   * pen radius and draw a point, you get a circle of the specified radius.
   * The pen radius is not affected by coordinate scaling.
   *
   * @param radius the radius of the pen
   * @throws IllegalArgumentException if {@code radius} is negative, NaN, or infinite
   */
  public static void setPenRadius(double radius) {
    validate(radius, "pen radius");
    validateNonnegative(radius, "pen radius");

    penRadius = radius;
    float scaledPenRadius = (float) (radius * DEFAULT_SIZE);
    BasicStroke stroke = new BasicStroke(scaledPenRadius, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
    // BasicStroke stroke = new BasicStroke(scaledPenRadius);
    offscreen.setStroke(stroke);
  }

  /**
   * Returns the current pen color.
   *
   * @return the current pen color
   */
  public static Color getPenColor() {
    return penColor;
  }

  /**
   * Sets the pen color to the default color (black).
   */
  public static void setPenColor() {
    setPenColor(DEFAULT_PEN_COLOR);
  }

  /**
   * Sets the pen color to the specified color.
   * <p>
   * The predefined pen colors are
   * {@code Draw.BLACK}, {@code Draw.BLUE}, {@code Draw.CYAN},
   * {@code Draw.DARK_GRAY}, {@code Draw.GRAY}, {@code Draw.GREEN},
   * {@code Draw.LIGHT_GRAY}, {@code Draw.MAGENTA}, {@code Draw.ORANGE},
   * {@code Draw.PINK}, {@code Draw.RED}, {@code Draw.WHITE}, and
   * {@code Draw.YELLOW}.
   *
   * @param color the color to make the pen
   * @throws IllegalArgumentException if {@code color} is {@code null}
   */
  public static void setPenColor(Color color) {
    validateNotNull(color, "color");
    penColor = color;
    offscreen.setColor(penColor);
  }

  /**
   * Sets the pen color to the specified RGB color.
   *
   * @param red   the amount of red (between 0 and 255)
   * @param green the amount of green (between 0 and 255)
   * @param blue  the amount of blue (between 0 and 255)
   * @throws IllegalArgumentException if {@code red}, {@code green},
   *                                  or {@code blue} is outside its prescribed range
   */
  public static void setPenColor(int red, int green, int blue) {
    if (red < 0 || red >= 256) throw new IllegalArgumentException("red must be between 0 and 255");
    if (green < 0 || green >= 256) throw new IllegalArgumentException("green must be between 0 and 255");
    if (blue < 0 || blue >= 256) throw new IllegalArgumentException("blue must be between 0 and 255");
    setPenColor(new Color(red, green, blue));
  }

  /**
   * Returns the current font.
   *
   * @return the current font
   */
  public static Font getFont() {
    return font;
  }

  /**
   * Sets the font to the default font (sans serif, 16 point).
   */
  public static void setFont() {
    setFont(DEFAULT_FONT);
  }

  /**
   * Sets the font to the specified value.
   *
   * @param font the font
   * @throws IllegalArgumentException if {@code font} is {@code null}
   */
  public static void setFont(Font font) {
    validateNotNull(font, "font");
    Draw.font = font;
  }


  /***************************************************************************
   *  Drawing geometric shapes.
   ***************************************************************************/

  /**
   * Draws a line segment between (<em>x</em><sub>0</sub>, <em>y</em><sub>0</sub>) and
   * (<em>x</em><sub>1</sub>, <em>y</em><sub>1</sub>).
   *
   * @param x0 the <em>x</em>-coordinate of one endpoint
   * @param y0 the <em>y</em>-coordinate of one endpoint
   * @param x1 the <em>x</em>-coordinate of the other endpoint
   * @param y1 the <em>y</em>-coordinate of the other endpoint
   * @throws IllegalArgumentException if any coordinate is either NaN or infinite
   */
  public static void line(double x0, double y0, double x1, double y1) {
    validate(x0, "x0");
    validate(y0, "y0");
    validate(x1, "x1");
    validate(y1, "y1");
    offscreen.draw(new Line2D.Double(scaleX(x0), scaleY(y0), scaleX(x1), scaleY(y1)));
    draw();
  }

  /**
   * Draws one pixel at (<em>x</em>, <em>y</em>).
   * This method is private because pixels depend on the display.
   * To achieve the same effect, set the pen radius to 0 and call {@code point()}.
   *
   * @param x the <em>x</em>-coordinate of the pixel
   * @param y the <em>y</em>-coordinate of the pixel
   * @throws IllegalArgumentException if {@code x} or {@code y} is either NaN or infinite
   */
  private static void pixel(double x, double y) {
    validate(x, "x");
    validate(y, "y");
    offscreen.fillRect((int) Math.round(scaleX(x)), (int) Math.round(scaleY(y)), 1, 1);
  }

  /**
   * Draws a point centered at (<em>x</em>, <em>y</em>).
   * The point is a filled circle whose radius is equal to the pen radius.
   * To draw a single-pixel point, first set the pen radius to 0.
   *
   * @param x the <em>x</em>-coordinate of the point
   * @param y the <em>y</em>-coordinate of the point
   * @throws IllegalArgumentException if either {@code x} or {@code y} is either NaN or infinite
   */
  public static void point(double x, double y) {
    validate(x, "x");
    validate(y, "y");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double r = penRadius;
    float scaledPenRadius = (float) (r * DEFAULT_SIZE);

    // double ws = factorX(2*r);
    // double hs = factorY(2*r);
    // if (ws <= 1 && hs <= 1) pixel(x, y);
    if (scaledPenRadius <= 1) pixel(x, y);
    else offscreen.fill(new Ellipse2D.Double(xs - scaledPenRadius / 2, ys - scaledPenRadius / 2,
            scaledPenRadius, scaledPenRadius));
    draw();
  }

  /**
   * Draws a circle of the specified radius, centered at (<em>x</em>, <em>y</em>).
   *
   * @param x      the <em>x</em>-coordinate of the center of the circle
   * @param y      the <em>y</em>-coordinate of the center of the circle
   * @param radius the radius of the circle
   * @throws IllegalArgumentException if {@code radius} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void circle(double x, double y, double radius) {
    validate(x, "x");
    validate(y, "y");
    validate(radius, "radius");
    validateNonnegative(radius, "radius");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * radius);
    double hs = factorY(2 * radius);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.draw(new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    draw();
  }

  /**
   * Draws a filled circle of the specified radius, centered at (<em>x</em>, <em>y</em>).
   *
   * @param x      the <em>x</em>-coordinate of the center of the circle
   * @param y      the <em>y</em>-coordinate of the center of the circle
   * @param radius the radius of the circle
   * @throws IllegalArgumentException if {@code radius} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void filledCircle(double x, double y, double radius) {
    validate(x, "x");
    validate(y, "y");
    validate(radius, "radius");
    validateNonnegative(radius, "radius");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * radius);
    double hs = factorY(2 * radius);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.fill(new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    draw();
  }


  /**
   * Draws an ellipse with the specified semimajor and semiminor axes,
   * centered at (<em>x</em>, <em>y</em>).
   *
   * @param x             the <em>x</em>-coordinate of the center of the ellipse
   * @param y             the <em>y</em>-coordinate of the center of the ellipse
   * @param semiMajorAxis is the semimajor axis of the ellipse
   * @param semiMinorAxis is the semiminor axis of the ellipse
   * @throws IllegalArgumentException if either {@code semiMajorAxis}
   *                                  or {@code semiMinorAxis} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void ellipse(double x, double y, double semiMajorAxis, double semiMinorAxis) {
    validate(x, "x");
    validate(y, "y");
    validate(semiMajorAxis, "semimajor axis");
    validate(semiMinorAxis, "semiminor axis");
    validateNonnegative(semiMajorAxis, "semimajor axis");
    validateNonnegative(semiMinorAxis, "semiminor axis");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * semiMajorAxis);
    double hs = factorY(2 * semiMinorAxis);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.draw(new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    draw();
  }

  /**
   * Draws a filled ellipse with the specified semimajor and semiminor axes,
   * centered at (<em>x</em>, <em>y</em>).
   *
   * @param x             the <em>x</em>-coordinate of the center of the ellipse
   * @param y             the <em>y</em>-coordinate of the center of the ellipse
   * @param semiMajorAxis is the semimajor axis of the ellipse
   * @param semiMinorAxis is the semiminor axis of the ellipse
   * @throws IllegalArgumentException if either {@code semiMajorAxis}
   *                                  or {@code semiMinorAxis} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void filledEllipse(double x, double y, double semiMajorAxis, double semiMinorAxis) {
    validate(x, "x");
    validate(y, "y");
    validate(semiMajorAxis, "semimajor axis");
    validate(semiMinorAxis, "semiminor axis");
    validateNonnegative(semiMajorAxis, "semimajor axis");
    validateNonnegative(semiMinorAxis, "semiminor axis");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * semiMajorAxis);
    double hs = factorY(2 * semiMinorAxis);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.fill(new Ellipse2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    draw();
  }


  /**
   * Draws a circular arc of the specified radius,
   * centered at (<em>x</em>, <em>y</em>), from angle1 to angle2 (in degrees).
   *
   * @param x      the <em>x</em>-coordinate of the center of the circle
   * @param y      the <em>y</em>-coordinate of the center of the circle
   * @param radius the radius of the circle
   * @param angle1 the starting angle. 0 would mean an arc beginning at 3 o'clock.
   * @param angle2 the angle at the end of the arc. For example, if
   *               you want a 90 degree arc, then angle2 should be angle1 + 90.
   * @throws IllegalArgumentException if {@code radius} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void arc(double x, double y, double radius, double angle1, double angle2) {
    validate(x, "x");
    validate(y, "y");
    validate(radius, "arc radius");
    validate(angle1, "angle1");
    validate(angle2, "angle2");
    validateNonnegative(radius, "arc radius");

    while (angle2 < angle1) angle2 += 360;
    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * radius);
    double hs = factorY(2 * radius);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.draw(new Arc2D.Double(xs - ws / 2, ys - hs / 2, ws, hs, angle1, angle2 - angle1, Arc2D.OPEN));
    draw();
  }

  /**
   * Draws a square of the specified size, centered at (<em>x</em>, <em>y</em>).
   *
   * @param x          the <em>x</em>-coordinate of the center of the square
   * @param y          the <em>y</em>-coordinate of the center of the square
   * @param halfLength one half the length of any side of the square
   * @throws IllegalArgumentException if {@code halfLength} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void square(double x, double y, double halfLength) {
    validate(x, "x");
    validate(y, "y");
    validate(halfLength, "halfLength");
    validateNonnegative(halfLength, "half length");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * halfLength);
    double hs = factorY(2 * halfLength);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.draw(new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    draw();
  }

  /**
   * Draws a filled square of the specified size, centered at (<em>x</em>, <em>y</em>).
   *
   * @param x          the <em>x</em>-coordinate of the center of the square
   * @param y          the <em>y</em>-coordinate of the center of the square
   * @param halfLength one half the length of any side of the square
   * @throws IllegalArgumentException if {@code halfLength} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void filledSquare(double x, double y, double halfLength) {
    validate(x, "x");
    validate(y, "y");
    validate(halfLength, "halfLength");
    validateNonnegative(halfLength, "half length");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * halfLength);
    double hs = factorY(2 * halfLength);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.fill(new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    draw();
  }


  /**
   * Draws a rectangle of the specified size, centered at (<em>x</em>, <em>y</em>).
   *
   * @param x          the <em>x</em>-coordinate of the center of the rectangle
   * @param y          the <em>y</em>-coordinate of the center of the rectangle
   * @param halfWidth  one half the width of the rectangle
   * @param halfHeight one half the height of the rectangle
   * @throws IllegalArgumentException if either {@code halfWidth} or {@code halfHeight} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void rectangle(double x, double y, double halfWidth, double halfHeight) {
    validate(x, "x");
    validate(y, "y");
    validate(halfWidth, "halfWidth");
    validate(halfHeight, "halfHeight");
    validateNonnegative(halfWidth, "half width");
    validateNonnegative(halfHeight, "half height");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * halfWidth);
    double hs = factorY(2 * halfHeight);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.draw(new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    draw();
  }

  /**
   * Draws a filled rectangle of the specified size, centered at (<em>x</em>, <em>y</em>).
   *
   * @param x          the <em>x</em>-coordinate of the center of the rectangle
   * @param y          the <em>y</em>-coordinate of the center of the rectangle
   * @param halfWidth  one half the width of the rectangle
   * @param halfHeight one half the height of the rectangle
   * @throws IllegalArgumentException if either {@code halfWidth} or {@code halfHeight} is negative
   * @throws IllegalArgumentException if any argument is either NaN or infinite
   */
  public static void filledRectangle(double x, double y, double halfWidth, double halfHeight) {
    validate(x, "x");
    validate(y, "y");
    validate(halfWidth, "halfWidth");
    validate(halfHeight, "halfHeight");
    validateNonnegative(halfWidth, "half width");
    validateNonnegative(halfHeight, "half height");

    double xs = scaleX(x);
    double ys = scaleY(y);
    double ws = factorX(2 * halfWidth);
    double hs = factorY(2 * halfHeight);
    if (ws <= 1 && hs <= 1) pixel(x, y);
    else offscreen.fill(new Rectangle2D.Double(xs - ws / 2, ys - hs / 2, ws, hs));
    draw();
  }


  /**
   * Draws a polygon with the vertices
   * (<em>x</em><sub>0</sub>, <em>y</em><sub>0</sub>),
   * (<em>x</em><sub>1</sub>, <em>y</em><sub>1</sub>), ...,
   * (<em>x</em><sub><em>n</em>–1</sub>, <em>y</em><sub><em>n</em>–1</sub>).
   *
   * @param x an array of all the <em>x</em>-coordinates of the polygon
   * @param y an array of all the <em>y</em>-coordinates of the polygon
   * @throws IllegalArgumentException unless {@code x[]} and {@code y[]}
   *                                  are of the same length
   * @throws IllegalArgumentException if any coordinate is either NaN or infinite
   * @throws IllegalArgumentException if either {@code x[]} or {@code y[]} is {@code null}
   */
  public static void polygon(double[] x, double[] y) {
    validateNotNull(x, "x-coordinate array");
    validateNotNull(y, "y-coordinate array");
    for (int i = 0; i < x.length; i++) validate(x[i], "x[" + i + "]");
    for (int i = 0; i < y.length; i++) validate(y[i], "y[" + i + "]");

    int n1 = x.length;
    int n2 = y.length;
    if (n1 != n2) throw new IllegalArgumentException("arrays must be of the same length");
    int n = n1;
    if (n == 0) return;

    GeneralPath path = new GeneralPath();
    path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));
    for (int i = 0; i < n; i++)
      path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
    path.closePath();
    offscreen.draw(path);
    draw();
  }

  /**
   * Draws a filled polygon with the vertices
   * (<em>x</em><sub>0</sub>, <em>y</em><sub>0</sub>),
   * (<em>x</em><sub>1</sub>, <em>y</em><sub>1</sub>), ...,
   * (<em>x</em><sub><em>n</em>–1</sub>, <em>y</em><sub><em>n</em>–1</sub>).
   *
   * @param x an array of all the <em>x</em>-coordinates of the polygon
   * @param y an array of all the <em>y</em>-coordinates of the polygon
   * @throws IllegalArgumentException unless {@code x[]} and {@code y[]}
   *                                  are of the same length
   * @throws IllegalArgumentException if any coordinate is either NaN or infinite
   * @throws IllegalArgumentException if either {@code x[]} or {@code y[]} is {@code null}
   */
  public static void filledPolygon(double[] x, double[] y) {
    validateNotNull(x, "x-coordinate array");
    validateNotNull(y, "y-coordinate array");
    for (int i = 0; i < x.length; i++) validate(x[i], "x[" + i + "]");
    for (int i = 0; i < y.length; i++) validate(y[i], "y[" + i + "]");

    int n1 = x.length;
    int n2 = y.length;
    if (n1 != n2) throw new IllegalArgumentException("arrays must be of the same length");
    int n = n1;
    if (n == 0) return;

    GeneralPath path = new GeneralPath();
    path.moveTo((float) scaleX(x[0]), (float) scaleY(y[0]));
    for (int i = 0; i < n; i++)
      path.lineTo((float) scaleX(x[i]), (float) scaleY(y[i]));
    path.closePath();
    offscreen.fill(path);
    draw();
  }


  /***************************************************************************
   *  Drawing text.
   ***************************************************************************/

  /**
   * Writes the given text string in the current font, centered at (<em>x</em>, <em>y</em>).
   *
   * @param x    the center <em>x</em>-coordinate of the text
   * @param y    the center <em>y</em>-coordinate of the text
   * @param text the text to write
   * @throws IllegalArgumentException if {@code text} is {@code null}
   * @throws IllegalArgumentException if {@code x} or {@code y} is either NaN or infinite
   */
  public static void text(double x, double y, String text) {
    validate(x, "x");
    validate(y, "y");
    validateNotNull(text, "text");

    offscreen.setFont(font);
    FontMetrics metrics = offscreen.getFontMetrics();
    double xs = scaleX(x);
    double ys = scaleY(y);
    int ws = metrics.stringWidth(text);
    int hs = metrics.getDescent();
    offscreen.drawString(text, (float) (xs - ws / 2.0), (float) (ys + hs));
    draw();
  }

  /**
   * Writes the given text string in the current font, centered at (<em>x</em>, <em>y</em>) and
   * rotated by the specified number of degrees.
   *
   * @param x       the center <em>x</em>-coordinate of the text
   * @param y       the center <em>y</em>-coordinate of the text
   * @param text    the text to write
   * @param degrees is the number of degrees to rotate counterclockwise
   * @throws IllegalArgumentException if {@code text} is {@code null}
   * @throws IllegalArgumentException if {@code x}, {@code y}, or {@code degrees} is either NaN or infinite
   */
  public static void text(double x, double y, String text, double degrees) {
    validate(x, "x");
    validate(y, "y");
    validate(degrees, "degrees");
    validateNotNull(text, "text");

    double xs = scaleX(x);
    double ys = scaleY(y);
    offscreen.rotate(Math.toRadians(-degrees), xs, ys);
    text(x, y, text);
    offscreen.rotate(Math.toRadians(+degrees), xs, ys);
  }


  /**
   * Writes the given text string in the current font, left-aligned at (<em>x</em>, <em>y</em>).
   *
   * @param x    the <em>x</em>-coordinate of the text
   * @param y    the <em>y</em>-coordinate of the text
   * @param text the text
   * @throws IllegalArgumentException if {@code text} is {@code null}
   * @throws IllegalArgumentException if {@code x} or {@code y} is either NaN or infinite
   */
  public static void textLeft(double x, double y, String text) {
    validate(x, "x");
    validate(y, "y");
    validateNotNull(text, "text");

    offscreen.setFont(font);
    FontMetrics metrics = offscreen.getFontMetrics();
    double xs = scaleX(x);
    double ys = scaleY(y);
    int hs = metrics.getDescent();
    offscreen.drawString(text, (float) xs, (float) (ys + hs));
    draw();
  }

  /**
   * Writes the given text string in the current font, right-aligned at (<em>x</em>, <em>y</em>).
   *
   * @param x    the <em>x</em>-coordinate of the text
   * @param y    the <em>y</em>-coordinate of the text
   * @param text the text to write
   * @throws IllegalArgumentException if {@code text} is {@code null}
   * @throws IllegalArgumentException if {@code x} or {@code y} is either NaN or infinite
   */
  public static void textRight(double x, double y, String text) {
    validate(x, "x");
    validate(y, "y");
    validateNotNull(text, "text");

    offscreen.setFont(font);
    FontMetrics metrics = offscreen.getFontMetrics();
    double xs = scaleX(x);
    double ys = scaleY(y);
    int ws = metrics.stringWidth(text);
    int hs = metrics.getDescent();
    offscreen.drawString(text, (float) (xs - ws), (float) (ys + hs));
    draw();
  }


  /**
   * Pauses for t milliseconds. This method is intended to support computer animations.
   *
   * @param t number of milliseconds
   */
  public static void pause(int t) {
    validateNonnegative(t, "t");
    try {
      Thread.sleep(t);
    } catch (InterruptedException e) {
      System.out.println("Error sleeping");
    }
  }

  /**
   * Copies offscreen buffer to onscreen buffer. There is no reason to call
   * this method unless double buffering is enabled.
   */
  public static void show() {
    onscreen.drawImage(offscreenImage, 0, 0, null);
    frame.repaint();
  }

  // draw onscreen if defer is false
  private static void draw() {
    if (!defer) show();
  }

  /**
   * Enables double buffering. All subsequent calls to
   * drawing methods such as {@code line()}, {@code circle()},
   * and {@code square()} will be deferred until the next call
   * to show(). Useful for animations.
   */
  public static void enableDoubleBuffering() {
    defer = true;
  }

  /**
   * Disables double buffering. All subsequent calls to
   * drawing methods such as {@code line()}, {@code circle()},
   * and {@code square()} will be displayed on screen when called.
   * This is the default.
   */
  public static void disableDoubleBuffering() {
    defer = false;
  }


  /***************************************************************************
   *  Save drawing to a file.
   ***************************************************************************/

  /**
   * Saves the drawing to using the specified filename.
   * The supported image formats are JPEG and PNG;
   * the filename suffix must be {@code .jpg} or {@code .png}.
   *
   * @param filename the name of the file with one of the required suffixes
   * @throws IllegalArgumentException if {@code filename} is {@code null}
   */
  public static void save(String filename) {
    validateNotNull(filename, "filename");
    File file = new File(filename);
    String suffix = filename.substring(filename.lastIndexOf('.') + 1);

    // png files
    if ("png".equalsIgnoreCase(suffix)) {
      try {
        ImageIO.write(onscreenImage, suffix, file);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // need to change from ARGB to RGB for JPEG
    // reference: http://archives.java.sun.com/cgi-bin/wa?A2=ind0404&L=java2d-interest&D=0&P=2727
    else if ("jpg".equalsIgnoreCase(suffix)) {
      WritableRaster raster = onscreenImage.getRaster();
      WritableRaster newRaster;
      newRaster = raster.createWritableChild(0, 0, width, height, 0, 0, new int[]{0, 1, 2});
      DirectColorModel cm = (DirectColorModel) onscreenImage.getColorModel();
      DirectColorModel newCM = new DirectColorModel(cm.getPixelSize(),
              cm.getRedMask(),
              cm.getGreenMask(),
              cm.getBlueMask());
      BufferedImage rgbBuffer = new BufferedImage(newCM, newRaster, false, null);
      try {
        ImageIO.write(rgbBuffer, suffix, file);
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("Invalid image file type: " + suffix);
    }
  }


  /**
   * This method cannot be called directly.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    FileDialog chooser = new FileDialog(Draw.frame, "Use a .png or .jpg extension", FileDialog.SAVE);
    chooser.setVisible(true);
    String filename = chooser.getFile();
    if (filename != null) {
      Draw.save(chooser.getDirectory() + File.separator + chooser.getFile());
    }
  }


  /***************************************************************************
   *  For improved resolution on Mac Retina displays.
   ***************************************************************************/

  private static class RetinaImageIcon extends ImageIcon {

    public RetinaImageIcon(Image image) {
      super(image);
    }

    public int getIconWidth() {
      return super.getIconWidth() / 2;
    }

    /**
     * Gets the height of the icon.
     *
     * @return the height in pixels of this icon
     */
    public int getIconHeight() {
      return super.getIconHeight() / 2;
    }

    public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
      Graphics2D g2 = (Graphics2D) g.create();
      g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
      g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.scale(0.5, 0.5);
      super.paintIcon(c, g2, x * 2, y * 2);
      g2.dispose();
    }
  }


  /**
   * Test client.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    Draw.square(0.2, 0.8, 0.1);
    Draw.filledSquare(0.8, 0.8, 0.2);
    Draw.circle(0.8, 0.2, 0.2);

    Draw.setPenColor(Draw.RED);
    Draw.setPenRadius(0.02);
    Draw.arc(0.8, 0.2, 0.1, 200, 45);

    // draw a blue diamond
    Draw.setPenRadius();
    Draw.setPenColor(Draw.BLUE);
    double[] x = {0.1, 0.2, 0.3, 0.2};
    double[] y = {0.2, 0.3, 0.2, 0.1};
    Draw.filledPolygon(x, y);

    // text
    Draw.setPenColor(Draw.BLACK);
    Draw.text(0.2, 0.5, "black text");
    Draw.setPenColor(Draw.WHITE);
    Draw.text(0.8, 0.8, "white text");
  }
}