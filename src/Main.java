public class Main {
    public static void main(String[] args) {
        Tree test = new Tree(2,2);
        test.fillTree();
        System.out.println(test.getNumPaths());
        System.out.println(test.getNumSnakes());
    }
}