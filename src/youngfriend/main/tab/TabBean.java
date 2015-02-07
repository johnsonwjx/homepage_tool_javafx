package youngfriend.main.tab;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by pandongyu on 15/1/15.
 */
@JsonDeserialize(as = TabBean_ServiceSub.TabBeanSub.class)
public interface TabBean {
    String getType();
    String getLogo();
    String getTitle() ;
    String getHeight() ;
    void setHeight(String height) ;
    void setTitle(String title);
    void setType(String type);
    void setLogo(String logo) ;
}
