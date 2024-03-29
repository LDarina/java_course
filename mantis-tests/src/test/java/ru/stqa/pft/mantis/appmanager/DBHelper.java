package ru.stqa.pft.mantis.appmanager;

import ru.stqa.pft.mantis.model.UserData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

public class DBHelper {
  private final SessionFactory sessionFactory;

  public DBHelper(ApplicationManager app) {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users getUsers() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where username != 'administrator'").list();
    session.getTransaction().commit();
    session.close();

    return new Users(result);
  }
}

