package youngfriend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import youngfriend.util.ExceptionDialog;
import youngfriend.util.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class APP extends Application {
    public static Stage primaryStage;
    public static String config_dir = null;{
        config_dir=new File(APP.class.getResource("").getFile()).getParentFile().getParent() + File.separator + "config";
        if(config_dir.startsWith("file:")){
            config_dir=config_dir.substring(5);
        }
    }
    private Properties config = new Properties();
    private String conifg_file = config_dir + File.separator + "config.properties";
    private App_Controller app_controller;


    private void loadConfig() {
        try {
            config.load(new FileInputStream(conifg_file));
            File workspace = new File(config.getProperty("workspace"));
            if (!workspace.exists()) {
                config.setProperty("workspace", "");
            }
            Utils.setWorkspace(workspace.getPath());
            app_controller.initWorkspaceDialog() ;
            app_controller.project_load();
            if(!Utils.fileExists(Utils.getProjectPath())){
                APP.primaryStage.close();
            }
        } catch (IOException e) {
            ExceptionDialog.getInstance(e);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        APP.primaryStage = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("app.fxml"));
        Scene scene = new Scene(loader.load());
         app_controller = loader.getController();
        primaryStage.setTitle("门户设计");
        primaryStage.setScene(scene);
//        primaryStage.setFullScreen(true);
        primaryStage.setOnCloseRequest((WindowEvent event) -> {
            if (!Utils.comfrim(APP.primaryStage,"确定退出")) {
                event.consume();
                return;
            }
            FileOutputStream out = null;
            try {
                config.setProperty("workspace", Utils.getWorkspace());
                config.store(new FileOutputStream(conifg_file), "config");
            } catch (Exception e) {
                ExceptionDialog.getInstance(e);
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    ExceptionDialog.getInstance(e);
                }
            }
        });
        primaryStage.show();
        loadConfig();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
