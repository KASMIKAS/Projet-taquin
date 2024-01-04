import java.util.Stack;
import java.util.HashSet;
import java.util.Set;

public class TaquinDFS {

    public static Taquin solve(Taquin initial) {
        Stack<Taquin> stack = new Stack<>();
        Set<Taquin> visited = new HashSet<>();

        stack.push(initial);
        visited.add(initial);

        while (!stack.isEmpty()) {
            Taquin current = stack.pop();

            if (current.isDone()) {
                return current;
            }

            for (String direction : new String[]{"haut", "bas", "gauche", "droit"}) {
                if (current.isDirectionOk(direction)) {
                    try {
                        Taquin next = current.Translation(direction);
                        if (!visited.contains(next)) {
                            stack.push(next);
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