import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.tiled.TiledMap;


public class inputHandler {

    Image blueTank;
    Image redTank;
    Shape blueRect;
    Shape redRect;
    Input input;
    Tank player;
    Tank player2;


    TiledMap map;


    public inputHandler(Image blueTank, Image redTank, Shape blueRect, Shape redRect, Tank player, Tank player2, TiledMap map, Input input){
        this.blueRect = blueRect;
        this.redRect = redRect;
        this.blueTank = blueTank;
        this.redTank = redTank;
        this.player = player;
        this.player2 = player2;
        this.map = map;
        this.input = input;

    }

    public void move(){

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

        if (input.isKeyDown(Input.KEY_A)) {
            float turn = -2f;
            if (nextTurnValid(turn,redRect)){
                redTank.rotate(turn);
                player2.setAngle(turn);
                redRect = redRect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), redRect.getCenterX(), redRect.getCenterY()));
            }
            turn = 0;
        }

        if (input.isKeyDown(Input.KEY_D)) {
            float turn = 2f;
            if (nextTurnValid(turn,redRect)){
                redTank.rotate(turn);
                player2.setAngle(turn);
                redRect = redRect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), redRect.getCenterX(), redRect.getCenterY()));
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

        if(input.isKeyDown(Input.KEY_W)){

            double xChange = Math.cos(Math.toRadians(redTank.getRotation())) * -1 * 2.8f;
            double yChange = Math.sin(Math.toRadians(redTank.getRotation())) * -1 * 2.8f;
            if(nextMoveValid(1,xChange,yChange,redRect)) {
                redRect.setCenterX(redRect.getCenterX() + (float) xChange);
                redRect.setCenterY(redRect.getCenterY() + (float) yChange);
                player2.move(-1, redTank.getRotation());

            }
        }

        if(input.isKeyDown(Input.KEY_S)) {
            double xChange = Math.cos(Math.toRadians(redTank.getRotation())) * 1 * 2.8f;
            double yChange = Math.sin(Math.toRadians(redTank.getRotation())) * 1 * 2.8f;
            if(nextMoveValid(1,xChange,yChange,redRect)) {
                redRect.setCenterX(redRect.getCenterX() + (float) xChange);
                redRect.setCenterY(redRect.getCenterY() + (float) yChange);
                player2.move(1, redTank.getRotation());

            }
        }
    }

    /*
     * Check the input for shooting
     * Shoots if player presses correct key
     */

    public void shooting(){
        if(input.isKeyPressed(Input.KEY_SPACE)){
            player.addCannon(new cannonBall(blueRect.getCenterX(),blueRect.getCenterY(),4, blueTank.getRotation()));
        }

        if(input.isKeyPressed(Input.KEY_Q)){
            player2.addCannon(new cannonBall(redRect.getCenterX(),redRect.getCenterY(),4, redTank.getRotation()));
        }

    }

    /*
     * Checks too see if the next turn is valid.
     * Turn: The angle which the tank is going to rotate.
     * Return: TRUE if next turn is valid. FALSE if next turn is invalid.
     */
    private boolean nextTurnValid(float turn, Shape rect) {

        int x1 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[0] / 32);
        int y1 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[1] / 32);

        int x2 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[2] / 32);
        int y2 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[3] / 32);

        int x3 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[4] / 32);
        int y3 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[5] / 32);

        int x4 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[6] / 32);
        int y4 = (int) Math.floor(rect.transform(Transform.createRotateTransform((float) Math.toRadians((turn)), rect.getCenterX(), rect.getCenterY())).getPoints()[7] / 32);

        if (map.getTileId(x1, y1, 1) != 0) {
            return false;
        }

        if (map.getTileId(x2, y2, 1) != 0) {
            return false;
        }

        if (map.getTileId(x3, y3, 1) != 0) {
            return false;
        }

        if (map.getTileId(x4, y4, 1) != 0) {
            return false;
        }

        return true;
    }


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

    public void cannonOutOfBounds(){

        for (cannonBall ammo : player.getAmmo()){
            if (ammo.getVisible()) {

                if (map.getTileId((int) Math.floor(ammo.getCenterX() / 32), (int) Math.floor(ammo.getCenterY() / 32), 1) != 0) {

                    double xChange = Math.cos(Math.toRadians(180 - ammo.getRotation())) * 20.0f;
                    double yChange = Math.sin(Math.toRadians(180 - ammo.getRotation())) * 20.0f;

                    if (map.getTileId((int) Math.floor((ammo.getCenterX() - (int) xChange)) / 32, (int) Math.floor((ammo.getCenterY() - (int) yChange))   / 32, 1) != 0){
                        ammo.bounceVertical();
                    }

                    else{
                        ammo.bounceHorisontal();
                    }

                }
            }
        }


        for (cannonBall ammo : player2.getAmmo()){
            if (ammo.getVisible()) {

                if (map.getTileId((int) Math.floor(ammo.getCenterX() / 32), (int) Math.floor(ammo.getCenterY() / 32), 1) != 0) {

                    double xChange = Math.cos(Math.toRadians(180 - ammo.getRotation())) * 20.0f;
                    double yChange = Math.sin(Math.toRadians(180 - ammo.getRotation())) * 20.0f;

                    if (map.getTileId((int) Math.floor((ammo.getCenterX() - (int) xChange)) / 32, (int) Math.floor((ammo.getCenterY() - (int) yChange))   / 32, 1) != 0){
                        ammo.bounceVertical();
                    }

                    else{
                        ammo.bounceHorisontal();
                    }

                }
            }
        }

    }

    public void winnerWinnerChickenDinner(){
        for (cannonBall ammo : player.getAmmo()){
            if (ammo.getVisible()){
                if (redRect.contains(ammo) || redRect.intersects(ammo)){
                    player2.dead();
                    ammo.outOfBounds();
                }
            }
        }

        for (cannonBall ammo : player2.getAmmo()){
            if (ammo.getVisible()){
                if (blueRect.contains(ammo) || blueRect.intersects(ammo)){
                    player.dead();
                    ammo.outOfBounds();
                }
            }
        }
    }
}
