package youngfriend;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import org.apache.commons.io.FileUtils;
import org.thymeleaf.context.Context;
import youngfriend.app.Page;
import youngfriend.app.WorkspaceDialog;
import youngfriend.bottom.Bottom;
import youngfriend.bottom.Bottom_Controller;
import youngfriend.header.Header;
import youngfriend.header.Header_Controller;
import youngfriend.main.Main;
import youngfriend.main.Main_Controller;
import youngfriend.template.TemplateCreator;
import youngfriend.util.ExceptionDialog;
import youngfriend.util.Utils;

import java.io.*;
import java.util.HashMap;
import java.util.Optional;

public class App_Controller extends BorderPane {
    private Header_Controller controller_header;
    private Bottom_Controller controller_bottomn;
    private WorkspaceDialog workspace_dialog;
    private Main_Controller controller_main;
    private final String preview_path = "file://" + APP.config_dir + File.separator + "webfiles" + File.separator + "demo.html";
    private final String demo_file = APP.config_dir + File.separator + "webfiles" + File.separator + "demo.html";

    @FXML
    void initialize() {
        try {
            FXMLLoader loader_header = new FXMLLoader();
            loader_header.setLocation(Header_Controller.class.getResource("header.fxml"));
            tab_header.setContent(loader_header.load());
            controller_header = loader_header.getController();

            FXMLLoader loader_bottom = new FXMLLoader();
            loader_bottom.setLocation(Bottom_Controller.class.getResource("bottom.fxml"));
            tab_bottom.setContent(loader_bottom.load());
            controller_bottomn = loader_bottom.getController();

            FXMLLoader loader_main = new FXMLLoader();
            loader_main.setLocation(Main_Controller.class.getResource("main.fxml"));
            tab_main.setContent(loader_main.load());
            controller_main = loader_main.getController();
        } catch (IOException e) {
            ExceptionDialog.getInstance(e);
        }

    }

    @FXML
    private WebView browser;
    @FXML
    private Tab tab_header;
    @FXML
    private Tab tab_bottom;
    @FXML
    private Tab tab_main;

    @FXML
    void preview(Event event) {
        try {

            Tab tab_browser = (Tab) event.getTarget();
            if (tab_browser.isSelected()) {
                Task<Void> task=new Task(){

                    @Override
                    protected Object call() throws Exception {
                        Header header = controller_header.getBean();
                        Bottom bottom = controller_bottomn.getBean();
                        Main main = controller_main.getBean();
                        Context cxt = new Context();
                        cxt.setVariable("header", header);
                        cxt.setVariable("bottom", bottom);
                        cxt.setVariable("main", main);
                        cxt.setVariable("imagepath", Utils.getImagePath());
                        TemplateCreator.export(demo_file, "demo", cxt, null);
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        browser.getEngine().load(preview_path);
                        updateMessage("Done!");
                    }
                };
              new Thread(task).start();
            }
        } catch (Exception e) {
            ExceptionDialog.getInstance(e);
        }

    }

    @FXML
    void project_load() {
        Optional<HashMap<String, String>> result = workspace_dialog.showAndWait();
        if (result.isPresent()) {
            String old = Utils.getProject();
            HashMap<String, String> map = result.get();
            Utils.setWorkspace(map.get("workspace"));
            Utils.setProject(map.get("project"));
            String path = Utils.getProject();
            if (path.equals(old)) {
                return;
            }
            File config_file = new File(Utils.getProjectPath(), "config.json");
                load(config_file);
        }
    }

    private void load(final File config_file) {
        Task<Void> task=new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                InputStream in = null;
                ObjectMapper mapper = new ObjectMapper();
                    try {
                        in = new FileInputStream(config_file);
                        Page page = mapper.readValue(in, Page.class);
                        if (page != null) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    controller_header.setBean(page.getHeader());
                                    controller_bottomn.setBean(page.getBottom());
                                    controller_main.setBean(page.getMain());
                                    Utils.msg(APP.primaryStage, "导入成功");
                                }
                            });

                        }
                        Utils.msg(APP.primaryStage, "导入成功");
                } catch (Exception e) {
                    ExceptionDialog.getInstance(e);
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            ExceptionDialog.getInstance(e);
                        }
                    }
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    @FXML
    void project_save() {
        File dir = new File(Utils.getProjectPath());
        if (!dir.exists()) {
            Utils.error(null, "工程路径不存在");
            return;
        }
        Task<Void> task=new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                FileOutputStream out = null;
                try {
                    Header header = controller_header.getBean();
                    Bottom bottom = controller_bottomn.getBean();
                    Main main = controller_main.getBean();
                    out = new FileOutputStream(new File(dir, "config.json"));
                    Page page = new Page(header, main, bottom);
                    Utils.msg(APP.primaryStage,new ObjectMapper().toString());
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(out, page);
                } catch (Exception e) {
                    ExceptionDialog.getInstance(e);
                } finally {
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            ExceptionDialog.getInstance(e);
                        }
                    }
                }
                return null;
            }

            @Override
            protected void succeeded() {
                Utils.msg(APP.primaryStage, "保存成功");
            }

        } ;
        new Thread(task).start();


    }

    @FXML
    void create_html() {
        File dir = Utils.selectDirectory("选择保存路径");
        if (dir != null) {
            new Thread(new Task<Void>(){
                @Override
                protected Void call() throws Exception {
                    try {
                        Header header = controller_header.getBean();
                        Bottom bottom = controller_bottomn.getBean();
                        Main main = controller_main.getBean();
                        TemplateCreator.export(new File(dir, "header.jsp").getAbsolutePath(), "header", "header", header);
                        TemplateCreator.export(new File(dir, "adv.jsp").getAbsolutePath(), "header", "header", header);
                        TemplateCreator.export(new File(dir, "foot.jsp").getAbsolutePath(), "bottom", "bottom", bottom);
                        TemplateCreator.export(new File(dir, "index.jsp").getAbsolutePath(), "main", "main", main);
                        TemplateCreator.export(new File(dir, "btns.xml").getAbsolutePath(), "main", "main", main);
                        //负责web文件
                        File cssDir=new File(dir,"css") ;
                        cssDir.mkdirs();
                        FileUtils.copyFileToDirectory(new File(Utils.getWebPath()+File.separator+"css" ,"style.css"),cssDir);
                        FileUtils.copyFileToDirectory(new File(Utils.getWebPath()+File.separator+"css" ,"csshover.htc"),cssDir);
                        File imageDir=new File(dir,"images") ;
                        imageDir.mkdirs();
                        FileUtils.copyDirectory(new File(Utils.getWebPath(), "images"), imageDir);
                        File images=new File(Utils.getImagePath());
                        for(File file:images.listFiles()){
                            FileUtils.copyFileToDirectory(file, imageDir);
                        }
                    } catch (Exception e) {
                        ExceptionDialog.getInstance(e);
                    }
                    return null;
                }

                @Override
                protected void succeeded() {
                    Utils.msg(APP.primaryStage, "生成成功");
                }
            }).start();

        }
    }

    public void initWorkspaceDialog() {
        workspace_dialog = new WorkspaceDialog(Utils.getWorkspace());
    }
}
