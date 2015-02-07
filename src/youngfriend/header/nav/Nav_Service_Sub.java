package youngfriend.header.nav;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import youngfriend.bean_table_base.Bean_Utils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pandongyu on 15/1/14.
 */
public class Nav_Service_Sub implements Nav_Service {
    private final ObservableList<Nav> data = FXCollections.observableArrayList();
    public static final Map<String, String> map = new LinkedHashMap<>();
    {

        map.put("首页", "index.jsp");
        map.put("公司新闻", "news.jsp?newType=10");
        map.put("业界资讯", "news.jsp?newType=20");
        map.put("公司公告", "news.jsp?newType=30");
        map.put("党团工会", "news.jsp?newType=40");
        map.put("问卷调查", "news.jsp?newType=50");
        map.put("期刊管理", "news.jsp?newType=60");
    }

    @Override
    public void add(int index) {
        data.add(Bean_Utils.getIndex(index, data), new Nav_Sub("首页", map.get("首页")));
    }

    @Override
    public void del(ObservableList<Nav> selects) {
        if (selects == null) {
            return;
        }
        data.removeAll(selects);
    }

    @Override
    public ObservableList<Nav> list() {
        return data;
    }

    @Override
    public void init(List<Nav> datas) {
        data.setAll(datas);
    }


    public static final class Nav_Sub implements Nav_Observable {
        private final SimpleStringProperty url;
        private final SimpleStringProperty title;

        public Nav_Sub() {
            url=new SimpleStringProperty();
            title=new SimpleStringProperty();
        }

        public Nav_Sub(String title, String url) {
            this.title = new SimpleStringProperty(title);
            this.url = new SimpleStringProperty(url);
        }

        @Override
        public ObservableStringValue titleProperty() {
            return title;
        }

        @Override
        public ObservableStringValue urlProperty() {
            return url;
        }

        @Override
        public String getTitle() {
            return title.get();
        }

        @Override
        public String getUrl() {
            return url.get();
        }

        @Override
        public void setUrl(String url) {
            this.url.setValue(url);
        }

        @Override
        public void setTitle(String title) {
            this.title.setValue(title);
        }
    }

}
