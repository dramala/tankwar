import org.newdawn.slick.geom.Circle;


public class cannonBall extends Circle {

    private double rotation;
    private boolean visible;

    public cannonBall(float centerPointX, float centerPointY, float radius, double rotation) {
        super(centerPointX, centerPointY, radius);
        this.rotation = rotation;
        this.visible = true;
    }

    public void move() {
        double xChange = Math.cos(Math.toRadians(rotation));
        double yChange = Math.sin(Math.toRadians(rotation));

        this.setCenterX(getCenterX() - (float) xChange * 3.5f);
        this.setCenterY(getCenterY() - (float) yChange * 3.5f);
    }

    public boolean getVisible(){
        return this.visible;
    }

    public void outOfBounds(){
        this.visible = false;
    }

    public void bounceVertical(){

        this.rotation = 360 - rotation;
    }

    public void bounceHorisontal(){
        this.rotation = 180 - rotation;
    }

    public double getRotation(){
        return rotation;
    }
}
