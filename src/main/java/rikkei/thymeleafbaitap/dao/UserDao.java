package rikkei.thymeleafbaitap.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import rikkei.thymeleafbaitap.entity.Users;

@Repository
public class UserDao
{
    @Autowired
    private SessionFactory sessionFactory;

    public boolean isTrueAcc(String userName, String password)
    {
       Session session = sessionFactory.openSession();
      Users users = session.createQuery("from Users u where u.username = :userName and u.password = :password",Users.class).getSingleResult();
        return users != null;
    }
}
