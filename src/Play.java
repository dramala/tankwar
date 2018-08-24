import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;


public class Play extends BasicGameState {

    public Play(int state){

    }

    Tank player = new Tank(70, 64);
    Tank player2 = new Tank(650, 700);
    Image blueTank;
    Image redTank;
    private TiledMap map;
    Shape blueRect;
    Shape redRect;
    inputHandler movement;
    Image ball;


    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        map = new TiledMap("Map2/map2.tmx");
        blueTank = new Image("/images/blue.png");
        redTank = new Image("/images/red.png");
        blueRect = new Rectangle(70,64,36,28);
        redRect = new Rectangle(650,700,36,28);
        movement = new inputHandler(blueTank,redTank,blueRect,redRect,player,player2,map,gc.getInput());
        ball = new Image("images/cannonball.png");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        movement.move();
        movement.shooting();
        movement.cannonOutOfBounds();
        for (cannonBall ammo : player.getAmmo()){
            ammo.move();
        }
        for (cannonBall ammo : player2.getAmmo()){
            ammo.move();
        }
       movement.winnerWinnerChickenDinner();


    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.render(0,0);

        if (player.getAlive()) {
            blueTank.draw(player.getxCoord(), player.getyCoord());
        }

        if (player2.getAlive()) {
            redTank.draw(player2.getxCoord(), player2.getyCoord());
        }

        if (!player2.getAlive()) {
            g.drawString("Player 1 Wins", 320, 400);
        }

        if (!player.getAlive()) {
            g.drawString("Player 2 Wins", 320, 400);
        }

        for (cannonBall ammo : player.getAmmo()){
            if (ammo.getVisible()) {
                ball.draw(ammo.getCenterX(), ammo.getCenterY());
            }
        }

        for (cannonBall ammo : player2.getAmmo()){
            if (ammo.getVisible()){
                ball.draw(ammo.getCenterX(),ammo.getCenterY());
            }
        }
    }

    @Override
    public int getID() {
        return 0;
    }
}


