import java.util.ArrayList;

public class Heuristique1 implements Heuristique{

//    private int dist_heurs;
//    public Taquin taquin ;

//    public Heuristique1(Taquin taquin) {
//
//        //this.dist_manhattan = dist_manhattan;
//        this.taquin = taquin ;
//    }


    public int calcul_dist_heurs(Taquin taquin){
        int dist_heurs=0;
        for (ArrayList<String> lis : taquin.getlist_line()){
            for (ArrayList<String> lisb : taquin.getbut()){
                for (String i : lis){
                    for (String j : lisb){
                        if (i.equals(j)){
                            //System.out.println(dist_heurs + " dist");
                            //System.out.println(taquin.getIndexBut(i)) ;
                            //System.out.println(taquin.getIndex(j));
                            System.out.println(Math.abs(taquin.getIndexBut(i) - taquin.getIndex(j) ));

                            dist_heurs += Math.abs(taquin.getIndexBut(i) - taquin.getIndex(j) );
                        }
                    }
                }
            }
        }
        return dist_heurs ;
    }
    @Override
    public boolean isMin() {
        return true;
    }

    @Override
    public long calcul(Taquin taq) {
        int dist_heurs = 0;
        for (ArrayList<String> lis : taq.getlist_line()){
            for (ArrayList<String> lisb : taq.getbut()){
                for (String i : lis){
                    for (String j : lisb){
                        if (i.equals(j)){
                            //System.out.println(dist_heurs + " dist");
                            //System.out.println(taquin.getIndexBut(i)) ;
                            //System.out.println(taquin.getIndex(j));
                            //System.out.println(Math.abs(taq.getIndexBut(i) - taq.getIndex(j) ));

                            dist_heurs += Math.abs(taq.getIndexBut(i) - taq.getIndex(j) );
                        }
                    }
                }
            }
        }
        return dist_heurs ;
    }
}
