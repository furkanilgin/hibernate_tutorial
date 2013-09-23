package javabrains.tutorial_30;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class HibernateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SessionFactory sessionFactory = new Configuration().configure()
				.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Criteria criteria = session.createCriteria(UserDetails.class);
		criteria.add(Restrictions.eq("userName", "User 10")).add(
				Restrictions.gt("userId", 5));
/*
		criteria.add(Restrictions.or(Restrictions.between("userId", 0, 3),
				Restrictions.between("userId", 7, 10)));*/

		List<UserDetails> users = (List<UserDetails>) criteria.list();

		session.getTransaction().commit();
		session.close();

		for (UserDetails user : users) {
			System.out.println(user.getUserName());
		}
	}
}
