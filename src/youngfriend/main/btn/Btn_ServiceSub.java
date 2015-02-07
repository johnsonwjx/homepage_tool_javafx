package youngfriend.main.btn;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import youngfriend.bean_table_base.Bean_Utils;

import java.util.List;

/**
 * Created by pandongyu on 15/1/15.
 */
public class Btn_ServiceSub implements Btn_Service {
    private final ObservableList<Btn> data = FXCollections.observableArrayList();

    @Override
    public void add(int index) {
        data.add(Bean_Utils.getIndex(index, data), new BtnSub("名称"));
    }

    @Override
    public void del(ObservableList<Btn> selects) {
        if (selects != null) {
            data.removeAll(selects);
        }
    }

    @Override
    public ObservableList<Btn> list() {
        return data;
    }

    @Override
    public void init(List<Btn> datas) {
        data.setAll(datas);
    }

    public static final class BtnSub implements Btn_Observable {
        private final SimpleStringProperty name;
        private final SimpleStringProperty url;
        private final SimpleStringProperty privilege;

        public BtnSub() {
            this.name = new SimpleStringProperty();
            this.url = new SimpleStringProperty();
            this.privilege = new SimpleStringProperty();
        }

        public BtnSub(String name) {
            this.name = new SimpleStringProperty(name);
            this.url = new SimpleStringProperty();
            this.privilege = new SimpleStringProperty();
        }

        @Override
        public ObservableStringValue nameProperty() {
            return name;
        }

        @Override
        public ObservableStringValue urlProperty() {
            return url;
        }

        @Override
        public ObservableStringValue privilegeProperty() {
            return privilege;
        }

        @Override
        public String getName() {
            return name.get();
        }

        @Override
        public String getUrl() {
            return url.get();
        }

        @Override
        public String getPrivilege() {
            return privilege.get();
        }
    }
}
