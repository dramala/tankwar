import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;

import org.newdawn.slick.tiled.TiledMap;


public class Main extends BasicGame {


    Tank player = new Tank(64, 64, 1, 10, true);
    Tank player2 = new Tank(650, 700, 2, 10, true);
    Image blueTank;
    Image redTank;
    private TiledMap map;
    Shape rect;


    public Main(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        map = new TiledMap("/Map2/map2.tmx");
        blueTank = new Image("/images/tank1.png");
        redTank = new Image("/images/tank2.png");
        rect = new Rectangle(68,64,82,60);

    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

        Input input = gc.getInput();
      
        if (input.isKeyDown(Input.KEY_LEFT)) {
            float turn = -2f;
            if (nextTurnValid(turn)){
                blueTank.rotate(turn);
                player.setAngle(turn);
                rect = rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY()));
            }
            turn = 0;
        }

        if (input.isKeyDown(Input.KEY_RIGHT)) {
            float turn = 2f;
            if (nextTurnValid(turn)) {
                blueTank.rotate(turn);
                player.setAngle(turn);
                rect = rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY()));
            }
            turn = 0;
        }

        if(input.isKeyDown(Input.KEY_UP)){

                double xChange = Math.cos(Math.toRadians(blueTank.getRotation())) * -1 * 2.8f;
                double yChange = Math.sin(Math.toRadians(blueTank.getRotation())) * -1 * 2.8f;
                if(nextMoveValid(1,xChange,yChange)) {
                    rect.setCenterX(rect.getCenterX() + (float) xChange);
                    rect.setCenterY(rect.getCenterY() + (float) yChange);
                    player.move(-1, blueTank.getRotation());

                }

        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
            double xChange = Math.cos(Math.toRadians(blueTank.getRotation())) * 1 * 2.8f;
            double yChange = Math.sin(Math.toRadians(blueTank.getRotation())) * 1 * 2.8f;
            if (nextMoveValid(1,xChange,yChange)) {
                rect.setCenterX(rect.getCenterX() + (float) xChange);
                rect.setCenterY(rect.getCenterY() + (float) yChange);
                player.move(1, blueTank.getRotation());
            }
        }

    }

    private boolean nextTurnValid(float turn){

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
    private boolean nextMoveValid(int collision, double xChange, double yChange){


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
        g.draw(rect);

        blueTank.draw(player.getxCoord(), player.getyCoord(), 1);


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