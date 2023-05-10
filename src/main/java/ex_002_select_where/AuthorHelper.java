package ex_002_select_where;


import ex_002_select_where.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();


        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery cq = cb.createQuery(Author.class);

        Root<Author> root = cq.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)

        Selection[] selections = {root.get("id"), root.get("name")};

        cq.select(root).where(cb.like(root.<String>get("lastName"), "%ush%"));


         //этап выполнения запроса
        Query query = session.createQuery(cq);


        List<Author> authorList = query.getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id); // получение объекта по id
        session.close();
        return author;
    }

    public Author addAuthor(Author author){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(author); // сгенерит ID и вставит в объект

        session.getTransaction().commit();

        session.close();


        return author;

    }
    public void updateAuthorsWithLastNameLengthGreaterThanSeven() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaUpdate<Author> update = cb.createCriteriaUpdate(Author.class);
        Root<Author> root = update.from(Author.class);

        update.set(root.get("name"), "1");
        update.where(cb.greaterThan(cb.length(root.get("lastName")), 7));

        Query query = session.createQuery(update);
        int rowCount = query.executeUpdate();

        session.getTransaction().commit();
        session.close();

        System.out.println("Updated " + rowCount + " authors");
    }

    public List<Author> searchAuthors(String searchExpression) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);
        Root<Author> root = cq.from(Author.class);

        cq.select(root).where(cb.or(
                cb.like(cb.lower(root.<String>get("name")), "%" + searchExpression.toLowerCase() + "%"),
                cb.like(cb.lower(root.<String>get("lastName")), "%" + searchExpression.toLowerCase() + "%")));

        TypedQuery<Author> query = session.createQuery(cq);

        List<Author> authors = query.getResultList();

        session.close();

        return authors;
    }
}

