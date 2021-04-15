package com.automation.audit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "auditerrormessage")
public class AuditErrorMessage extends AuditEntity {
    @Column
    private String stackTrace;
    @Column
    private String auditEventTypeCode;

    public AuditErrorMessage() {
        super.setTimestampCreation(new Date());
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getAuditEventTypeCode() {
        return auditEventTypeCode;
    }

    public void setAuditEventTypeCode(String auditEventTypeCode) {
        this.auditEventTypeCode = auditEventTypeCode;
    }
}
