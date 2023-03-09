import java.util.*;

public class Tree{
    private ArrayList<ArrayList<pair>> paths;

    private long numPaths;
    private long numSnakes;
    private TreeNode root;
    private int n;
    private int m;
    private pair size;
    private int[][] SAWsperPoint;
    public Tree (int n, int m){
        this.root = new TreeNode(new pair(0,0));
        this.n = n;
        this.m = m;
        paths = new ArrayList<ArrayList<pair>>();
        this.size = new pair(n,m);
        SAWsperPoint = new int[n+1][m+1];
    }
    public TreeNode getRoot(){
        return root;
    }
    public boolean inList(pair toAdd, ArrayList<pair> list){
        for(pair i: list){
            if(i.isEqual(toAdd)){
                return true;
            }
        }
        return false;
    }
    public long getNumPaths(){
        return numPaths;
    }
    public long getNumSnakes(){
        return numSnakes;
    }

    public void fillTree(){
        for(int i =0; i<(n+1)*(m+1);i++){
            addLayer(root);
        }
        printAllRootToLeafPaths(root);
    }
    public void addLayer(TreeNode node, ArrayList<pair> vec)
    {
        // If node is null
        if (node == null)
            return;
        // Insert current node's
        // data into the vector
        vec.add(node.data);
        // If current node is a leaf node
        if (node.children.isEmpty())
        {
            // Print the path
            if(node.data.isEqual(new pair(n,m))){

            }
            else {
                if (node.data.getX() + 1 <= n && !inList(new pair(node.data.getX() + 1, node.data.getY()), vec)) {
                    node.children.add(new TreeNode(new pair(node.data.getX() + 1, node.data.getY())));
                }
                if (node.data.getY() + 1 <= m && !inList(new pair(node.data.getX(), node.data.getY() + 1), vec)) {
                    node.children.add(new TreeNode(new pair(node.data.getX(), node.data.getY() + 1)));
                }
                if (node.data.getX() - 1 >= 0 && !inList(new pair(node.data.getX() - 1, node.data.getY()), vec)) {
                    node.children.add(new TreeNode(new pair(node.data.getX() - 1, node.data.getY())));
                }
                if (node.data.getY() - 1 >= 0 && !inList(new pair(node.data.getX(), node.data.getY() - 1), vec)) {
                    node.children.add(new TreeNode(new pair(node.data.getX(), node.data.getY() - 1)));
                }
            }
            // Pop the leaf node
            // and return
            vec.remove(vec.size() - 1);
            return;
        }

        // Recur for all children of
        // the current node
        for (int i = 0; i < node.children.size(); i++)

            // Recursive Function Call
            addLayer(node.children.get(i), vec);
        vec.remove(vec.size() - 1);
    }
    public void addLayer(TreeNode root)
    {

        // If root is null, return
        if (root == null)
            return;

        // Stores the root to leaf path
        ArrayList<pair> vec = new ArrayList<>();

        // Utility function call
        addLayer(root, vec);
    }

    public void printAllRootToLeafPaths(TreeNode root, ArrayList<pair> vec)
    {

        // If root is null
        if (root == null)
            return;

        // Insert current node's
        // data into the vector
        vec.add(root.data);

        // If current node is a leaf node
        if (root.children.isEmpty()&&root.data.isEqual(new pair(n,m)))
        {

            // Print the path

            /*ArrayList<pair> toAdd = new ArrayList<>();
            paths.add(toAdd);
            for(int i = 0; i< vec.size();i++){
                toAdd.add(vec.get(i));
                SAWsperPoint[vec.get(i).getX()][vec.get(i).getY()]++;
            }
         */

            //printPath(toAdd);

            // Pop the leaf node
            // and return
            numPaths ++;
            if(vec.size()-1 >= (n+1)*(m+1)-2){
                numSnakes++;
                for(pair i: vec){
                }
            }
            vec.remove(vec.size() - 1);
            return;
        }

        // Recur for all children of
        // the current node
        for (int i = 0; i < root.children.size(); i++)

            // Recursive Function Call
            printAllRootToLeafPaths(root.children.get(i), vec);
        vec.remove(vec.size() - 1);
    }
    public void printAllRootToLeafPaths(TreeNode root)
    {

        // If root is null, return
        if (root == null)
            return;

        // Stores the root to leaf path
        ArrayList<pair> vec = new ArrayList<>();

        // Utility function call
        printAllRootToLeafPaths(root, vec);
    }
    public int numSAWS(){
        return paths.size();
    }
    public void printPaths(){
        for(ArrayList<pair> i: paths){
            for(pair j: i){
                System.out.print(j.toString() + " ");
            }
            System.out.println(" ");
        }
    }
    public void printLetters(){
        for(ArrayList<pair> i: paths){
            System.out.println(letters(i));
        }
    }
    public String letters(ArrayList<pair> path){
        String letters = new String();
        for(int i = 0; i<path.size()-1;i++){
            if(path.get(i+1).getX()>path.get(i).getX()){
                letters = letters + "R";
            }
            else if(path.get(i+1).getY()>path.get(i).getY()){
                letters = letters + "U";
            }
            else if(path.get(i+1).getX()<path.get(i).getX()){
                letters = letters + "L";
            }
            else if(path.get(i+1).getY()<path.get(i).getY()){
                letters = letters + "D";
            }
        }
        return letters;
    }

    public void printSAWsperPoint(){
        System.out.println("Number of Valid SAWs that contain this point");
        for(int i = 0; i<=n;i++){
            for(int j = 0; j<=m;j++){
                System.out.println("( " + i + " , " + j + ") is on " + SAWsperPoint[i][j] + " SAWs");
            }
        }
    }
    private class TreeNode{
        private pair data;
        private ArrayList<TreeNode> children;
        public TreeNode(pair data){
            this.data = data;
            children = new ArrayList<TreeNode>();
        }
        public ArrayList<TreeNode> getChildren(){
            return children;
        }
        public void setChildren(){
            if(data.getX()+1<=n){
                children.add(new TreeNode(new pair(data.getX()+1, data.getY())));
            }
            if(data.getY()+1<=m){
                children.add(new TreeNode(new pair(data.getX(), data.getY()+1)));
            }
            if(data.getX()-1>=0){
                children.add(new TreeNode(new pair(data.getX()-1, data.getY())));
            }
            if(data.getY()-1>=0){
                children.add(new TreeNode(new pair(data.getX(), data.getY()-1)));
            }
        }
    }
}
