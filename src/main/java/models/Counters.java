package models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Created by krzysztof on 12/06/2017.
 */
@Entity("counters")
public class Counters {
    @Id
    private String key;
    private int counter;

    public Counters(){}

    public Counters(String collection, int startValue){
        this.key = collection;
        this.counter = startValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
