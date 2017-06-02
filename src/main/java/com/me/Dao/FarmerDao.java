package com.me.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.me.pojo.Bids;
import com.me.pojo.Crop;
import com.me.pojo.CropCategory;
import com.me.pojo.Farmer;
import com.me.pojo.FarmerOrder;
import com.me.pojo.OrderItem;
import com.me.pojo.Product;

public class FarmerDao extends Dao {

	public FarmerDao() {

	}

	public boolean addFarmer(Farmer farmer) {
		try {
			begin();
			getSession().save(farmer);
			commit();
			return true;
		} catch (HibernateException e) {
			rollback();
			return false;
		} finally {
			close();
		}

	}

	public Farmer getFarmer(int personId) {
		Farmer farmer = null;
		try {
			begin();
			Query q = getSession().createQuery("from Farmer where person.personId = :id ");
			q.setInteger("id", personId);
			farmer = (Farmer) q.uniqueResult();
			commit();

		} catch (HibernateException e) {
			rollback();
		} finally {
			close();
		}
		return farmer;
	}

	public boolean addCropCategory(CropCategory c) {
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
	
	
	public List<CropCategory> getCropCategories()
	{
		List<CropCategory> cc=null;
		try
		{
			begin();
			Query q=getSession().createQuery("from CropCategory");
			cc=q.list();
			commit();
			return cc;
		}
		catch(HibernateException e)
		{
			rollback();
			return cc;
		}
		finally
		{
			close();
		}
	}
	public int getCountCropCategories()
	{
		int tc=0;
		try
		{
			begin();
			Query q=getSession().createQuery("from CropCategory");
			List<CropCategory> cc=q.list();
			tc=cc.size();
			commit();
			return tc;
		}
		catch(HibernateException e)
		{
			rollback();
			return tc;
		}
		finally
		{
			close();
		}
	}
	
	public boolean addCrop(Crop crop)
	{
		try
		{
			begin();
			getSession().save(crop);
			commit();
			return true;
		}catch(HibernateException e)
		{
			rollback();
			return false;
		}
		finally
		{
			close();
		}	
	}
	
	public List<Crop> getCrops(int id,int c)
	{
		List<Crop> cropList=null;
		if(c>1)
		{
			c=(c-1)*4;
		}
		else
		{
			c=c-1;
		}
		try
		{
			begin();
			Query q=getSession().createQuery("from Crop where farmer = :id");
			q.setInteger("id",id);
			q.setFirstResult(c);
			q.setMaxResults(4);
			cropList=q.list();
			return cropList;
		}catch(HibernateException e)
		{
			rollback();
			return cropList;
		}finally
		{
			close();
		}
	}
	
	public List<Product> getAllProducts(int pn)
	{
		List<Product> proList = null;
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
			Query query=getSession().createQuery("from Product");
			query.setFirstResult(pn);
			query.setMaxResults(8);
			proList=query.list();
			return proList;
			
		}catch(HibernateException e)
		{
			rollback();
			return proList;
			
		}finally
		{
			close();
		}
	}
	
	public long getTotalCount()
	{
		long tc=0;
		try
		{
			begin();
			Query q=getSession().createQuery("select count(productId) from Product");
			tc=(Long)q.uniqueResult();
			return tc;
		}catch(HibernateException e)
		{
		    rollback();
		    return tc;
		}finally
		{
			close();
		}
	}
	
	public long getTotalCropsCount(int id)
	{
		Farmer farmer;
		long tc=0;
		try
		{
			begin();
			Query q=getSession().createQuery("from Farmer where farmerId = :id");
			q.setInteger("id",id);
			farmer=(Farmer)q.uniqueResult();
			tc=farmer.getCropList().size();
			return tc;
		}catch(HibernateException e)
		{
		    rollback();
		    return tc;
		}finally
		{
			close();
		}
	}
	
	public boolean saveOrder(FarmerOrder order)
	{
		try
		{
			begin();
			getSession().save(order);
			commit();
			return true;
		}catch(HibernateException e)
		{
			rollback();
			return false;
		}finally
		{
			close();
		}
	}
	
	
	
	public Crop getCrop(int id)
	{
		Crop crop=null;
		try
		{
			begin();
			crop=(Crop)getSession().get(Crop.class,id);
			commit();
			return crop;
		}
		catch(HibernateException e)
		{
			rollback();
			return crop;
		}
		finally
		{
			close();
		}
	}
	
	
	public List<Bids> getBidsForCrop(int id)
	{
		List<Bids> bid=null;
		try
		{
			begin();
			Query q=getSession().createQuery("from Bids where crop = :cid");
			q.setInteger("cid",id);
			bid=q.list();
			close();
			return bid;
		}catch(HibernateException e)
		{
			rollback();
			return bid;
		}finally
		{
			close();
		}
	}
	
	public List<OrderItem> getOrderOfFarmers(int fid,int pageNo)
	{
		List<OrderItem> itemList=null;
		try
		{
			begin();
			Query q=getSession().createQuery("from OrderItem where forder.farmer1.farmerId = :fid");
			q.setInteger("fid",fid);
			q.setFirstResult(pageNo-1);
			q.setMaxResults(8);
			itemList=q.list();
			commit();
			return itemList;
			
		}catch(HibernateException e)
		{
			rollback();
			return itemList;
		}finally
		{
			close();
		}
	}
	
	public long getFarmerOrderCount(int fid)
	{
          long tc=0;
		try
		{
			begin();
			Query q=getSession().createQuery("Select count(orderItemId) from OrderItem where forder.farmer1.farmerId = :fid");
			q.setInteger("fid",fid);
		    tc=(Long)q.uniqueResult();
		    commit();
		    return tc;
		}catch(HibernateException e)
		{
			rollback();
			return tc;
		}finally
		{
			close();
		}
	}
	
	public boolean updateBidStatus(String mes,int id)
	{
		int id2=0;
		boolean up=false;
		Crop crop;
		try
		{
			begin();
			Query q=getSession().createQuery("update Bids set status = :mes where id = :id");
			q.setParameter("mes",mes);
			q.setParameter("id",id);
			int result=q.executeUpdate();
			  if(mes.equalsIgnoreCase("Accept"))
			 {
				  Bids bids=(Bids)getSession().get(Bids.class,id);
				  crop=bids.getCrop();
				  id2=crop.getId();
				  Query q1=getSession().createQuery("update Crop set status = :me where id = :id");
				  q1.setParameter("me","sold");
				  q1.setParameter("id",id2);
				  int re1=q1.executeUpdate();
				  commit();
				  if(result>0&&re1>0)
					  return true;
				  else
					  return false;
			 }
			  else
			  {
				  commit();
				  if(result>0)
					  return true;
				  else
					  return false;
			  }
		
		}catch(HibernateException e)
		{
			System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrr " + e);
			rollback();
			return false;
		}finally
		{
			close();
		}
		
	}
	
}
