package com.automation.audit.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public abstract class AuditEntity {

    @Id
    private UUID id = UUID.randomUUID();
    @Column
    private Date timestampCreation;

    public UUID getId() {
        return this.id;
    }

    public Date getTimestampCreation() {
        return timestampCreation;
    }

    public void setTimestampCreation(Date timestampCreation) {
        this.timestampCreation = timestampCreation;
    }
}
