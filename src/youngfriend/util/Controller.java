package youngfriend.util;

/**
 * Created by pandongyu on 15/1/17.
 */
public interface Controller<T>  {
        T getBean() ;
        void setBean(T bean) ;
}
