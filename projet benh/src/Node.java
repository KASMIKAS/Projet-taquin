public class Node {
    private Taquin taq;
    private Taquin father;


    public Node(Taquin taq,Taquin father ){
        this.taq=taq;
        this.father=father;
    }


    public Taquin getFather() {
        return father;
    }

    public Taquin getTaq() {
        return taq;
    }
}
