
import org.newdawn.slick.geom.Circle;

import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 */
public class Tank {


    private float xCoord;
    private float yCoord;
    private double angle;
    private boolean alive;
    private ArrayList<cannonBall> ammo = new ArrayList<>();



    public Tank(int xCoord,int yCoord){

        this.xCoord=xCoord;
        this.yCoord=yCoord;
        alive = true;


    }


    public void move(int direction,double angle){

        double xChange = Math.cos(Math.toRadians(angle)) * direction;
        double yChange = Math.sin(Math.toRadians(angle)) * direction;
        this.angle = angle;
        xCoord += xChange * 2.8f;
        yCoord += yChange * 2.8f;

    }

    public float getxCoord(){
        return xCoord;
    }

    public float getyCoord(){
        return yCoord;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }

    public ArrayList<cannonBall> getAmmo() {
        return ammo;
    }

    public void addCannon(cannonBall cannon){
        ammo.add(cannon);
    }

    public void dead(){this.alive = false;}

    public boolean getAlive(){
        return alive;
    }
}
