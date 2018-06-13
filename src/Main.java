import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import org.newdawn.slick.tiled.TiledMap;


public class Main extends BasicGame {


    Tank player = new Tank(70, 64, 1, 10, true);
    Tank player2 = new Tank(650, 700, 2, 10, true);
    Image blueTank;
    Image redTank;
    private TiledMap map;
    Shape blueRect;
    Shape redRect;


    public Main(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        float[] vertices  = new float[]{69,64,94,64,121,64,121,86,121,109,94,109,69,109,69,86,69,64};
        map = new TiledMap("/Map2/map2.tmx");
        blueTank = new Image("/images/blue.png");
        redTank = new Image("/images/red.png");
        //rect for reference!
         blueRect = new Rectangle(70,64,36,28);
         redRect = new Rectangle(650,700,36,28);

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        Input input = gc.getInput();
      
        if (input.isKeyDown(Input.KEY_LEFT)) {
            float turn = -2f;
            if (nextTurnValid(turn,blueRect)){
                blueTank.rotate(turn);
                player.setAngle(turn);
                blueRect = blueRect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), blueRect.getCenterX(), blueRect.getCenterY()));
            }
            turn = 0;
        }

        if (input.isKeyDown(Input.KEY_RIGHT)) {
            float turn = 2f;
            if (nextTurnValid(turn,blueRect)) {
                blueTank.rotate(turn);
                player.setAngle(turn);
                blueRect = blueRect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)),blueRect.getCenterX(),blueRect.getCenterY()));

            }
            turn = 0;
        }

        if(input.isKeyDown(Input.KEY_UP)){

                double xChange = Math.cos(Math.toRadians(blueTank.getRotation())) * -1 * 2.8f;
                double yChange = Math.sin(Math.toRadians(blueTank.getRotation())) * -1 * 2.8f;
                if(nextMoveValid(1,xChange,yChange,blueRect)) {
                    blueRect.setCenterX(blueRect.getCenterX() + (float) xChange);
                    blueRect.setCenterY(blueRect.getCenterY() + (float) yChange);
                    player.move(-1, blueTank.getRotation());

                }

        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
            double xChange = Math.cos(Math.toRadians(blueTank.getRotation())) * 1 * 2.8f;
            double yChange = Math.sin(Math.toRadians(blueTank.getRotation())) * 1 * 2.8f;
            if (nextMoveValid(1,xChange,yChange,blueRect)) {
                blueRect.setCenterX(blueRect.getCenterX() + (float) xChange);
                blueRect.setCenterY(blueRect.getCenterY() + (float) yChange);
                player.move(1, blueTank.getRotation());
            }
        }

    }

    /*
     * Checks too see if the next turn is valid.
     * Turn: The angle which the tank is going to rotate.
     * Return: TRUE if next turn is valid. FALSE if next turn is invalid.
     */
    private boolean nextTurnValid(float turn, Shape rect){

        int x1 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[0]/32);
        int y1 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[1]/32);

        int x2 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[2]/32);
        int y2 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[3]/32);

        int x3 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[4]/32);
        int y3 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[5]/32);

        int x4 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[6]/32);
        int y4 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[7]/32);

        if (map.getTileId(x1,y1,1) != 0){
            return false;
        }

        if (map.getTileId(x2,y2,1) != 0){
             return false;
        }

        if (map.getTileId(x3,y3,1) != 0){
            return false;
        }

        if (map.getTileId(x4,y4,1) != 0){
            return false;
        }

        return true;
    }

    /*
     * Checks too see if the next move is valid.
     * Collision: Index of the tileID that results in invalid move.
     * xChange: the change in x coordinates
     * yChange: the change in y coordinates
     * Return: TRUE if next move is valid. FALSE if next move is invalid.
     */
    private boolean nextMoveValid(int collision, double xChange, double yChange, Shape rect){


        if (map.getTileId((int)Math.floor((rect.getPoints()[0]+xChange)/32),(int) Math.floor((rect.getPoints()[1]+yChange)/32),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor((rect.getPoints()[2]+xChange)/32),(int) Math.floor((rect.getPoints()[3]+yChange)/32),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor((rect.getPoints()[4]+xChange)/32),(int) Math.floor((rect.getPoints()[5]+yChange)/32),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor((rect.getPoints()[6]+xChange)/32),(int) Math.floor((rect.getPoints()[7]+yChange)/32),collision) != 0){
            return false;
        }


        return true;
    }


    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        map.render(0,0);
        blueTank.draw(player.getxCoord(), player.getyCoord(), 1);
        redTank.draw(player2.getxCoord(),player2.getyCoord(),1);
    }


    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(1000, 800, false);
            appgc.setVSync(true);
            appgc.setTargetFrameRate(60);
            appgc.start();

        }
        catch (SlickException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}