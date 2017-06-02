package com.me.Dao;

import java.util.List;

import org.apache.log4j.spi.RootLogger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.pojo.CropCategory;
import com.me.pojo.Person;
import com.me.pojo.ProductCategory;

public class RegistrationDao extends Dao {

	public RegistrationDao() {

	}

	public boolean addPerson(Person person) {
		try {
			begin();
			getSession().save(person);
			commit();
		} catch (HibernateException e) {
			rollback();
		} finally {
			close();
		}
		return true;
	}

	public Person findUser(String userName, String password) {
		Person person = null;
		try {
			begin();
			Query q = getSession().createQuery("from Person where username = :us and password = :pw and status = :st");
			q.setString("us", userName);
			q.setString("pw", password);
			q.setString("st", "active");
			person = (Person) q.uniqueResult();
			commit();
			return person;
		} catch (HibernateException e) {
			rollback();
			return person;
		} finally {
			close();
		}

	}

	public List<Person> getUsers(int startingNo) {
		List<Person> personList = null;
		try {
			if (startingNo > 1) {
				startingNo=(startingNo-1)*5;
			}
			else
			{
				startingNo=startingNo-1;
			}
			begin();
			Query q = getSession().createQuery("from Person");
			q.setFirstResult(startingNo);
			q.setMaxResults(5);
			personList = q.list();
			commit();
			return personList;
		} catch (HibernateException e) {
			rollback();
			return personList;
		} finally {
			close();
		}
	}

	public int getToralUersCount() {
		int tc = 0;
		try {
			begin();
			Query query = getSession().createQuery("from Person");
			List<Person> pc = query.list();
			tc = pc.size();
			commit();
			return tc;
		} catch (HibernateException e) {
			rollback();
			return tc;
		} finally {
			close();
		}
	}

	public boolean addCategory(ProductCategory c) {

		try {
			begin();
			getSession().save(c);
			commit();
			return true;
		} catch (HibernateException e) {
			rollback();
			return false;
		} finally {
			close();
		}
	}

	public List<ProductCategory> getCategories() {
		List<ProductCategory> pcList = null;
		try {
			begin();
			Query q = getSession().createQuery("from ProductCategory");
			pcList = q.list();
			commit();
			return pcList;
		} catch (HibernateException e) {
			rollback();
			return pcList;
		} finally {
			close();
		}
	}

	public boolean activateAccount(int id) {
		try {
			begin();
			Query q = getSession().createQuery("update Person set status = :val where personId = :id");
			q.setParameter("val", "active");
			q.setParameter("id", id);
			int result = q.executeUpdate();
			commit();
			if (result > 0)
				return true;
			else
				return false;

		} catch (HibernateException e) {
			rollback();
			return false;
		} finally {
			close();
		}
	}

	public boolean checkUsernameUnique(String username) {

		Person person=null;
		try {
			begin();
			Criteria cri =getSession().createCriteria(Person.class);
			              cri.add(Restrictions.eq("userAccount.username", username));
			person = (Person) cri.uniqueResult();
			System.out.println(" result................... " + person);
			commit();
			if (null == person)
				return false;
			else
				return true;

		} catch (HibernateException e) {
			rollback();
			System.out.println(" eeeeeeee " + e);
			return false;
		} finally {
			close();
		}
	}

	public Person getPerson(int personId) {
		Person person = null;
		try {
			begin();
			person = (Person) getSession().get(Person.class, personId);
			commit();

		} catch (HibernateException e) {
			rollback();
		} finally {
			close();
		}
		return person;
	}

	public int getProductCategorieCount() {
		int tc = 0;
		try {
			begin();
			Query q = getSession().createQuery("from ProductCategory");
			List<ProductCategory> pc = q.list();
			tc = pc.size();
			return tc;
		} catch (HibernateException e) {
			rollback();
			return tc;
		} finally {
			close();
		}
	}

	public List<ProductCategory> getProductCategories() {
		List<ProductCategory> proList = null;
		try {
			begin();
			Query q = getSession().createQuery("from ProductCategory");
			proList = q.list();
			commit();
			return proList;
		} catch (HibernateException e) {
			rollback();
			return proList;
		} finally {
			close();
		}
	}

	public ProductCategory getPc(String name) {
		ProductCategory p = null;
		try {

			begin();
			Query q = getSession().createQuery("from ProductCategory where name = :n");
			q.setString("n", name);
			p = (ProductCategory) q.uniqueResult();
			return p;

		} catch (HibernateException e) {
			rollback();
			return p;
		} finally {
			close();
		}
	}

	public CropCategory getcc(String name) {

		CropCategory c = null;
		try {

			begin();
			Query q = getSession().createQuery("from CropCategory where name = :n");
			q.setString("n", name);
			c = (CropCategory) q.uniqueResult();
			return c;

		} catch (HibernateException e) {
			rollback();
			return c;
		} finally {
			close();
		}
	}
}
