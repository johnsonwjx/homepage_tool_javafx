package youngfriend.bean_table_base;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.List;

/**
 * Created by pandongyu on 15/1/14.
 */
public class Bean_Utils {

    public static <T> void bean_del(TableView<T> table, Bean_Service<T> service) {
        ObservableList<T> selects = table.getSelectionModel().getSelectedItems();
        if (selects.isEmpty()) {
            return;
        }
        int index = table.getSelectionModel().getSelectedIndex();
        service.del(selects);
        index--;
        if (index < 0) {
            index = table.getItems().size() - 1;
        }
        if (index > 0) {
            table.getSelectionModel().clearSelection();
            table.getSelectionModel().select(index);
        }
    }

    public static <T> void bean_add(TableView<T> table, Bean_Service<T>  service) {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index == -1) {
            index = table.getItems().size();
        } else {
            index++;
        }
        service.add(index);
        table.getSelectionModel().clearSelection();
        table.getSelectionModel().select(index);
    }

    public static <T> int getIndex(int index ,List<T> data){
        if (index < 0 || index > data.size() - 1) {
            index = data.size();
        }
        return index ;
    }
}
