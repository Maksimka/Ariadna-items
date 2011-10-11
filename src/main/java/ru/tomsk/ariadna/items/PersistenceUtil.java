package ru.tomsk.ariadna.items;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Шаймарданов Максим Маратович
 */
public class PersistenceUtil {

    private static final Logger logger = LoggerFactory.getLogger(PersistenceUtil.class);

    private static final EntityManagerFactory entityManagerFactory;

    private static final EntityManager entityManager;

    static {
        logger.info("Открываем соендинение с бд");
        // Use persistence.xml configuration
        entityManagerFactory = Persistence.createEntityManagerFactory("ariadna.items");
        // Retrieve an application managed entity manager
        entityManager = entityManagerFactory.createEntityManager();
        // Work with the EM
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static void close() {
        logger.info("Закрываем соендинение с бд");
        entityManager.close();
        entityManagerFactory.close(); //close at application end   
    }
}