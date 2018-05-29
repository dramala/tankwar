
import org.newdawn.slick.geom.Circle;

import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;

/**
 *
 */
public class Tank {


    private float xCoord;
    private float yCoord;
    private double angle;
    private boolean state;//alive

    private ArrayList<cannonBall> ammo= new ArrayList<cannonBall>();
    private int ammoSize;



    public Tank(int xCoord,int yCoord,int player,int ammoSize,boolean state){
        this.xCoord=xCoord;
        this.yCoord=yCoord;


        for (int s=0;s<ammoSize;s++){
            if(player==1) {
                cannonBall b = new cannonBall(820 + 15 * s, 100, 10);
                addBall(b);
            }
            if(player==2){
                cannonBall b = new cannonBall(820 + 15 * s, 700, 10);
                addBall(b);
            }
        }
        this.state=true;
    }


    public float getxCoord(){
        return xCoord;
    }

    public double getAngle(){
        return angle;
    }

    public float getyCoord(){
        return yCoord;
    }

    public void setxCoord(int x){
        xCoord=x;
    }
    public void setAngle(double angle1){
        angle=angle1;

    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean isState() {
        return state;
    }

    public void addBall(cannonBall c){

        ammo.add(c);
        ammoSize++;


    }
    public void shoot(){

        if(ammoSize!=0) {
            ammoSize--;
            //check if empty null checks
            // ammo.get(0).move(angle);
            ammo.remove(ammoSize);
        }
        else System.out.println("error");

    }


    public void setyCoord(int y){
        yCoord=y;
    }

   public cannonBall getBall(){

        //null check
        if(ammoSize!=0)
        return ammo.get(ammoSize-1);

        else
            return null;

   }
    public void move(int direction,double angle){


       if(state) {
           double xChange = Math.cos(Math.toRadians(angle)) * direction;
           double yChange = Math.sin(Math.toRadians(angle)) * direction;
           this.angle = angle;
           xCoord += xChange * 2.8f;
           yCoord += yChange * 2.8f;

       }

    }

    public int getAmmosize() {
        return ammoSize;
    }

    public ArrayList<cannonBall> getAmmoList() {
        return ammo;
    }

    public cannonBall getAmmo(int id) {
        return ammo.get(id);
    }

    public void setAmmoSize(int ammoSize) {
        this.ammoSize = ammoSize;
    }

}
