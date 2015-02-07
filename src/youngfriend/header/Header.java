package youngfriend.header;

import youngfriend.header.nav.Nav;
import youngfriend.txt.TxtBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pandongyu on 15/1/14.
 */
public class Header {
    private String logopath;
    private String flashpath;
    private List<TxtBean> txt1 = new ArrayList<TxtBean>();
    private List<TxtBean> txt2 = new ArrayList<TxtBean>();
    private List<Nav> navs = new ArrayList<>();

    public List<Nav> getNavs() {
        return navs;
    }

    public void setNavs(List<Nav> navs) {
        this.navs = navs;
    }

    public Header() {
    }

    public Header(String logopath, String flashpath, List<TxtBean> txt1, List<TxtBean> txt2, List<Nav> navs) {
        this.logopath = logopath;
        this.flashpath = flashpath;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.navs = navs;
    }

    public String getFlashpath() {
        return flashpath;
    }

    public void setFlashpath(String flashpath) {
        this.flashpath = flashpath;
    }


    public List<TxtBean> getTxt1() {
        return txt1;
    }

    public void setTxt1(List<TxtBean> txt1) {
        this.txt1 = txt1;
    }

    public void setTxt2(List<TxtBean> txt2) {
        this.txt2 = txt2;
    }


    public List<TxtBean> getTxt2() {
        return txt2;
    }


    public String getLogopath() {
        return logopath;
    }

    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }


}
