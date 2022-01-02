public abstract class Element implements GameCharecter{

    protected Position position;

    public Element(int x, int y){
        position = new Position(x,y);
        position.setX(x);
        position.setY(y);
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }


}
