package com.automation.audit.repository;

import com.automation.audit.entity.AuditMessage;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AuditMessageRepository implements PanacheRepository<AuditMessage> {
    private static final Logger LOGGER = Logger.getLogger("AuditMessageRepository");

    @Transactional
    public void audit(AuditMessage auditMessage) {
        try {
            LOGGER.debug(String.format("Will audit the following message: [%s]", auditMessage));
            persist(auditMessage);
        } catch (Exception e) {
            LOGGER.error("An error occurred during audit {}", e);
        }
    }

}
