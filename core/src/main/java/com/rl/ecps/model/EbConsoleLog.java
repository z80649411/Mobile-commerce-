package com.rl.ecps.model;

import java.util.Date;

public class EbConsoleLog {
    private Long consoleLogId;

    private String entityName;

    private Long entityId;

    private Long userId;

    private String opType;

    private Date opTime;

    private String notes;

    private String tableName;

    public Long getConsoleLogId() {
        return consoleLogId;
    }

    public void setConsoleLogId(Long consoleLogId) {
        this.consoleLogId = consoleLogId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}