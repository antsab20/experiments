package com.automation.audit.repository;

import com.automation.audit.entity.AuditErrorMessage;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class AuditErrorMessageRepository implements PanacheRepository<AuditErrorMessage> {
    private static final Logger LOGGER = Logger.getLogger("AuditErrorMessageRepository");

    @Transactional
    public void audit(AuditErrorMessage auditErrorMessage) {
        try {
            LOGGER.debug(String.format("Will audit the following message: [%s]", auditErrorMessage));
            persist(auditErrorMessage);
        } catch (Exception e) {
            LOGGER.error("An error occurred during audit {}", e);
        }
    }

}
