package youngfriend.txt;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import youngfriend.bean_table_base.Bean_Utils;
import youngfriend.header.Header;

import java.util.Collection;
import java.util.List;

/**
 * Created by pandongyu on 15/1/14.
 */
public class TxtBean_Service_Sub implements TxtBean_Service {
    private final ObservableList<TxtBean> data = FXCollections.observableArrayList();

    @Override
    public void add(int index) {
        data.add(Bean_Utils.getIndex(index, data), new TxtBean_Sub("文字"));
    }

    @Override
    public void del(ObservableList<TxtBean> selects) {
        if (selects == null) {
            return;
        }
        data.removeAll(selects);
    }

    @Override
    public ObservableList<TxtBean> list() {
        return data;
    }

    @Override
    public void init(List<TxtBean> datas) {
        data.setAll(datas);
    }

    public static final class TxtBean_Sub implements TxtBean_Observable {
        public TxtBean_Sub() {
            txt = new SimpleStringProperty();
        }

        public TxtBean_Sub(String txt) {
            this.txt = new SimpleStringProperty(txt);
        }

        private final SimpleStringProperty txt;

        @Override
        public ObservableStringValue txtProperty() {
            return txt;
        }

        @Override
        public String getTxt() {
            return txt.get();
        }
    }

}
