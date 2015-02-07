package youngfriend.main;

import youngfriend.main.btn.Btn;
import youngfriend.main.tab.TabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandongyu on 15/1/17.
 */
public class Main {
    private List<TabBean> tabs_left = new ArrayList<>();
    private List<TabBean> tabs_right = new ArrayList<>();
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Btn> getBtns() {
        return btns;
    }

    public void setBtns(List<Btn> btns) {
        this.btns = btns;
    }

    private List<Btn> btns = new ArrayList<>();

    public Main() {
    }

    public Main(String title,List<TabBean> tabs_left, List<TabBean> tabs_right,List<Btn> btns) {
        this.title=title;
        this.tabs_left = tabs_left;
        this.tabs_right = tabs_right;
        this.btns=btns;
    }

    public List<TabBean> getTabs_left() {
        return tabs_left;
    }

    public void setTabs_left(List<TabBean> tabs_left) {
        this.tabs_left = tabs_left;
    }

    public List<TabBean> getTabs_right() {
        return tabs_right;
    }

    public void setTabs_right(List<TabBean> tabs_right) {
        this.tabs_right = tabs_right;
    }
}
