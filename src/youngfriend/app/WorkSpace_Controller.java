package youngfriend.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import org.apache.commons.io.FileUtils;
import youngfriend.APP;
import youngfriend.util.ExceptionDialog;
import youngfriend.util.Utils;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by pandongyu on 15/1/19.
 */
public class WorkSpace_Controller extends GridPane {
    @FXML
    private TextField workspace;
    @FXML
    private Button project_add_btn;

    @FXML
    private Button project_del_btn;

    @FXML
    private ComboBox<String> project;
    private final ObservableList<String> projects = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        project.setItems(projects);
    }

    @FXML
    void project_new() {
        try {
            TextInputDialog txt = new TextInputDialog();
            txt.setTitle("新建项目");
            txt.setHeaderText("新建项目");
            txt.initOwner(APP.primaryStage);
            Optional<String> result = txt.showAndWait();
            if (result.isPresent()) {
                String name = result.get();
                if (Utils.isStrEmpty(name)) {
                    return;
                }
                File file = new File(workspace.getText(), name);
                if (file.exists()) {
                    Utils.error(this.getScene().getWindow(),"项目已存在");
                    return;
                }
                file.mkdirs();
                File config = new File(file, "config.json");
                File images_dir = new File(file, "images");
                images_dir.mkdirs();
                config.createNewFile();
                projects.add(name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void project_del() {
        String name=project.getSelectionModel().getSelectedItem();
        if(!Utils.isStrEmpty(name)){
            if(Utils.comfrim(APP.primaryStage,"确定删除")){
                File file =new File(workspace.getText(),name) ;
                if(file.exists()){
                    try {
                        FileUtils.deleteDirectory(file);
                        project.getSelectionModel().clearSelection();
                        project.setValue(null);
                        projects.remove(name );
                        if(name.equals(Utils.getProject())){
                            Utils.setProject(null);
                        }
                    } catch (IOException e) {
                        ExceptionDialog.getInstance(e);
                    }
                }
            }


        }
    }

    @FXML
    void select_workspace() {
        workspace.setText("");
        projects.clear();
        project.getSelectionModel().clearSelection();
        project_add_btn.setDisable(true);
        project_del_btn.setDisable(true);

        File dir = Utils.selectDirectory("选择工作目录");
        initByWoirkspace(dir.getPath());
    }

    public void initByWoirkspace(String workspace_dir) {
        if (!Utils.isStrEmpty(workspace_dir)) {
            File workspaceFile = new File(workspace_dir);
            if (workspaceFile.exists()) {
                workspace.setText(workspace_dir);
                for (File file : workspaceFile.listFiles()) {
                    if (Utils.containFile(file, "config.json")) {
                        projects.add(file.getName());
                    }
                }
            }
            project_add_btn.setDisable(false);
            project_del_btn.setDisable(false);
        }

    }

}
