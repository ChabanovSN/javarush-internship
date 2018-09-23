package ru.javarush.asaraykin.pccomponents.config.app;

import ru.javarush.asaraykin.pccomponents.core.model.ComputerComponent;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
public class HibernateSessionFactoryUtil
{
    private  SessionFactory sessionFactory;
    public HibernateSessionFactoryUtil(){}

    @Bean
    public  SessionFactory sessionFactory() {
        try {
            StandardServiceRegistry standardRegistry =
                    new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
            Metadata metaData =
                    new MetadataSources(standardRegistry).addAnnotatedClass(ComputerComponent.class).getMetadataBuilder().build();
            sessionFactory = metaData.getSessionFactoryBuilder().build();
        } catch (Throwable th) {
            System.err.println("Enitial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
        return sessionFactory;
}

}
