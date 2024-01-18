package irnode;

import java.util.ArrayList;
import java.util.HashMap;

public class irClassNode {
    public String name;
    public ArrayList<irType> tp;
    public HashMap<String, Integer> name2Id;
    public irClassNode(String name) {
        this.name = name;
        this.tp = new ArrayList<>();
        this.name2Id = new HashMap<>();
    }
}
