package youngfriend.bean_table_base;

import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by pandongyu on 15/1/14.
 */
public interface Bean_Service<T> {
    void add(int index);
    void del(ObservableList<T> selects);
    ObservableList<T> list();
    void init(List<T> datas);
}
