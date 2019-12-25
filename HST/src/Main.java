import java.util.ArrayList;
import java.util.Random;
import java.util.stream.DoubleStream;

public class Main {
    public static void main(String[] args) {
        ArrayList<v_point> list = new ArrayList<>();
//        list.add(new v_point(0,0));
//        list.add(new v_point(0,1));
//        list.add(new v_point(1,0));
//        list.add(new v_point(1,1));
//
//        list.add(new v_point(0,2));
//        list.add(new v_point(1,2));
//        list.add(new v_point(2,0));
//        list.add(new v_point(2,1));
//        list.add(new v_point(2,2));

        int k = 10;
        Random r = new Random();
        for(int i = 0; i < k; i++){
//            for (int j = 0 ; j < k ; j++){
//                if(r.nextDouble() > 0.5){
//                    list.add(new v_point(i,j));
//                }
//            }
            for (int j = 0 ; j < k ; j++){
                list.add(new v_point(i,j));
            }
        }
//        System.out.println(list);
        HST tree1 = new HST(list);
//        tree1.show_D();
        tree1.statistics_D(1,10);
        System.out.println("---------------------------------");
        tree1.set_k(2);
        tree1.statistics_D(1,10);
    }
}

