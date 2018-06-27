package JavaFXMappingApp;

import java.util.ArrayList;

 public class Coordlist {
    ArrayList<Coord> list = new ArrayList();

    public ArrayList<Coord> getList() {
        return list;
    }

    public void updateList(ArrayList<Coord> newlist) {
        list.clear();
        list.addAll(list);
    }
    
    
    
}
