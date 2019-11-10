import java.util.ArrayList;

public class cluster {
    private ArrayList<v_point> list = new ArrayList<>();

    public void add_point(v_point a){
        list.add(a);
    }

    public ArrayList<v_point> getList() {
        return list;
    }

    public void show_point(){
        System.out.println("points num: " + list.size());

//        for (v_point i: list) {
//            System.out.println("x=" + i.getX() + "  y=" + i.getY());
//        }
    }

    public int get_point_num(){
        return list.size();
    }
}
