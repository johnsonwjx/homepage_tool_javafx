package youngfriend.util;

import javafx.util.Pair;

/**
 * Created by pandongyu on 15/1/14.
 */
public class Name_Pair<K,V> extends Pair<K,V>{


    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Name_Pair(K key, V value) {
        super(key, value);
    }

    @Override
    public String toString() {
        return getKey()+"";
    }
}
