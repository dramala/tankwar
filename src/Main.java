import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;



public class Main extends StateBasedGame {

    public static final int menu = 0;
    public static final int play = 0;


    public Main(String gamename) {
        super(gamename);
        //this.addState(new Menu(menu));
        this.addState(new Play(play));
    }

    public void initStatesList(GameContainer gc) throws SlickException{
       // this.getState(menu).init(gc, this);
        this.getState(play).init(gc,this);
        enterState(play);
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Main("Tank War"));
            appgc.setDisplayMode(800, 800, false);
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