package rikkei.thymeleafbaitap.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import rikkei.thymeleafbaitap.entity.Employee;

import java.util.Collections;
import java.util.List;

@Repository
public class EmployeeDao implements IDaoEmployee
{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getEmployeeList()
    {
        Session session = sessionFactory.openSession();
        try {
            List list = session.createQuery("from Employee ").list();
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return null;
    }

    @Override
    public Employee getEmployeeById(int id)
    {
        Session session = sessionFactory.openSession();
        try
        {
            return session.get(Employee.class,id);
        }catch (Exception e){
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return null;
    }

    @Override
    public void addEmployee(Employee employee)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();

        }catch (
                Exception e
        ){
            session.getTransaction().rollback();
            throw e;

        }finally
        {
            session.close();
        }

    }

    @Override
    public void updateEmployee(Employee employee)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            session.update(employee);
            session.getTransaction().commit();

        }catch (
                Exception e
        ){
            e.printStackTrace();session.getTransaction().rollback();
        }finally
        {
            session.close();
        }



    }

    @Override
    public void deleteEmployee(int id)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.beginTransaction();
            session.delete(getEmployeeById(id));
            session.getTransaction().commit();

        }catch (
                Exception e
        ){
            e.printStackTrace();session.getTransaction().rollback();
        }finally
        {
            session.close();
        }

    }
}
