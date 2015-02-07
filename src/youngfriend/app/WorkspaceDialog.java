package youngfriend.app;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import youngfriend.APP;
import youngfriend.util.Utils;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by pandongyu on 15/1/19.
 */
public class WorkspaceDialog extends Dialog<HashMap<String,String>> {
    private final WorkSpace_Controller workSpace_controller;

    public WorkspaceDialog(String workspace_dir) {
        try {
            initOwner(APP.primaryStage);
            FXMLLoader workspace_xmlloader = new FXMLLoader();
            workspace_xmlloader.setLocation(WorkSpace_Controller.class.getResource("workspace.fxml"));
            getDialogPane().setContent(workspace_xmlloader.load());
            workSpace_controller = workspace_xmlloader.getController();

            getDialogPane().getButtonTypes().addAll(ButtonType.OK);
            Node root = getDialogPane().getContent();
            workSpace_controller.initByWoirkspace(workspace_dir);
            TextField workspace_com = (TextField) root.lookup("#workspace");

            ComboBox<String> proejct_com = (ComboBox) root.lookup("#project");
            Platform.runLater(()->proejct_com.requestFocus());
            //检查 workspace  是否存在
            setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    HashMap<String, String> hashMap = new HashMap();
                    hashMap.put("workspace", workspace_com.getText());
                    hashMap.put("project", proejct_com.getValue());
                    return hashMap;
                }
                return null;
            });

            setOnCloseRequest( event->{
                HashMap<String, String> map = getResult();
                if(map!=null&&Utils.isStrEmpty(getResult().get("project"))){
                    if(Utils.comfrim(this.getOwner(),"工程目录没选择，是否继续选择")){
                        event.consume();
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
