package school.connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionUtils {

    private static SessionFactory factory;

    static {
        factory = new AnnotationConfiguration()
                .configure().buildSessionFactory();
    }

    public static Session getSession() {
        return factory.openSession();
    }

    public static void shutDown() {
        if(!factory.isClosed()) {
            factory.close();
        }
    }

}
