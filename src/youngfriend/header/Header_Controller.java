package youngfriend.header;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import youngfriend.bean_table_base.Bean_Utils;
import youngfriend.header.nav.Nav;
import youngfriend.header.nav.Nav_Service;
import youngfriend.header.nav.Nav_Service_Sub;
import youngfriend.txt.TxtBean;
import youngfriend.txt.TxtBean_Service;
import youngfriend.txt.TxtBean_Service_Sub;
import youngfriend.txt.TxtBean_util;
import youngfriend.util.Controller;
import youngfriend.util.ExceptionDialog;
import youngfriend.util.Map_Utils;
import youngfriend.util.Utils;

import java.io.File;


/**
 * Created by pandongyu on 15/1/14.
 */
public class Header_Controller extends GridPane implements Controller<Header>{

    private final TxtBean_Service txt1_service = new TxtBean_Service_Sub();
    private final TxtBean_Service txt2_service = new TxtBean_Service_Sub();
    private final Nav_Service nav_service = new Nav_Service_Sub();

    @Override
    public Header getBean() {
        Header header =new Header(logopath.getText(),flashpath.getText(),txt1_service.list(),txt1_service.list(),nav_service.list()) ;
        return header ;
    }

    @Override
    public void setBean(Header bean) {
        logopath.setText(bean.getLogopath());
        flashpath.setText(bean.getFlashpath());
        txt1_service.init(bean.getTxt1());
        txt2_service.init(bean.getTxt2());
        nav_service.init(bean.getNavs());
    }

    @FXML
    void initialize() {
        TxtBean_util.txt_table_init(txt1, txt1_service, txt1_txt);
        TxtBean_util.txt_table_init(txt2, txt2_service, txt2_txt);

        nav.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        nav_url.setCellValueFactory(new PropertyValueFactory<Nav, String>("url"));
        nav_url.setCellFactory(TextFieldTableCell.<Nav, String>forTableColumn(Utils.getStringCover()));

        nav_title.setCellValueFactory(new PropertyValueFactory<Nav, String>("title"));
        nav_title.setCellFactory(ComboBoxTableCell.<Nav, String>forTableColumn(Utils.getStringCover(), Map_Utils.getNameArr(Nav_Service_Sub.map)));
        nav.setItems(nav_service.list());
    }

    @FXML
    private TextField logopath;
    @FXML
    private TextField flashpath;

    @FXML
    private TableView<TxtBean> txt1;
    @FXML
    private TableColumn<TxtBean, String> txt1_txt;

    @FXML
    private TableView<TxtBean> txt2;

    @FXML
    private TableColumn<TxtBean, String> txt2_txt;

    @FXML
    private TableView<Nav> nav;

    @FXML
    private TableColumn<Nav, String> nav_title;

    @FXML
    private TableColumn<Nav, String> nav_url;



    @FXML
    void nav_add() {
        Bean_Utils.bean_add(nav, nav_service);
    }

    @FXML
    void nav_title_change(TableColumn.CellEditEvent<Nav, String> event) {
        Nav navitem = event.getRowValue();
        navitem.setTitle(event.getNewValue());
        navitem.setUrl(Nav_Service_Sub.map.get(navitem.getTitle()));
    }

    @FXML
    void nav_del() {
        Bean_Utils.bean_del(nav, nav_service);
    }

    @FXML
    void logo_select(ActionEvent event) {
        File file = Utils.selectFile("图片", "*.png", "*.jpg", "*.gif");
        if (file != null) {
            try {
                Utils.selectFile_init(logopath, file);
            } catch (Exception e) {
                ExceptionDialog.getInstance(e);
            }

        }

    }

    @FXML
    void flashpath_select(ActionEvent event) {
        File file= Utils.selectFile("flash", "*.swf");
        if (file != null) {
            try {
                Utils.selectFile_init(flashpath,file);
            } catch (Exception e) {
                ExceptionDialog.getInstance(e);
            }
        }
    }

    @FXML
    void txt1_add(ActionEvent event) {
        Bean_Utils.bean_add(txt1, txt1_service);
    }

    @FXML
    void txt1_del(ActionEvent event) {
        Bean_Utils.bean_del(txt1, txt1_service);
    }


    @FXML
    void txt2_add(ActionEvent event) {
        Bean_Utils.bean_add(txt2, txt2_service);
    }

    @FXML
    void txt2_del(ActionEvent event) {
        Bean_Utils.bean_del(txt2, txt2_service);
    }


}
