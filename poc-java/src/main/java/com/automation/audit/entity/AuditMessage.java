package com.automation.audit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "auditmessage")
public class AuditMessage extends AuditEntity {
    @Column
    private String subjectMessageType;
    @Column
    private String subjectStatus;
    @Column
    private String decision;

    public AuditMessage() {
        super.setTimestampCreation(new Date());
    }

    public String getSubjectMessageType() {
        return subjectMessageType;
    }

    public void setSubjectMessageType(String subjectMessageType) {
        this.subjectMessageType = subjectMessageType;
    }

    public String getSubjectStatus() {
        return subjectStatus;
    }

    public void setSubjectStatus(String subjectStatus) {
        this.subjectStatus = subjectStatus;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
