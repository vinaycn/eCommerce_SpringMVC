package com.me.farm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.Dao.FarmerDao;
import com.me.Dao.RegistrationDao;
import com.me.Dao.RetailerDao;
import com.me.Dao.SupplierDao;
import com.me.pojo.Bids;
import com.me.pojo.Crop;
import com.me.pojo.Person;
import com.me.pojo.ProductCategory;
import com.me.pojo.Retailer;

@Controller
@RequestMapping(value="/retailer")
public class RetailerController {

	
	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private FarmerDao farmerDao;
	@Autowired
	private RetailerDao retailerDao;
	@Autowired
	private SupplierDao supplierDao;
	
	
	@RequestMapping(value="/viewAllCrops.htm")
	public String viewAllCrops()
	{
		return "viewAllCrops";
	}
	
	@RequestMapping(value="/viewMyBids.htm")
	public String getAllBids()
	{
		return "viewRetailerBids";
	}
	
	@RequestMapping(value="/getAllCrops/{c}")
	public void getAllCrops(@PathVariable String c,HttpServletRequest req,HttpServletResponse res)
	{
		System.out.println("sssssssssssssss");
		long totalCrops=retailerDao.getCropsTotalCount();
		List<Crop> cropList=retailerDao.getAllCrops(Integer.valueOf(c));
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("tp",totalCrops);
		obj.put("crops",cropList);
		out.println(obj);	
	}
	
	
	@RequestMapping(value="/placeBid/{id}/{price}")
	public void placeBid(@PathVariable String id,@PathVariable String price,HttpServletRequest req,HttpServletResponse res)
	{
		boolean suc=false;
		HttpSession session=req.getSession();
		Person person=(Person)session.getAttribute("Person");
		Retailer retailer=retailerDao.getRetailer(person.getPersonId());
		Crop crop=farmerDao.getCrop(Integer.valueOf(id));
		Date date=new Date();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(date);
		Bids bidu=retailerDao.checkBid(Integer.valueOf(id),retailer.getId());
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		if(null==bidu)
		{
		Bids bid=new Bids();
		bid.setCreatedOn(currentTime);
		bid.setPrice(Long.parseLong(price));
		bid.setCrop(crop);
		bid.setRetailer(retailer);
		bid.setStatus("Pending");
		suc=retailerDao.placeBid(bid);
		obj.put("sucm","Bid Placed Successfully");
		}
		else
		{
			bidu.setPrice(Long.parseLong(price));
			suc=retailerDao.placeBid(bidu);
			obj.put("sucm","Bid Updated Successfully");
		}
		
		
		if(suc)
		obj.put("mes",true);
		else
	    obj.put("mes",false);
		out.println(obj);
	}
	
	@RequestMapping(value="getBidsRetailer/{pn}/")
	public void getBidsforRetailer(@PathVariable String pn,HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session=req.getSession();
		Person person=(Person)session.getAttribute("Person");
		Retailer retailer=retailerDao.getRetailer(person.getPersonId());
		List<Bids> bidList=retailerDao.getAllBids(retailer.getId(),Integer.valueOf(pn));
	    long tc=retailerDao.getBidsCount(retailer.getId());
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("bids",bidList);
		obj.put("tb",tc);
		System.out.println(obj);
		out.println(obj);
	}
	
	@RequestMapping(value ="/getCategories.htm", method = RequestMethod.GET)
	@ResponseBody
	public void getCategory(HttpServletResponse res) {
		
		List<ProductCategory> pcList = registrationDao.getCategories();
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("pc", pcList);
		out.println(obj);
	}
	
	@RequestMapping(value="logout.htm")
	public String logOut(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:/login.htm";
	}
}
