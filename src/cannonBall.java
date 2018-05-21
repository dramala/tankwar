import org.newdawn.slick.geom.Circle;


public class cannonBall extends Circle {


    private float xCoord;
    private float yCoord;
    private boolean move;
    private double angle;
private boolean visible;

    public cannonBall(float centerPointX, float centerPointY, float radius) {
        super(centerPointX, centerPointY, radius);
        xCoord=centerPointX;
        yCoord=centerPointY;
    }


    public void move(double angle) {

        double xChange = Math.cos(Math.toRadians(angle));
        double yChange = Math.sin(Math.toRadians(angle));

        xCoord -= xChange * 4.1f;
        yCoord -= yChange * 4.1f;


    }
    public boolean getMove(){
        return move;
    }

    public void setMove(boolean change){
        move=change;

    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public float getxCoord(){
        return xCoord;
    }
    public float getyCoord(){
        return yCoord;
    }

    public void setxCoord(float xCoord1) {
        xCoord=xCoord1;
    }
    public void setyCoord(float yCoord1) {
        yCoord=yCoord1;
    }


    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isMove() {
        return move;
    }
}
