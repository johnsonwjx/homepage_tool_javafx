package youngfriend.main.tab;

import javafx.beans.value.ObservableStringValue;

/**
 * Created by pandongyu on 15/1/15.
 */
public interface TabBean_Observable extends  TabBean{
    ObservableStringValue typeProperty();

    ObservableStringValue logoProperty();

    ObservableStringValue titleProperty();

    ObservableStringValue heightProperty();
}
