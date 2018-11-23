package asteroidsPackage;

/**
 *
 * @author Dobermann
 */
public class Missile extends Element {

    long t;
    double angle;

    public Missile(double x, double y, double vx, double vy, double ray, boolean isAlive, double angle) {
        super(x, y, 0, 0, ray, isAlive);
        this.t = 0;
        this.angle = angle;
        xCenter = (int) (x - ray);
        yCenter = (int) (y - ray);
        delvX = Math.cos(Math.toRadians(angle));
        delvY = Math.sin(Math.toRadians(angle));
    }

    double delvX;
    double delvY;
    double delV = 0.90;

    @Override
    public void timelapse() {
        t++;
        
        if (vx > -10 && vx < 10) {
            vx += delvX;
        }
        if (vy > -10 && vy < 10) {
            vy += delvY;
        }

        xCenter = (int) (x - ray);
        yCenter = (int) (y - ray);

        if (t > 20) {
            isAlive = false;
        }
    }

}
