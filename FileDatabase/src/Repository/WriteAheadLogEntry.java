package Repository;

public class WriteAheadLogEntry<T> {
    private String operationType;
    private String entityType;
    private T entity;
    private Integer entityId;

    public WriteAheadLogEntry(String operationType, String entityType, T entity, Integer entityId) {
        this.operationType = operationType;
        this.entityType = entityType;
        this.entity = entity;
        this.entityId = entityId;
    }

    public WriteAheadLogEntry(String operationType, String entityType, T entity) {
        this.operationType = operationType;
        this.entityType = entityType;
        this.entity = entity;
        this.entityId = null;
    }

    public WriteAheadLogEntry(String operationType, String entityType, Integer entityId) {
        this.operationType = operationType;
        this.entityType = entityType;
        this.entity = null;
        this.entityId = entityId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }
}
