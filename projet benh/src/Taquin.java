import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Collections;

//Classe à Tester
public class Taquin {

    //ajouter la position de l'espace
    //modifier le constructeur en conséquence
    private ArrayList<ArrayList<String>> list_line;
    private ArrayList<ArrayList<String>> but;

    private int largeur;

    private int hauteur;

    private int depth;


    //1er Constructeur ne sert que lors de l'ouverture du fichier
    public Taquin(String Filename, int largeur) throws FileNotFoundException {

        File file = new File(Filename);
        Scanner scanner = new Scanner(file);
        int nb_line = Integer.parseInt(scanner.nextLine());
        int comp = 0;
        String line;
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        ArrayList<ArrayList<String>> but_inter = new ArrayList<>();
        ArrayList<Integer> index_inter = new ArrayList<>();
        while (scanner.hasNext()) {
            line = scanner.nextLine();
            if (comp >= nb_line) {
                but_inter.add(line_creator(line,largeur));
                continue;
            }
            matrix.add(line_creator(line,largeur));
            comp++;

        }
        this.largeur = largeur;
        this.hauteur = nb_line;
        this.list_line = matrix;
        this.but = but_inter;
        this.depth = 0;

    }

    //2e constructeur du taquin sert à créer un taquin à partir de la matrice
    //son etat final et l'indice du trou
    public Taquin(ArrayList<ArrayList<String>> matrix, ArrayList<ArrayList<String>> but, int depth, int largeur, int hauteur){
        this.list_line = matrix;
        this.but = but;
        this.depth = depth;
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    //méthodes get
    //sert juste à retourner les élèments du taquin

    public ArrayList<ArrayList<String>> getlist_line(){
        return list_line;
    }

    public ArrayList<ArrayList<String>> getbut(){
        return but;
    }

    public int getDepth(){
        return depth;
    }



    //utile pour créer le tableau
    //utilisé dans le constructeur

    public ArrayList<String> line_creator (String line, int nb_ligne){
        ArrayList<String> res = new ArrayList<String>();
        for (int index = 1; index <= line.length(); index++) {
            res.add(line.substring(index - 1, index));

        }
        if(res.size()<nb_ligne){
            res.add(" ");
        }
        return res;
    }

    //utile pour trouver l'indice du trou
    //utilisé dans le constructeur
    public int line_finder(String str){
        int res = 0;
        for (int index = 1; index <= str.length(); index++){
            if (str.substring(index-1,index).equals(" ")){
                break;
            }
            res++;
        }
        return res;
    }

    public ArrayList<Integer> getIndexHole(){
        ArrayList<Integer> sol = new ArrayList<>();

        for(int index0=0; index0<list_line.size();index0++){
            if(list_line.get(index0).contains(" ")){
                sol.add(index0);
                for(int index1=0; index1<largeur;index1++){
                    if(list_line.get(index0).get(index1).equals(" ") ){
                        sol.add(index1);
                    }
            }

            }
        }
        return sol;
    }


    //méthode toString
    public String toString(){
        return list_line.toString() + "\n" + but.toString()  + "\n" + getIndexHole().toString();
    }



    //ne sert qu'a copié des listes utiles dans Translation
    public ArrayList<ArrayList<String>> copylist_line() {
        ArrayList<ArrayList<String>> res = new ArrayList<>();
        for(ArrayList<String> list: list_line){
            ArrayList<String> inter = new ArrayList<>();
            inter.addAll(list);
            res.add(inter);
        }
        return res;
    }

    public String sepcial_print(){
        String res = list_line.toString();
        res = res + ""+ isDone();
        return res;
    }

    //méthode pour calculer les changements après qu'on est modifié un taquin selon la direction qu'on lui donne
    //en paramètre
    //utilisez droit , gauche , bas , haut
    public Taquin Translation(String direction)throws Exception{
        ArrayList<ArrayList<String>> reslist_line = this.copylist_line();
        ArrayList<ArrayList<String>> resbut = new ArrayList<>();
        resbut.addAll(but);



        Taquin res = new Taquin(reslist_line,resbut,depth+1,largeur,hauteur);
        int index0 = res.getIndexHole().get(0);
        int index1 = res.getIndexHole().get(1);
        switch(direction) {
            case "haut": // get(0)-1
                if (res.getIndexHole().get(0) == 0) {
                    throw new Exception("flag haut");
                }
                String elt_haut = res.getlist_line().get(index0-1).get(index1);
                res.getlist_line().get(index0-1).set(index1, " ");
                res.getlist_line().get(index0).set(index1, elt_haut);
                break;

            case "bas": //get(0)+1

                if (res.getIndexHole().get(0) == res.getlist_line().size()-1){
                    throw new Exception("flag bas");
                }
                String elt_bas = res.getlist_line().get(index0+1).get(index1);
                res.getlist_line().get(index0+1).set(index1, " ");
                res.getlist_line().get(index0).set(index1, elt_bas);

                break;


            case "gauche": //droit get(1)-1
                if (res.getIndexHole().get(1) == 0){
                    throw new Exception("flag gauche");
                }
                Collections.swap(res.getlist_line().get(res.getIndexHole().get(0)), index1,index1-1);
                //System.out.println(res.index_Hole + " res.indexHole");
                break;


            case "droit": //get(1)+1
                if (res.getIndexHole().get(1) == res.list_line.get(0).size()-1){
                    throw new Exception("flag droit");
                }
                Collections.swap(res.list_line.get(res.getIndexHole().get(0)), index1,index1+1);
                break;

            default:
                throw new Exception("direction incorrecte");

        }
        return res;

    }

    //sert à tester si deux taquins sont identiques ne pas utiliser ==
    public boolean isEqual(Taquin elt){
        if(list_line.size()!= elt.list_line.size()){
            return false;
        }
        for(int index=0; index<list_line.size();index++){
            for(int index2=0; index2<list_line.get(index).size(); index2++){
                if (!list_line.get(index).get(index2).equals(elt.getlist_line().get(index).get(index2))){
                    return false;
                }

            }

        }
        return true;
    }

    //sert à savoir si on a atteint l'état final
    public boolean isDone(){
        for(int index=0; index<list_line.size();index++){
            if (!list_line.get(index).equals(but.get(index))){
                return false;
            }
        }
        return true;
    }

    //sert à savoir si une direction est faisable
    //utilisé uniquement dans la construction des feuilles
    //seule méthodes non_testées
    public boolean isDirectionOk(String direction){
        try {
            this.Translation(direction);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


    public int getIndexBut(String elt) {
        ArrayList<Integer> sol = new ArrayList<>();
        int trueindex = 0;
        String ss = "";
        for (int index0 = 0; index0 < but.size(); index0++) {
            if (but.get(index0).contains(elt)) {
                sol.add(index0);
                for (int index1 = 0; index1 < largeur; index1++) {
                    if (but.get(index0).get(index1).equals(elt)) {
                        sol.add(index1);
                    }
                }
                trueindex = sol.get(0) * but.get(0).size()   + sol.get(1);

            }


        }
        return  trueindex ;
    }


    public int getIndex(String elt) {
        ArrayList<Integer> sol = new ArrayList<>();
        int trueindex = 0;
        String ss = "";
        for (int index0 = 0; index0 < list_line.size(); index0++) {
            if (list_line.get(index0).contains(elt)) {
                sol.add(index0);
                for (int index1 = 0; index1 < largeur; index1++) {
                    if (list_line.get(index0).get(index1).equals(elt)) {
                        sol.add(index1);
                    }
                }
                trueindex = sol.get(0) * list_line.get(0).size()   + sol.get(1);

            }


        }
        return  trueindex ;
    }

}
