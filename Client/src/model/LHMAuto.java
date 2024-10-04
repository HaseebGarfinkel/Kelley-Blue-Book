package model;

import java.util.LinkedHashMap;
import java.util.Collection;
import java.util.Iterator;

public class LHMAuto <T extends Automobile> {
    
    private LinkedHashMap<String, T> LHM;

    public LHMAuto() {

        this.LHM = new LinkedHashMap<String, T>();
    }

    public void add(String key, T value) {

        this.LHM.put(key, value);
    }

    public void remove(String key) {

        this.LHM.remove(key);
    }

    public T find(String key) {

        return this.LHM.get(key);
    }

    public String iterate() {

        String str = "";
        Collection c = this.LHM.keySet();
        Iterator itr = c.iterator();

        while(itr.hasNext()) {
            
            str = str + itr.next();

        }

        return str;

    }

}
