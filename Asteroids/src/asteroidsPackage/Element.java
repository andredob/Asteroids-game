/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidsPackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

/**
 *
 * @author Dobermann
 */
public abstract class Element {

    double x, y, vx, vy, ray;
    boolean isAlive;
    int xCenter;
    int yCenter;

    public Element(double x, double y, double vx, double vy, double ray, boolean isAlive) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.ray = ray;
        this.isAlive = isAlive;
        xCenter = (int) (x + ray);
        yCenter = (int) (y + ray);
    }

    public synchronized void show(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillOval((int) x, (int) y, (int) ray, (int) ray);
    }

    public void timelapse() {
    }

    public void move() {
        if (x - ray > Universe.getInstance().getWidth()) {
            x = -ray;
        } else if (y - ray > Universe.getInstance().getHeight()) {
            y = -ray;
        }
        if (x + ray < 0) {
            x = Universe.getInstance().getWidth() + ray;
        } else if (y + ray < 0) {
            y = Universe.getInstance().getHeight() + ray;
        } else {
            x += vx;
            y += vy;
        }
    }

    public boolean collision(Element other) {
        double dx = Math.abs(x - (other.xCenter));
        double dy = Math.abs(y - (other.yCenter));
        double d = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        System.out.println(d);
        if (d <= ((ray) + (other.ray))) {
            return true;
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getRay() {
        return ray;
    }

    public void setRay(double ray) {
        this.ray = ray;
    }

    public boolean isIsAlive() {
        return isAlive;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public int getxCenter() {
        return xCenter;
    }

    public void setxCenter(int xCenter) {
        this.xCenter = xCenter;
    }

    public int getyCenter() {
        return yCenter;
    }

    public void setyCenter(int yCenter) {
        this.yCenter = yCenter;
    }

}
