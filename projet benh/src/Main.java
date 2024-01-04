import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("choisissez un parcours "+ "\n" +
                " Parcours en profondeur tapez profondeur " +"\n"+
                " Parcours en largeur tapez largeur " + "\n"+
                " Parcours meilleur d'abord tapez meilleur "
        );
        Scanner saisieUtilisateur = new Scanner(System.in);
        String choix = saisieUtilisateur.next();
        System.out.println("choissisez la profondeur");
        int depth = saisieUtilisateur.nextInt();
        System.out.println("donnez le nom du fichier");
        String file = saisieUtilisateur.next();
        System.out.println("donnez la largeur du taquin");
        int width = saisieUtilisateur.nextInt();
        //D:\cours\L3\ia benhs\2e try\projet benh best (2)\projet benh best\projet benh\src\
        Taquin taquin = new Taquin("/amuhome/r20023197/IA benhmsss/projet benh/src/" +file, width);
        ArrayList<Taquin> res = new ArrayList<>();
        switch (choix){

            case "largeur":
                parcours_largeur parcoursDFS = new parcours_largeur(depth);
                res = parcoursDFS.solve(taquin);
                break;



            case "profondeur":
                parcours_profondeur parcoursBFS = new parcours_profondeur(depth);
                res = parcoursBFS.solve(taquin);
                break;

            case "meilleur":
                System.out.println("choisissez l'heuristique" + "\n"+
                        "heuristique1 tapez 1" + "\n"+
                        "heuristique2 tapez 2" + "\n"+
                        "heuristique3 tapez 3");
                int heur = saisieUtilisateur.nextInt();
                Heuristique h;
                if (heur == 1) {
                    h = new Heuristique1();
                } else if (heur == 2) {
                    h = new Heuristique2();

                } else {
                    h = new Heuristique3();
                }
                parcours_best best = new parcours_best(depth,h);
                res = best.solve(taquin);
                break;

            default:
                System.out.println("vous avez mal écris quelques choses");
                break;


        }

        if(res!=null){
            System.out.println("souhaitez vous affichez le chemin ? tapez 1 sinon n'importe quoi d'autres");
            int r = saisieUtilisateur.nextInt();
            if(r == 1){
                System.out.println(res.toString());
                System.out.println(res.size());
            }
        }



//        Taquin test = new Taquin("D:\\cours\\L3\\ia benhs\\2e try\\projet benh best (2)\\projet benh best\\projet benh\\src\\tester2",4);
//        parcours_largeur parcoursDFS = new parcours_largeur(15);
//        parcours_profondeur parcoursBFS = new parcours_profondeur(10);

//        Taquin test2 = test.Translation("haut");
//        Taquin test3 = test2.Translation("bas");

//       long timer = System.nanoTime();
//       ArrayList<Taquin> res = parcoursBFS.solve(test);
//       System.out.println(System.nanoTime()-timer + " temps");
//       System.out.println("test " + "\n" + test);
//       System.out.println("res " + "\n" + res);
    }
}