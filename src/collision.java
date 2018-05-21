import org.newdawn.slick.geom.Shape;

public class collision {
    private Shape rect;

    private collision(Shape rectangle){
        rect = rectangle;
    }

    public float[] getCollisionPoints(){
        return rect.getPoints();
    }

    public void moveCollision(){


    }

    /*
    private boolean nextMoveValid(int direction, int collision){

        double xChange = Math.cos(Math.toRadians(img.getRotation()))*direction;
        double yChange = Math.sin(Math.toRadians(img.getRotation()))*direction;

        if (map.getTileId((int)Math.floor(polygon.getPoints()[0]/32 + (int) xChange - 1),(int) Math.floor(polygon.getPoints()[1]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(polygon.getPoints()[2]/32 + (int) xChange - 1),(int) Math.floor(polygon.getPoints()[3]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(polygon.getPoints()[4]/32 + (int) xChange - 1),(int) Math.floor(polygon.getPoints()[5]/32 + (int) yChange),collision) != 0){
            return false;
        }

        if (map.getTileId((int)Math.floor(polygon.getPoints()[6]/32 + (int) xChange - 1),(int) Math.floor(polygon.getPoints()[7]/32 + (int) yChange),collision) != 0){
            return false;
        }

        return true;

    }
    */
}
