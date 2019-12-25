import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class HST {
    private Distribution[] D;
    private int delta;

    public HST(ArrayList<v_point> list){
        //1.打乱顺序
        Collections.shuffle(list);
        //2.生成β
        Random r = new Random();
        double beta_base = r.nextDouble() + 1;
        System.out.println("beta : " + beta_base);
        //3.计算delta
        //3-1. 计算list直径
        double diameter = 0;
        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                double temp = getDistance(list.get(i),list.get(j));
                if (diameter < temp){
                    diameter = temp;
                }
            }
        }
        //3-2. 计算delta
        delta =  (int)Math.floor(Math.log(diameter) / Math.log(2.0));
        System.out.println("delta : " + delta);
        //4. 初始化D和i
        D = new Distribution[delta + 1];
        for (int i = 0; i <= delta; i++) {
            D[i] = new Distribution();
        }
        cluster V = new cluster();
        for (v_point a: list) {
            V.add_point(a);
        }
        D[delta].add_cluster(V);
        int i = delta - 1;
        //迭代构建HST
        while(!D[i+1].is_singleton() && i >= 0){
            double beta = Math.pow(2,i-1) * beta_base;
            for(int l = 0; l < list.size(); l++){
                v_point l_temp = new v_point(list.get(l).getX(),list.get(l).getY());
                for(cluster s : D[i+1].getList_cluster()){
                    cluster new_c = new cluster();
                    new_c.setCore(l_temp);
                    new_c.setBeta(beta);
                    for(v_point p : s.getList()){
                        if (p.getFlag() == 1){
                            continue;
                        }
                        if (getDistance(p,l_temp) <= beta){
                            new_c.add_point(p);
                            p.setFlag(1);
                        }
                    }
                    if (new_c.get_point_num() != 0){
                        D[i].add_cluster(new_c);
                    }
                }
            }
            //将flag归0
            for (v_point p: list) {
                p.setFlag(0);
            }
            i = i - 1;
        }
    }

    public void show_D(){
        //显示hst
        for (int j = 0; j < delta+1; j++) {
            System.out.println("第 " + j + " 层   cluster数 : " + D[j].getList_cluster().size());
            D[j].show_cluster();
        }
    }

    //重载show_D 显示具体第i层 cluster
    public void show_D(int i){
        System.out.println("第 " + i + " 层   cluster数 : " + D[i].getList_cluster().size());
        D[i].show_cluster();
    }

    //统计功能 i为层数  k为从点数为1的cluster统计到点数为k的cluster
    public void statistics_D(int i, int k){
        int num = D[i].getList_cluster().size();
        System.out.println("cluster总数： " + num);
        for (int j = 1; j < k; j++) {
            D[i].show_cluster(j);
        }
    }


    public double getDistance(v_point a, v_point b){
        return Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY()));
    }

    //设置D1层每个cluster中 point的最少数目
    public void set_k(int k){
        for (int i = 0; i < D[1].getList_cluster().size(); i++) {
            while(D[1].getList_cluster().get(i).get_point_num() < k){
                Random r = new Random();
                double r_x;
                double r_y;
                do{
                    r_x = D[1].getList_cluster().get(i).getCore().getX()+(r.nextDouble()-0.5)*2*D[1].getList_cluster().get(i).getBeta();
                    r_y = D[1].getList_cluster().get(i).getCore().getY()+(r.nextDouble()-0.5)*2*D[1].getList_cluster().get(i).getBeta();
                }while (getDistance(new v_point(r_x,r_y),D[1].getList_cluster().get(i).getCore()) <= D[1].getList_cluster().get(i).getBeta());
                D[1].getList_cluster().get(i).add_point(new v_point(r_x,r_y));
            }
        }
    }
}
