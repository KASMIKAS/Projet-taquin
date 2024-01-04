import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;
import java.util.Set;

public class TaquinBFS {

    public static Taquin solve(Taquin initial) {
        Queue<Taquin> queue = new LinkedList<>();
        Set<Taquin> visited = new HashSet<>();

        queue.add(initial);
        visited.add(initial);

        while (!queue.isEmpty()) {
            Taquin current = queue.poll();

            if (current.isDone()) {
                return current;
            }

            for (String direction : new String[]{"haut", "bas", "gauche", "droit"}) {
                if (current.isDirectionOk(direction)) {
                    try {
                        Taquin next = current.Translation(direction);
                        if (!visited.contains(next)) {
                            queue.add(next);
                            visited.add(next);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }

}