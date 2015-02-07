package youngfriend.main.btn;

import javafx.beans.value.ObservableStringValue;

/**
 * Created by pandongyu on 15/1/15.
 */
public interface Btn_Observable extends Btn {
    ObservableStringValue nameProperty();
    ObservableStringValue urlProperty();
    ObservableStringValue privilegeProperty();
}
