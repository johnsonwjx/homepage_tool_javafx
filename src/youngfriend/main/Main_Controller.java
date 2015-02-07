package youngfriend.main;

import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import org.apache.commons.io.FileUtils;
import youngfriend.bean_table_base.Bean_Utils;
import youngfriend.main.btn.Btn;
import youngfriend.main.btn.Btn_Service;
import youngfriend.main.btn.Btn_ServiceSub;
import youngfriend.main.tab.TabBean;
import youngfriend.main.tab.TabBean_Service;
import youngfriend.main.tab.TabBean_ServiceSub;
import youngfriend.util.Controller;
import youngfriend.util.ExceptionDialog;
import youngfriend.util.Map_Utils;
import youngfriend.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by pandongyu on 15/1/15.
 */
public class Main_Controller extends GridPane implements Controller<Main> {
    private final Btn_Service btn_service = new Btn_ServiceSub();
    private final TabBean_Service tabs_left_service = new TabBean_ServiceSub();
    private final TabBean_Service tabs_right_service = new TabBean_ServiceSub();

    @Override
    public Main getBean() {
        Main main = new Main(title.getText(),tabs_left_service.list(), tabs_right_service.list(),btn_service.list());
        return main;
    }

    @Override
    public void setBean(Main bean) {
        title.setText(bean.getTitle());
        tabs_left_service.init(bean.getTabs_left());
        tabs_right_service.init(bean.getTabs_right());
        btn_service.init(bean.getBtns());
    }

    @FXML
    void initialize() {
        btns.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        btns.setItems(btn_service.list());
        btns_name.setCellValueFactory(new PropertyValueFactory<Btn, String>("name"));
        btns_url.setCellValueFactory(new PropertyValueFactory<Btn, String>("url"));
        btns_privilege.setCellValueFactory(new PropertyValueFactory<Btn, String>("privilege"));
        btns_name.setCellFactory(TextFieldTableCell.forTableColumn(Utils.getStringCover()));
        btns_url.setCellFactory(TextFieldTableCell.forTableColumn(Utils.getStringCover()));
        btns_privilege.setCellFactory(TextFieldTableCell.forTableColumn(Utils.getStringCover()));

        tabs_left.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabs_left.setItems(tabs_left_service.list());
        tabs_left_type.setCellValueFactory(new PropertyValueFactory<TabBean, String>("type"));
        tabs_left_logo.setCellValueFactory(new PropertyValueFactory<TabBean, String>("logo"));
        tabs_left_height.setCellValueFactory(new PropertyValueFactory<TabBean, String>("height"));
        tabs_left_type.setCellFactory(ComboBoxTableCell.forTableColumn(Utils.getStringCover(), Map_Utils.getNameArr(TabBean_ServiceSub.typeMap)));
        tabs_left_logo.setCellFactory(TextFieldTableCell.forTableColumn(Utils.getStringCover()));
        tabs_left_height.setCellFactory(TextFieldTableCell.forTableColumn(Utils.getStringCover()));

        tabs_right.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabs_right.setItems(tabs_right_service.list());
        tabs_right_type.setCellValueFactory(new PropertyValueFactory<TabBean, String>("type"));
        tabs_right_title.setCellValueFactory(new PropertyValueFactory<TabBean, String>("title"));
        tabs_right_height.setCellValueFactory(new PropertyValueFactory<TabBean, String>("height"));
        tabs_right_type.setCellFactory(ComboBoxTableCell.forTableColumn(Utils.getStringCover(), Map_Utils.getNameArr(TabBean_ServiceSub.typeMap)));
        tabs_right_title.setCellFactory(TextFieldTableCell.forTableColumn(Utils.getStringCover()));
        tabs_right_height.setCellFactory(TextFieldTableCell.forTableColumn(Utils.getStringCover()));

    }

    @FXML
    private TextField title;

    @FXML
    private TableView<Btn> btns;
    @FXML
    private TableColumn<Btn, String> btns_name;
    @FXML
    private TableColumn<Btn, String> btns_url;
    @FXML
    private TableColumn<Btn, String> btns_privilege;

    @FXML
    void btns_add() {
        Bean_Utils.bean_add(btns, btn_service);
    }

    @FXML
    void btns_del() {
        Bean_Utils.bean_del(btns, btn_service);
    }

    @FXML
    private TableView<TabBean> tabs_left;
    @FXML
    private TableColumn<TabBean, String> tabs_left_type;
    @FXML
    private TableColumn<TabBean, String> tabs_left_logo;
    @FXML
    private TableColumn<TabBean, String> tabs_left_height;

    @FXML
    void tabs_left_add() {
        Bean_Utils.bean_add(tabs_left, tabs_left_service);
    }

    @FXML
    void tabs_left_del() {
        Bean_Utils.bean_del(tabs_left, tabs_left_service);
    }

    @FXML
    void tabs_left_height_commit(TableColumn.CellEditEvent<TabBean, String> event) {
        try {
            Integer.parseInt(event.getNewValue());
            event.getRowValue().setHeight(event.getNewValue());
        } catch (Exception e) {
            event.getRowValue().setHeight(event.getOldValue());
        }
    }


    @FXML
    void tabs_left_logo_select() {
        TabBean item = tabs_left.getSelectionModel().getSelectedItem();
        if (item != null) {
            File file= Utils.selectFile("图片", "*.png", "*.jpg", "*.gif");
            if(file!=null){
                try {
                    String old=item.getLogo();
                    if(!Utils.isStrEmpty(old)){
                        File old_file=new File(Utils.getImagePath(),old) ;
                        if(old_file.equals(file)){
                            return;
                        }
                        old_file.deleteOnExit();
                    }
                    FileUtils.copyFileToDirectory(file, new File(Utils.getProject()));
                    item.setLogo(file.getName());
                } catch (IOException e) {
                    ExceptionDialog.getInstance(e);
                }
            }

        }


    }

    @FXML
    void tabs_right_type_change(TableColumn.CellEditEvent<TabBean, String> event) {
        Map.Entry<String, String> item = Map_Utils.getEntryByName(TabBean_ServiceSub.typeMap, event.getNewValue());
        if (item != null) {
            event.getRowValue().setTitle(item.getKey());
            event.getRowValue().setType(item.getKey());
        }
    }

    @FXML
    void tabs_right_height_commit(TableColumn.CellEditEvent<TabBean, String> event) {
        try {
            Integer.parseInt(event.getNewValue());
            event.getRowValue().setHeight(event.getNewValue());
        } catch (Exception e) {
            event.getRowValue().setHeight(event.getOldValue());
        }
    }

    @FXML
    private TableView<TabBean> tabs_right;
    @FXML
    private TableColumn<TabBean, String> tabs_right_type;
    @FXML
    private TableColumn<TabBean, String> tabs_right_title;
    @FXML
    private TableColumn<TabBean, String> tabs_right_height;

    @FXML
    void tabs_right_add() {
        Bean_Utils.bean_add(tabs_right, tabs_right_service);
    }

    @FXML
    void tabs_right_del() {
        Bean_Utils.bean_del(tabs_right, tabs_right_service);
    }

}
