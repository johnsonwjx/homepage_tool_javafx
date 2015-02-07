package youngfriend.main.btn;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by pandongyu on 15/1/15.
 */
@JsonDeserialize(as = Btn_ServiceSub.BtnSub.class)
public interface Btn {
    String getName() ;
    String getUrl() ;
    String getPrivilege();
}
