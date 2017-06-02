package com.me.Dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.me.pojo.Bids;
import com.me.pojo.Crop;
import com.me.pojo.Retailer;

public class RetailerDao extends Dao {

	
	public RetailerDao()
	{
		
	}
	
	public boolean addRetailer(Retailer retailer)
	{
		try
		{
			begin();
			getSession().save(retailer);
		    commit();
		}
		catch(HibernateException e)
		{
			rollback();
			return false;
		}
		finally
		{
			close();
		}
		return true;
	}
	
	public Retailer getRetailer(int personId)
	{
		Retailer retailer=null;
		try
		{
			begin();
			Query q=getSession().createQuery("from Retailer where person.personId = :id ");
		    q.setInteger("id",personId);
			retailer=(Retailer)q.uniqueResult();
			commit();
			
		}
		catch(HibernateException e)
		{
			rollback();
		}finally
		{
			close();
		}
		return retailer;
	}
	
	
	public List<Crop> getAllCrops(int pn)
	{
		List<Crop> cropList=null;
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
			Criteria c=getSession().createCriteria(Crop.class)
					   .add(Restrictions.eq("status","active"));
			c.setFirstResult(pn);
			c.setMaxResults(8);          
			cropList=c.list();
			System.out.println(" ffffffffff " +cropList.size());
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
	
	public long getCropsTotalCount()
	{
		long tc=0;
		try
		{
			begin();
			Query q=getSession().createQuery("select count(id) from Crop");
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
	
	public boolean placeBid(Bids bid)
	{
		try
		{
			begin();
			getSession().saveOrUpdate(bid);
			commit();
			return true;		
		}
		catch(HibernateException e)
		{
			rollback();
			return false;
		}finally
		{
			close();
		}
		
	}
	
	public Bids checkBid(int cropId,int retailerId)
	{
		Bids bid=null;
		try
		{
			begin();
			String hw="from Bids where crop = :cid and status = :mes and retailer = :rId";
			Query q=getSession().createQuery(hw);
			q.setInteger("cid",cropId);
			q.setInteger("rId",retailerId);
			q.setString("mes","Pending");
			bid=(Bids)q.uniqueResult();
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
	
	public List<Bids> getAllBids(int rid,int pn)
	{
		List<Bids> bidList=null;
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
			Query q=getSession().createQuery("from Bids where retailer = :rid");
			q.setInteger("rid",rid);
			q.setFirstResult(pn);
			q.setMaxResults(8);
			bidList=q.list();
			commit();
			return bidList;
			
		}catch(HibernateException e)
		{
		rollback();
		return bidList;
		}finally
		{
			close();
		}
	}
	
	public long getBidsCount(int rid)
	{
		long count=0;
		try
		{
			begin();
			Query q=getSession().createQuery("select count(id) from Bids where retailer = :rid");
			q.setInteger("rid",rid);
			count=(Long)q.uniqueResult();
			commit();
			return count;
		}catch(HibernateException e)
		{
		     rollback();
		     return count;	
		}finally {
			close();
		}
	}
}
