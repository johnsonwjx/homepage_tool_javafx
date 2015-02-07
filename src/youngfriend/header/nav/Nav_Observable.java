package youngfriend.header.nav;

import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import youngfriend.util.Name_Pair;

/**
 * Created by pandongyu on 15/1/14.
 */
public interface Nav_Observable extends Nav {
    ObservableStringValue titleProperty() ;
    ObservableStringValue urlProperty() ;
}
