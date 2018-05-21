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
    float x1 = 64;
    float y1 = 64;


    Tank player = new Tank(64, 64, 1, 10, true);
    Tank player2 = new Tank(650, 700, 2, 10, true);
    Image blueTank;
    Image redTank;
    Image cannon;

    boolean move = false;

    Sound sound;
    double temp = 0;

    private int shotNumber = 0;
    private int shotNumber2 = 0;

    private TiledMap map;
    float[] vertices;
    Shape rect;

    Shape[] myShapes;

    public Main(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {

        myShapes = new Shape[25*25];
        int index = 0;

        // Debugging purposes
        for (int i = 0; i < 25; i++){
            for (int j = 0; j < 25; j++){
               myShapes[index] = new Rectangle(32*j,32*i,32,32);
               index++;
            }
        }

        cannon = new Image("images/canonball.png");
        sound = new Sound("sound/sound.ogg");

        map = new TiledMap("/Map2/map2.tmx");
        blueTank = new Image("/images/tank1.png");
        redTank = new Image("/images/tank2.png");

        rect = new Rectangle(68,64,82,60);
        map = new TiledMap("/Map2/map2.tmx");
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

                double xChange = Math.cos(Math.toRadians(blueTank.getRotation())) * -1 * 0.8f;
                double yChange = Math.sin(Math.toRadians(blueTank.getRotation())) * -1 * 0.8f;
                if(nextMoveValid(1,xChange,yChange)) {
                    rect.setCenterX(rect.getCenterX() + (float) xChange);
                    rect.setCenterY(rect.getCenterY() + (float) yChange);
                    player.move(-1, blueTank.getRotation());

                }

        }

        if(input.isKeyDown(Input.KEY_DOWN)) {
            double xChange = Math.cos(Math.toRadians(blueTank.getRotation())) * 1 * 0.8f;
            double yChange = Math.sin(Math.toRadians(blueTank.getRotation())) * 1 * 0.8f;
            if (nextMoveValid(1,xChange,yChange)) {
                rect.setCenterX(rect.getCenterX() + (float) xChange);
                rect.setCenterY(rect.getCenterY() + (float) yChange);
                player.move(1, blueTank.getRotation());
            }
        }


        if (!player.getAmmoList().isEmpty())
            for (cannonBall b : player2.getAmmoList()) {


                if (b.getMove()) {
                    b.move(b.getAngle());
                    b.setVisible(true);

                    if (b.getyCoord() > 800 || b.getxCoord() > 800 || b.getxCoord() < 0 || b.getyCoord() < 0) {

                        b.setMove(false);
                    }


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

    private boolean nextMoveValid(int collision, double xChange, double yChange){

        if (map.getTileId((int)Math.floor(rect.getPoints()[0]/32 + (int) xChange),(int) Math.floor(rect.getPoints()[1]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(rect.getPoints()[2]/32 + (int) xChange),(int) Math.floor(rect.getPoints()[3]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(rect.getPoints()[4]/32 + (int) xChange),(int) Math.floor(rect.getPoints()[5]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(rect.getPoints()[6]/32 + (int) xChange),(int) Math.floor(rect.getPoints()[7]/32 + (int) yChange),collision) != 0){
            return false;
        }


        return true;

    }


    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {

        map.render(0,0);
        g.draw(rect);

        for (Shape shape : myShapes){
            g.draw(shape);
        }

        g.drawString(Arrays.toString(rect.getPoints()),10,700);

        if(player.getAmmosize()!=0) {
           for (int x=0;x<player.getAmmosize();x++) {
            //   System.out.println(player.getAmmosize());
               cannonBall b=player.getAmmo(x);
               cannon.draw(b.getxCoord(),b.getyCoord(),0.1f);
              // g.draw(b);
           }
        }

        if(player2.getAmmosize()!=0) {
            for (int x = 0; x < player2.getAmmosize(); x++) {

                cannonBall b = player2.getAmmo(x);
                cannon.draw(b.getxCoord(), b.getyCoord(), 0.1f);
                  //  g.draw(b);
            }
        }

        sidePanel(g);

        blueTank.draw(player.getxCoord(), player.getyCoord(), 1);

        if (!player.isState()){
            gameover(player2,g);
        }
        else if(!player2.isState()){
            gameover(player,g);
        }

        if(move) gameover(player,g);


    }

    public void sidePanel(Graphics g) {

       g.drawString("Player one!",820,50);
       g.drawString("Player two!",820,730);
       g.drawString("shots:"+player.getAmmosize(),820,80);
       g.drawString("shots:"+player2.getAmmosize(),820,750);

    }


    public void gameover(Tank winner,Graphics g){

        if(winner.equals(player)) g.drawString("Congratulations \n player 1 !",820,400);
        if(winner.equals(player2)) g.drawString("Congratulations player 2 !",820,400);


    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Simple Slick Game"));
            appgc.setDisplayMode(1000, 800, false);
            //  appgc.setShowFPS(false);
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