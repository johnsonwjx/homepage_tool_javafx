package youngfriend.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import youngfriend.APP;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by pandongyu on 15/1/16.
 */
public class ExceptionDialog extends Alert {
    private static final ExceptionDialog INSTANCE=new  ExceptionDialog() ;
    private final TextArea textArea;

    public static void getInstance(Exception ex){
        INSTANCE.setContentText(ex.getMessage());
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String exceptionText = sw.toString();
        INSTANCE.textArea.setText(exceptionText);
        INSTANCE.showAndWait();
    }

    private ExceptionDialog() {
        super(AlertType.ERROR);
        initOwner(APP.primaryStage);
        setTitle("发生错误");
        setHeaderText("错误信息");
        Label label = new Label("错误堆栈信息");
         textArea = new TextArea("");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        getDialogPane().setExpandableContent(expContent);
    }
}
