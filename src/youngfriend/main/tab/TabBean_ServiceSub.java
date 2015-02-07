package youngfriend.main.tab;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import youngfriend.bean_table_base.Bean_Utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pandongyu on 15/1/15.
 */
public class TabBean_ServiceSub implements TabBean_Service {
    private final ObservableList<TabBean> data = FXCollections.observableArrayList();
    public final static Map<String, String> typeMap = new LinkedHashMap<>();

    {
        typeMap.put("滚动信息", "00");
        typeMap.put("滚动图片", "05");
        typeMap.put("公司新闻", "10");
        typeMap.put("业界资讯", "20");
        typeMap.put("公司公告", "30");
        typeMap.put("党团工会", "40");
        typeMap.put("问卷调查", "50");
        typeMap.put("期刊管理", "60");
        typeMap.put("应用附件", "90");
    }

    @Override
    public void add(int index) {
        data.add(Bean_Utils.getIndex(index, data), new TabBeanSub("滚动信息"));
    }

    @Override
    public void del(ObservableList<TabBean> selects) {
        if (selects != null) {
            data.removeAll(selects);
        }
    }

    @Override
    public ObservableList<TabBean> list() {
        return data;
    }

    @Override
    public void init(List<TabBean> datas) {
        data.setAll(datas);
    }

    public static final class TabBeanSub implements TabBean_Observable {
        private final SimpleStringProperty type;
        private final SimpleStringProperty logo;
        private final SimpleStringProperty title;
        private final SimpleStringProperty height;

        public TabBeanSub() {
            this.type = new SimpleStringProperty();
            this.height= new SimpleStringProperty("340");
            this.logo=new SimpleStringProperty();
            this.title=new SimpleStringProperty();
        }

        public TabBeanSub(String type) {
            this.type = new SimpleStringProperty(type);
            this.height= new SimpleStringProperty("340");
            this.logo=new SimpleStringProperty();
            this.title=new SimpleStringProperty(type);

        }

        @Override
        public ObservableStringValue typeProperty() {
            return type;
        }

        @Override
        public ObservableStringValue logoProperty() {
            return logo;
        }

        @Override
        public ObservableStringValue titleProperty() {
            return title;
        }

        @Override
        public ObservableStringValue heightProperty() {
            return height;
        }

        @Override
        public String getType() {
            return type.get();
        }

        @Override
        public String getLogo() {
            return logo.get();
        }

        @Override
        public String getTitle() {
            return title.get();
        }

        @Override
        public String getHeight() {
            return height.get();
        }

        @Override
        public void setHeight(String height) {
            this.height.setValue(height);
        }

        @Override
        public void setTitle(String title) {
            this.title.setValue(title);
        }

        @Override
        public void setType(String type) {
            this.type.setValue(type);
        }

        @Override
        public void setLogo(String logo) {
            this.logo.setValue(logo);
        }
    }
}
