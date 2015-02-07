package youngfriend.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.StringConverter;
import org.apache.commons.io.FileUtils;
import youngfriend.APP;

import java.io.File;
import java.util.Optional;

/**
 * Created by pandongyu on 15/1/14.
 */
public class Utils {
    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    public final static String txt = "txt";
    public final static String xml = "xml";
    public final static String pw = "report";
    private static final FileChooser fileChooser = new FileChooser();
    private static final DirectoryChooser directoryChooser = new DirectoryChooser();
    private static String workspace = null;
    private static String project = null;

    public static String getWorkspace() {
        return workspace;
    }

    public static void setWorkspace(String workspace) {
        Utils.workspace = workspace;
    }

    public static String getProject() {
        return project;
    }

    public static void setProject(String project) {
        Utils.project = project;
    }

    /*
         * Get the extension of a file.
         */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }


    public static File selectFile(String title, String... exts) {
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(title, exts));
        return fileChooser.showOpenDialog(APP.primaryStage);
    }

    public static File selectDirectory(String title) {
        directoryChooser.setTitle(title);
        return directoryChooser.showDialog(APP.primaryStage);
    }
    public static void selectFile_init(TextField com, File newfile) throws Exception {
        String old = com.getText();
        if (!Utils.isStrEmpty(old)) {
            File file = new File(getImagePath()+old);
            if (file.equals(newfile)) {
                return;
            }
            file.deleteOnExit();
        }
        FileUtils.copyFileToDirectory(newfile, new File(getImagePath()));
        com.setText(newfile.getName());
    }


    private static final StringConverter<String> sc = new StringConverter<String>() {
        @Override
        public String toString(String object) {
            return object == null ? null : object.toString();
        }

        @Override
        public String fromString(String string) {
            return string;
        }
    };

    public static StringConverter getStringCover() {
        return sc;
    }


    public static boolean isStrEmpty(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().length() == 0;
    }

    public static boolean containFile(File dir, String name) {
        if (!dir.isDirectory()) {
            return false;
        }
        String[] files = dir.list();
        for (String file : files) {
            if (file.equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static boolean fileExists(String path) {
        if (path == null) {
            return false;
        }
        File file = new File(path);
        return file.exists();
    }




    public static void error(Window owner, String msg) {
        Alert alert_error = new Alert(Alert.AlertType.ERROR);
        alert_error.setHeaderText("错误");
        alert_error.setTitle("错误");
        alert_error.initOwner(owner);
        alert_error.setContentText(msg);
        alert_error.showAndWait();
    }


    public static void msg(Window owner, String msg) {
        Alert alert_msg = new Alert(Alert.AlertType.INFORMATION);
        alert_msg.setHeaderText("提示");
        alert_msg.setTitle("提示");
        alert_msg.initOwner(owner);
        alert_msg.setContentText(msg);
        alert_msg.showAndWait();
    }


    public static boolean comfrim(Window owner, String msg) {
        Alert alert_comfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alert_comfirm.setHeaderText("提示");
        alert_comfirm.setTitle("提示");
        alert_comfirm.initOwner(owner);
        alert_comfirm.setContentText(msg);
        Optional<ButtonType> result = alert_comfirm.showAndWait();
        return result.get() == ButtonType.OK;
    }

    public static String getProjectPath(){
        return Utils.workspace+File.separator+Utils.project;
    }
    public static String getImagePath() {
        return getProjectPath()+File.separator+"images";
    }

    public static  String getWebPath(){
        return APP.config_dir + File.separator + "webfiles" ;
    }
}
