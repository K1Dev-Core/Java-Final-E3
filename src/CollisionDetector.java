public class CollisionDetector {

    public static boolean checkCollision(Car car, Bomb bomb) {
        int scaleLeft = car.getX();
        int scaleRight = car.getX() + car.getWidth();
        int scaleTop = car.getY();
        int scaleBottom = car.getY() + car.getHeight();

        int snackLeft = bomb.getX();
        int snackRight = bomb.getX() + bomb.getWidth();
        int snackTop = bomb.getY();
        int snackBottom = bomb.getY() + bomb.getHeight();

        return !(scaleRight < snackLeft || scaleLeft > snackRight ||
                scaleBottom < snackTop || scaleTop > snackBottom);
    }
}
