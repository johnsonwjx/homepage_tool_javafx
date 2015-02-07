package youngfriend.bottom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.apache.commons.io.FileUtils;
import youngfriend.bean_table_base.Bean_Utils;
import youngfriend.txt.TxtBean;
import youngfriend.txt.TxtBean_util;
import youngfriend.txt.TxtBean_Service;
import youngfriend.txt.TxtBean_Service_Sub;
import youngfriend.util.Controller;
import youngfriend.util.ExceptionDialog;
import youngfriend.util.Utils;

import java.io.File;
import java.io.IOException;


/**
 * Created by pandongyu on 15/1/14.
 */
public class Bottom_Controller extends BorderPane implements Controller<Bottom>{
    private final TxtBean_Service txt1_service=new TxtBean_Service_Sub() ;
    private final TxtBean_Service txt2_service=new TxtBean_Service_Sub() ;

    @Override
    public Bottom getBean(){
        Bottom bottom=new Bottom(weixin_path.getText(),txt1_service.list(),txt2_service.list()) ;
        return bottom;
    }

    @Override
    public void setBean(Bottom bean) {
        weixin_path.setText(bean.getWeixin_path());
        txt1_service.init(bean.getTxt1());
        txt2_service.init(bean.getTxt2());
    }

    @FXML
    void initialize(){
        TxtBean_util.txt_table_init(txt1, txt1_service, txt1_txt);
        TxtBean_util.txt_table_init(txt2, txt2_service, txt2_txt);
    }


    @FXML
    private TextField weixin_path ;

    @FXML
    private TableView<TxtBean> txt1 ;

    @FXML
    private TableColumn<TxtBean,String> txt1_txt ;

    @FXML
    private TableView<TxtBean> txt2 ;

    @FXML
    private TableColumn<TxtBean,String> txt2_txt ;
    @FXML
    void weixin_select(){
        File file = Utils.selectFile("图片", "*.png", "*.jpg", "*.gif");
        if (file != null) {
            try {
                Utils.selectFile_init(weixin_path, file) ;
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
