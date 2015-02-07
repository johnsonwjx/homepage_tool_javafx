package youngfriend.app;

import youngfriend.bottom.Bottom;
import youngfriend.header.Header;
import youngfriend.main.Main;

/**
 * Created by pandongyu on 15/1/19.
 */
public class Page {
    private Header header=new Header() ;
    private Main main =new Main();
    private Bottom bottom =new Bottom();

    public Page() {
    }

    public Page(Header header, Main main, Bottom bottom) {
        this.header = header;
        this.main = main;
        this.bottom = bottom;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Bottom getBottom() {
        return bottom;
    }

    public void setBottom(Bottom bottom) {
        this.bottom = bottom;
    }
}
