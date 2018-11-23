/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidsPackage;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Dobermann
 */
public class Asteroid extends Element{
    int stage;
    public Asteroid(double x, double y, double vx, double vy, double ray, boolean isAlive, int stage) {
        super(x, y, vx, vy, ray, isAlive);
        this.stage = stage;
    }
    @Override
    public synchronized void show(Graphics2D g2) {
        g2.setColor(Color.PINK);
        g2.fillOval((int) x, (int) y, (int) ray, (int) ray);
    }
    
    public void explodeAsteroid() {
        switch (this.stage) {
            case 1:
                Universe.getInstance().addElement(new Asteroid(this.x, this.y, this.vx * Math.random(), this.vy * Math.random(), this.ray / 2, true, 2));
                Universe.getInstance().addElement(new Asteroid(this.x, this.y, this.vx * Math.random(), this.vy * Math.random(), this.ray / 2, true, 2));
                Game.getInstance().addScore(this.stage);
                break;

            case 2:
                Universe.getInstance().addElement(new Asteroid(this.x, this.y, this.vx * Math.random(), this.vy * Math.random(), this.ray / 2, true, 3));
                Universe.getInstance().addElement(new Asteroid(this.x, this.y, this.vx * Math.random(), this.vy * Math.random(), this.ray / 2, true, 3));
                Game.getInstance().addScore(this.stage);
                break;
        }
    }
}
