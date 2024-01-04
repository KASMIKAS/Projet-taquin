import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class parcours_profondeur {
    int max_depth;

    public parcours_profondeur(int max){
        this.max_depth=max;
    }

    public ArrayList<Taquin> solve(Taquin taquin) throws Exception {
        ArrayList<Taquin> solution = new ArrayList<>();
        HashSet<Taquin> visited = new HashSet<>();
        ArrayDeque<Taquin> file = new ArrayDeque<>();
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
            current = file.pollFirst();
            System.out.println(current);
            for (String direction : new String[]{"haut", "bas", "gauche", "droit"}) {
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
                        } else if (max_depth <= inter.getDepth()) {
                            continue;
                        } else {
                            file.addFirst(inter);
                            visited.add(inter);
                        }
                    }
                }
            }
            nb_it++;
//            System.out.println(current.getDepth()+ " profondeur");
//            System.out.println(visited.size() + " visited");
//            System.out.println(file.size() + " file");
//            System.out.println(nb_it + " nombre itération");

        }
        System.out.println(current.getDepth()+ " profondeur");
        System.out.println(nb_it + " nombre itération");
        System.out.println(visited.size() + " noeuds visités");
        System.out.println(path.size()  + " nombre-node");
        return null;
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

}
