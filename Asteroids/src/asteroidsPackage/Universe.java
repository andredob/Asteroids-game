package asteroidsPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Dobermann
 */
public class Universe extends JPanel implements Runnable {

    List<Element> ElementsList;
    long t = 0;
    Ship ship = new Ship(0, 3, 500 / 2 - 20, 500 / 2 - 20, 0, 0, 20, true);

    private static Universe singleton;

    public static synchronized Universe getInstance() {
        if (singleton == null) {
            singleton = new Universe();
        }
        return singleton;
    }

    private Universe() {
        ElementsList = new ArrayList<>();
        this.addKeyListener(ship);
        this.setFocusable(true);
    }

    @Override
    public synchronized void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int w = this.getWidth();
        int h = this.getHeight();
        String drawScore = "Score: " + Game.getInstance().score;
        String drawLives = "Lives: " + Game.getInstance().lives;
        
        g2.setColor(Color.black);
        g2.fillRect(0, 0, w, h);
        ship.show(g2);
        for (Element element : ElementsList) {
            element.show(g2);
        }
        
        //Draw Score
        g2.setColor(Color.green);
        g2.drawString( drawScore, 5, 20);
        
        //Draw Lives
        g2.drawString(drawLives, this.getWidth() -50, 20 );
        
        

    }

    public synchronized void addElement(Element E) {
        ElementsList.add(E);
    }

    synchronized void update() {
        t++;
        ship.timelapse();
        ship.move();
        boolean newFase = true;
        for (Element element : ElementsList) {
            if (element.isAlive) {

                element.timelapse();
                element.move();
                if (element instanceof Asteroid && element.collision(ship)) {
                    System.out.println("colidiu" + t);
                    int lives = Game.getInstance().getLives();
                    if (!ship.isImune()) {
                        Game.getInstance().setLives(lives - 1);
                    }
                    System.out.println(lives);
                    ship.setImune();
                }
                if (element instanceof Missile) {
                    for (Element asteroid : ElementsList) {
                        if (asteroid instanceof Asteroid) {
                            if (asteroid.collision(element)) {
                                ((Asteroid) asteroid).isAlive = false;
                                ((Missile) element).isAlive = false;
                                //System.out.println("explodiu!!");
                            }
                        }
                    }
                }
                if (element instanceof Asteroid) {
                    newFase = false;
                }
            }
        }

        for (Element element1 : ElementsList) {
            if (!element1.isAlive) {
                if (element1 instanceof Asteroid) {
                    ((Asteroid) element1).explodeAsteroid();
                }
                ElementsList.remove(element1);
                break;
            }
        }
        if (newFase) {
            Game.getInstance().newFase();
        }
        repaint();
    }

    

    @Override
    public void run() {
        while (!Game.getInstance().isGameOver()) {
            update();
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(Universe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Perdeu otário!!!");
    }

    public List<Element> getElementsList() {
        return ElementsList;
    }

    public void setElementsList(List<Element> ElementsList) {
        this.ElementsList = ElementsList;
    }

    public long getT() {
        return t;
    }

    public void setT(long t) {
        this.t = t;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public static Universe getSingleton() {
        return singleton;
    }

    public static void setSingleton(Universe singleton) {
        Universe.singleton = singleton;
    }

}
