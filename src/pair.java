public class pair {
    private int x;
    private int y;

    public pair(int x,int y){
        this.x = x;
        this.y = y;
    };
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public boolean isEqual(pair other){
        if(other.x == this.x && other.y == this.y){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
