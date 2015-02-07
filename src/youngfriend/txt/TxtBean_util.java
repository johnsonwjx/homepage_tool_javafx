package youngfriend.txt;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import youngfriend.util.Utils;


/**
 * Created by pandongyu on 15/1/14.
 */
public class TxtBean_util {
    public static void txt_table_init(TableView<TxtBean> table,TxtBean_Service service, TableColumn<TxtBean, String> column) {
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        column.setCellValueFactory(new PropertyValueFactory<TxtBean, String>("txt"));
        column.setCellFactory(TextFieldTableCell.<TxtBean, String>forTableColumn(Utils.getStringCover()));
        table.setItems(service.list());
    }
}
