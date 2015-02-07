package youngfriend.header.nav;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by pandongyu on 15/1/14.
 */
@JsonDeserialize(as = Nav_Service_Sub.Nav_Sub.class)
public interface Nav {
    String getTitle() ;
    String getUrl() ;
    void setUrl(String url) ;
    void setTitle(String title);
}
