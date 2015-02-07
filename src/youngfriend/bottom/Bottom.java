package youngfriend.bottom;

import youngfriend.txt.TxtBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandongyu on 15/1/17.
 */
public class Bottom {
    private String weixin_path;
    private List<TxtBean> txt1 = new ArrayList<>();
    private List<TxtBean> txt2 = new ArrayList<>();

    public Bottom() {
    }

    public Bottom(String weixin_path) {
        this.weixin_path = weixin_path;
    }

    public Bottom(String weixin_path, List<TxtBean> txt1, List<TxtBean> txt2) {
        this.weixin_path = weixin_path;
        this.txt1 = txt1;
        this.txt2 = txt2;
    }

    public String getWeixin_path() {
        return weixin_path;
    }

    public void setWeixin_path(String weixin_path) {
        this.weixin_path = weixin_path;
    }

    public List<TxtBean> getTxt1() {
        return txt1;
    }

    public void setTxt1(List<TxtBean> txt1) {
        this.txt1 = txt1;
    }

    public List<TxtBean> getTxt2() {
        return txt2;
    }

    public void setTxt2(List<TxtBean> txt2) {
        this.txt2 = txt2;
    }
}
