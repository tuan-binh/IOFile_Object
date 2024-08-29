package business.feature;

public interface IGenericFeature<T,E> {

    void addOrUpdate(T t);
    void delete(E id);
    int findIndexByID(E id);
}
