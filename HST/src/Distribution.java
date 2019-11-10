import java.util.ArrayList;

public class Distribution {
    private ArrayList<cluster> list_cluster = new ArrayList<>();

    public void add_cluster(cluster c){
        list_cluster.add(c);
    }

    public ArrayList<cluster> getList_cluster() {
        return list_cluster;
    }

    public void show_cluster(){
        int j = 0;
        for(int i = 0; i < list_cluster.size(); i++){
            if(list_cluster.get(i).get_point_num() == 0){
                continue;
            }
            System.out.println("cluster : " + j++ + "  ");
            list_cluster.get(i).show_point();
        }
    }

    public void show_cluster(int k){
        int j = 0;
        int count = 0;
        System.out.println("+++++++++++++++++++++++++++++");
        System.out.println("points number = " + k);
        for (int i = 0; i < list_cluster.size(); i++) {
            if(list_cluster.get(i).get_point_num() == k){
//                System.out.println("cluster : " + j++ + "  ");
                count++;
            }
        }
        System.out.println("cluster number = " + count);
        System.out.println("+++++++++++++++++++++++++++++");
    }

    public boolean is_singleton(){
        for(cluster i : list_cluster){
            if (i.get_point_num() > 1){
                return false;
            }
        }
        return true;
    }


}
