/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asteroidsPackage;

import java.awt.Dimension;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Dobermann
 */
public class Game {

    int lives = 3;
    Ship ship;
    int w = 500;
    int h = 500;
    int score = 0;
    boolean gameOver = false;

    private static Game singleton;

    public static synchronized Game getInstance() {
        if (singleton == null) {
            singleton = new Game();
        }
        return singleton;
    }

    public Game() {
        JFrame jf = new JFrame();
        jf.setSize(new Dimension(w, h));
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Random r = new Random(0);
        Universe u = Universe.getInstance();
        jf.getContentPane().add(u);
        jf.setVisible(true);
        Thread t = new Thread(u);
        t.start();
    }

    //not functioning
    public void newFase() {
        int notX = (int) Universe.getInstance().ship.getX();
        int notY = (int) Universe.getInstance().ship.getY();

        ship = Universe.getInstance().ship;
        for (int i = 0; i < 1; i++) {
            double x = Math.random() * w;
            double y = Math.random() * h;
            double vx = Math.random() * 5;
            double vy = Math.random() * 5;
            double ray = 100;
            boolean isAlive = true;
            Asteroid a = new Asteroid(x, y, vx, vy, ray, isAlive, 1);
            if (!inRay(a.getxCenter(), a.getyCenter(), notX, notY)) {
                Universe.getInstance().addElement(a);
            } else {
                System.out.println("In ray");
                i--;
            }
        }
    }

    boolean inRay(int x, int y, int notX, int notY) {
        if (notX + x < 50 && notX - x > 50) {
            return true;
        }
        if (notY + x < 50 && notY - x > 50) {
            return true;
        }
        return false;
    }

    void addScore(int stage) {
        switch (stage) {
            case 1:
                this.score += 30;
                break;
            case 2:
                this.score += 50;
                break;
        }
    }

    //Getters and setters
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
        if (this.lives == 0) {
            this.gameOver = true;
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

}
