import java.util.*;
import java.util.ArrayList;
import java.util.HashSet;
public class parcours_best {


    private Heuristique heuristique;
    private int max_depth;

    public parcours_best( int max_depth,Heuristique heuristique){
            this.max_depth = max_depth;
            this.heuristique = heuristique;
        }
    public ArrayList<Taquin> solve(Taquin taquin) throws Exception {
            ArrayList<Taquin> solution = new ArrayList<>();
            HashSet<Taquin> visited = new HashSet<>();
            ArrayList<Taquin> file = new ArrayList<>();
            ArrayList<Node> path = new ArrayList<>();
            if (taquin.isDone()) {
                solution.add(taquin);
                return solution;
            }
            int nb_it = 0;
            Taquin current = taquin;
            visited.add(taquin);
            file.add(taquin);
            while (!file.isEmpty()) {
                current = file.get(0);
                System.out.println(heuristique.calcul(current));
                file.remove(0);
                System.out.println(current);
                for (String direction : new String[]{"haut", "bas", "gauche", "droit"}) {
//                System.out.println(direction);
//                System.out.println(current.isDirectionOk(direction));
                    if (current.isDirectionOk(direction)) {
                        Taquin inter = current.Translation(direction);
                        if (!this.isInVisited(visited, inter)) {
                            Node node = new Node(inter,current);
                            path.add(node);
                            if (inter.isDone()) {
                                System.out.println(current.getDepth()+ " profondeur");
                                System.out.println(nb_it + " nombre itération");
                                System.out.println(visited.size() + " noeuds visités");
                                System.out.println(path.size()  + " nombre-node");
                                solution = this.getChemin(path,node);
                                return solution;
                            } else if (max_depth <= current.getDepth()) {
                                continue;
                            } else {
                                InsertTaquin(file,inter);
                                visited.add(inter);
                            }
                        }
                    }
                }
                nb_it++;
//                System.out.println(current.getDepth()+ " profondeur");
//                System.out.println(visited.size() + " visited");
//                System.out.println(file.size() + " file");
//                System.out.println(nb_it + " nombre itération");


            }
            System.out.println(current.getDepth()+ " profondeur");
            System.out.println(nb_it + " nombre itération");
            System.out.println(visited.size() + " noeuds visités");
            System.out.println(path.size()  + " nombre-node");
            return null;
        }

        public void InsertTaquin(ArrayList<Taquin> taquins, Taquin taquin) {
            boolean isNotDone=true;
            if(taquins != null) {
                for (int index = 0; index < taquins.size(); index++) {
                    if (this.getHeuristique().isMin()) {
                        if (this.getHeuristique().calcul(taquins.get(index)) <= this.getHeuristique().calcul(taquin)) {
                            taquins.add(index, taquin);
                            isNotDone = false;
                        }
                    } else {
                        if (this.getHeuristique().calcul(taquins.get(index)) >= this.getHeuristique().calcul(taquin)) {
                            taquins.add(index, taquin);
                            isNotDone = false;
                        }

                    }
            }

                if (isNotDone) {
                taquins.add(taquin);
                }
            }else{
                taquins.add(taquin);
            }

        }

    public boolean isInVisited (HashSet < Taquin > visited, Taquin taquin){
        for (Taquin elt : visited) {
            if (elt.isEqual(taquin)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Taquin> getChemin(ArrayList<Node> path, Node Leaf){
        ArrayList<Taquin> res = new ArrayList<>();
        res.add(Leaf.getTaq());
        Taquin var = Leaf.getFather();
        for(Node node : path){
            if(var == node.getTaq()){
                res.addAll(this.getChemin(path,node));
                return res;
            }
        }
        res.add(Leaf.getFather());
        return res;
    }

    public Heuristique getHeuristique() {
        return heuristique;
    }

}


