package com.me.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.pojo.OrderItem;
import com.me.pojo.Product;
import com.me.pojo.Supplier;

public class SupplierDao extends Dao {

	public SupplierDao() {

	}

	public boolean addSupplier(Supplier supplier) {
		try {
			begin();
			getSession().save(supplier);

			commit();
		} catch (HibernateException e) {
			rollback();
			return false;
		} finally {
			close();
		}
		return true;
	}

	public Supplier getSupplier(int personId) {
		Supplier supplier = null;
		try {
			begin();
			Query q = getSession().createQuery("from Supplier where person.personId = :id ");
			q.setInteger("id", personId);
			supplier = (Supplier) q.uniqueResult();
			commit();

		} catch (HibernateException e) {
			rollback();
		} finally {
			close();
		}
		return supplier;
	}

	public boolean addProduct(Product product) {
		try {
			begin();
			getSession().save(product);
			commit();
			return true;

		} catch (HibernateException e) {
			rollback();
			System.out.println("Error in Product Adding" + e);
			return false;
		} finally {
			close();
		}
	}

	public List<Product> getProduct(int supplierId, int c) {
		List<Product> proList = null;
		if (c > 1) {
			c=(c-1)*4;
		}
		else
		{
			c=c-1;
		}
		try {
			begin();
			Query q = getSession().createQuery("from Product where supplier = :id");
			q.setInteger("id", supplierId);
			q.setFirstResult(c);
			q.setMaxResults(4);
			proList = q.list();
			return proList;
		} catch (HibernateException e) {
			rollback();
			return proList;
		} finally {
			close();
		}
	}

	public int getTotalProductsOfSupplier(int sId) {
		int tP = 0;
		Supplier supplier = null;
		try {
			begin();
			Query q = getSession().createQuery("from Supplier where supplierId = :id");
			q.setInteger("id", sId);
			supplier = (Supplier) q.uniqueResult();
			tP = supplier.getProductList().size();
			commit();
			return tP;

		} catch (HibernateException e) {
			rollback();
			return tP;
		} finally {
			close();
		}
	}

	public int updateProducts(int id, int price, int quantity) {
		int success = 0;
		System.out.println("Error ffff");
		try {
			begin();
			Query query = getSession().createQuery(
					"UPDATE Product set pricePerUnit = :price,availableQuantity = :sq WHERE productId = :pId");
			query.setParameter("price", price);
			query.setParameter("sq", quantity);
			query.setParameter("pId", id);
			success = query.executeUpdate();
			if (success > 0)
				return success;
			else
				return success;

		} catch (HibernateException e) {
			rollback();
			System.out.println("Error");
			return success;
		} finally {
			close();
		}
	}

	public Product getProductToAddToCart(int pid) {
		Product product = null;
		try {
			begin();
			product = (Product) getSession().get(Product.class, pid);
			commit();
			return product;
		} catch (HibernateException e) {
			rollback();
			return product;
		} finally {
            close();
		}
	}
	
	public boolean updatePrduct(int id,int q)
	{
		
		try
		{	
			begin();
			Query query=getSession().createQuery("UPDATE Product set availableQuantity = :q where productId = :pid");
			query.setParameter("q",q); 
			query.setParameter("pid",id);
			query.executeUpdate();
			return true;
		}catch(HibernateException e)
		{
			rollback();
			return false;
		}finally {
			close();
		}
	}
	
	public List<OrderItem> getSuppliersOrderItem(int id,int pn)
	{
		List<OrderItem> itemList=null;
		if(pn>1)
		{
			pn=(pn-1)*8;
			
		}
		else
		{
			pn=pn-1;
		}
		try
		{
			begin();
			Query q=getSession().createQuery("from OrderItem where product.supplier = :id");
			q.setInteger("id",id);
			q.setFirstResult(pn);
			q.setMaxResults(8);
			itemList=q.list();
			commit();
			return itemList;

		}
		catch(HibernateException e)
		{
			rollback();
			System.out.println("Erorrrrrr in OrderItem" + e);
			return itemList;
		}finally
		{
			close();
		}
	}
	
	public long getTotalSupplierOrderCount(int id)
	{
		long to=0;
		try
		{
			begin();
			Query q=getSession().createQuery("select count(orderItemId) from OrderItem where product.supplier = :id");
			q.setInteger("id",id);
			to=(Long)q.uniqueResult();
			commit();
			return to;
		}catch(HibernateException e)
		{
			System.out.println("Erorrrrrr in OrderItem" + e);
			rollback();
			return to;
		}finally 
		{
			close();
		}
	}
	
	public List<Product> getProductsByFilter(String cat,int price1,int price2)
	{
		List<Product> proList=null;
		try
		{
			begin();
			if(price1==0&&price2==0)
			{
			Criteria cri=getSession().createCriteria(Product.class);
			proList=(List<Product>)cri.createCriteria("pCategory")
			        .add(Restrictions.eq("name",cat))
			        .list();
			}
			else if(cat.equals("null"))
			{
				Criteria cri=getSession().createCriteria(Product.class)
		                 .add(Restrictions.between("pricePerUnit",price1,price2));
		                 
				proList=cri.list();
			}
			else
			{
				Criteria cri=getSession().createCriteria(Product.class)
						.add(Restrictions.between("pricePerUnit",price1,price2));
				proList=(List<Product>)cri.createCriteria("pCategory")
				        .add(Restrictions.eq("name",cat))
				        .list();
			}
			commit();
			return proList;
		}catch(HibernateException e)
		{
			System.out.println("Exception in Filter ---------" + e);
			rollback();
			return proList;
		}finally
		{
			close();
		}
	}
	
	
	
	public long getProductsByFilterCount(String cat,int price1,int price2)
	{
		
		long tc=0;
		System.out.println(cat==null);
		try
		{
			begin();
			if(price1==0&&price2==0)
			{
			Criteria cri=getSession().createCriteria(Product.class);
			tc=cri.createCriteria("pCategory")
			        .add(Restrictions.eq("name",cat))
			        .list().size();
			System.out.println("ONly CAt is Excuting");
			
			}
			else if(cat.equals("null"))
			{
				Criteria cri=getSession().createCriteria(Product.class)
			                 .add(Restrictions.ge("pricePerUnit",price1))
			                 .add(Restrictions.le("pricePerUnit",price2));
				tc=cri.list().size();
				System.out.println("ONly Price is Excuting");
			}
			else
			{
				Criteria cri=getSession().createCriteria(Product.class)
						.add(Restrictions.between("pricePerUnit",price1,price2));
				tc=cri.createCriteria("pCategory")
				        .add(Restrictions.eq("name",cat))
				        .list().size();
				System.out.println("Both is Excuting");
			}
			commit();
			return tc;
		}catch(HibernateException e)
		{
			System.out.println("Exception in Filter ---------" + e);
			rollback();
			return tc;
		}finally
		{
			close();
		}
	}
	
	
	public boolean changeOrderStatus(int oId,String ns)
	{
		int result=0;
		
		try
		{	
			begin();
			Query q=getSession().createQuery("update OrderItem set status = :mes where orderItemId = :id");
			q.setParameter("mes",ns);
			q.setParameter("id",oId);
			result=q.executeUpdate();
			commit();
			if(result>0)
			return true;
			else
		    return false;
		}catch(HibernateException e)
		{
			rollback();
			return false;
			
		}finally
		{
			close();
		}
	}
	
	public Product getPro(int id)
	{
		Product p=null;
		try
		{
			begin();
			p=(Product)getSession().get(Product.class,id);
			commit();
			return p;
			
		}catch(HibernateException e)
		{
			rollback();
			return p;
		}finally
		{
			close();
		}
	}
	
	
	
}
