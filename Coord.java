
package JavaFXMappingApp;

public class Coord {
    private String name;
    private int x;
    private int y;
    

    public Coord(int x, int y, String name) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    @Override
    public String toString() {
        return "Mapping{" + "X=" + x + ", Y=" + y + ", name=" + name + '}';
    }
    
}
