public class v_point {
    double x;
    double y;
    int flag = 0;

    @Override
    public String toString() {
        return "v_point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public v_point(double x, double y){
        this.x = x;
        this.y = y;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
