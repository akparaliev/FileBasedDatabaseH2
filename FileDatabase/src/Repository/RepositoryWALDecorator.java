package Repository;

import java.util.Map;

public class RepositoryWALDecorator<T> implements IRepository<T> {
    protected IRepository<T> decoratedRepository;
    protected WriteAheadLog<T> writeAheadLog;

    public RepositoryWALDecorator(IRepository<T> decoratedRepository, String walFileName) {
        this.decoratedRepository = decoratedRepository;
        writeAheadLog = new WriteAheadLog<>(walFileName);
    }

    @Override
    public Map<Integer, T> GetAll() {
        return decoratedRepository.GetAll();
    }

    @Override
    public T GetById(Integer id) {
        return decoratedRepository.GetById(id);
    }

    @Override
    public void Add(T entity) {
        decoratedRepository.Add(entity);
        writeAheadLog.LogAddOperation(0, entity, entity.getClass().getSimpleName());
    }

    @Override
    public void Update(T entity) {
        decoratedRepository.Update(entity);
        writeAheadLog.LogUpdateOperation(entity, entity.getClass().getSimpleName());
    }

    @Override
    public void Remove(Integer id) {
        decoratedRepository.Remove(id);
        writeAheadLog.LogRemoveOperation(decoratedRepository.GetById(id).getClass().getSimpleName(), id);
    }
}
