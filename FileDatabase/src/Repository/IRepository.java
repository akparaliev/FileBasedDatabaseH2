package Repository;

import java.util.Map;

public interface  IRepository<T> {
    Map<Integer, T> GetAll();
    T GetById(Integer id);
    void Add(T entity);
    void Update(T entity);
    void Remove(Integer id);
}
