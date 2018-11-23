package asteroidsPackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Dobermann
 */
public class Ship extends Element implements KeyListener {

    double angle;
    static int dtMin = 1; //tempo t entre tiros
    int life;
    double delV = 0.2;
    double delvX;
    double delvY;
    double MAX_VEL = 5;
    double linearVel = 0;
    int timeToShoot = 10;
    boolean imune = true;
    long t = 0;
    

    boolean keyRightPressed = false;
    boolean keyLeftPressed = false;
    boolean keyUpPressed = false;
    boolean spaceBarPressed = false;

    public Ship(double angle, int life, double x, double y, double vx, double vy, double ray, boolean isAlive) {
        super(x, y, vx, vy, ray, isAlive);
        this.angle = angle;
        this.life = life;
        xCenter = (int) (x - ray);
        yCenter = (int) (y - ray);
        
    }

    @Override
    public void timelapse() {
        timeToShoot++;
        if (keyRightPressed) {
            angle += 5;
        } else if (keyLeftPressed) {
            angle -= 5;
        }
        if (keyUpPressed) {
            double vel = Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2));
            delvX = delV * Math.cos(Math.toRadians(angle));
            delvY = delV * Math.sin(Math.toRadians(angle));
            if (vel < MAX_VEL) {
                vx += delvX;
                vy += delvY;
            }
            vx *= 0.98;
            vy *= 0.98;
        }
        xCenter = (int) (x - ray);
        yCenter = (int) (y - ray);

        if (spaceBarPressed && timeToShoot > 5) {
           
            shoot();
            timeToShoot = 0;
        }

    }

    //ta escroto ainda
    void shoot() {
        double mvx = vx * Math.cos(Math.toRadians(angle));
        double mvy = vy * Math.sin(Math.toRadians(angle));
        Missile m = new Missile(xCenter, yCenter, mvx, mvx, 5, isAlive, angle);
        Universe.getInstance().addElement(m);
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                keyRightPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                keyLeftPressed = true;
                break;
            case KeyEvent.VK_UP:
                keyUpPressed = true;
                break;
            case KeyEvent.VK_SPACE:
                spaceBarPressed = true;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        switch (ke.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                keyRightPressed = false;
                break;
            case KeyEvent.VK_LEFT:
                keyLeftPressed = false;
                break;
            case KeyEvent.VK_UP:
                keyUpPressed = false;
                break;
            case KeyEvent.VK_SPACE:
                spaceBarPressed = false;
                break;
        }
    }

    @Override
    public void show(Graphics2D g2) {
      //  System.out.println(imune);
        if(imune){
            g2.setColor(Color.cyan);
        }else{
            g2.setColor(Color.red);
        }
                
        g2.rotate(Math.toRadians(angle), xCenter, yCenter);
        
        g2.drawLine((int) xCenter + 15, (int) yCenter, (int) xCenter - 15, (int) yCenter + 10);
        g2.drawLine((int) xCenter + 15, (int) yCenter, (int) xCenter - 15, (int) yCenter - 10);
        g2.drawLine((int) xCenter - 15, (int) yCenter - 10, (int) xCenter - 15, (int) yCenter + 10);
        
        g2.rotate(Math.toRadians(-angle), xCenter, yCenter);   
    }
    
    //Getters and setters
    public boolean isImune() {
        return imune;
    }

    public void setImune() {
        long universT = Universe.getInstance().getT();
        if(this.imune && universT - t > 40){
            t = Universe.getInstance().getT();
            this.imune = false;
        }else {
            this.imune = true;
        }
    } 
}
