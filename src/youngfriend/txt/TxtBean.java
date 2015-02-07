package youngfriend.txt;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by pandongyu on 15/1/14.
 */
@JsonDeserialize(as= TxtBean_Service_Sub.TxtBean_Sub.class)
public interface TxtBean {
    public String getTxt();
}
