package com.me.farm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.Dao.FarmerDao;
import com.me.Dao.RegistrationDao;
import com.me.Dao.RetailerDao;
import com.me.Dao.SupplierDao;
import com.me.pojo.CropCategory;
import com.me.pojo.Person;
import com.me.pojo.ProductCategory;

@Controller
public class AdminController {
	
	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private FarmerDao farmerDao;
	@Autowired
	private RetailerDao retailerDao;
	@Autowired
	private SupplierDao supplierDao;
    @Autowired
    private JavaMailSender mailSender;
    
    @RequestMapping(value="/admin/adminHome.htm" ,method= RequestMethod.GET)
    public String getAdminPage()
    {
    	return "admin";
    }
    
    
	
	@RequestMapping(value ="/getUsers.htm", method = RequestMethod.POST)
	@ResponseBody
	public void viewUers(@RequestParam String pageNo,HttpServletResponse res) {
		int totalCount=registrationDao.getToralUersCount();
		System.out.println(" PageNo " + pageNo);
		List<Person> personList = registrationDao.getUsers(Integer.valueOf(pageNo));
		System.out.println(" PSize " + personList.size());
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("per", personList);
		obj.put("tc",totalCount);
		System.out.println(obj);
		out.println(obj);

	}
	
	@RequestMapping(value = "/activate.htm", method = RequestMethod.POST)
	@ResponseBody
	public void activateAccount(@RequestParam int personId, HttpServletResponse res) {
		boolean act = registrationDao.activateAccount(personId);
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("pc", act);
		out.println(obj);
	}
	
	@RequestMapping(value = "/addCategory.htm", method = RequestMethod.POST)
	@ResponseBody
	public void saveCategory(@RequestParam String categoryName, HttpServletResponse res) {
		ProductCategory c = new ProductCategory();
		c.setName(categoryName);
		boolean suc = registrationDao.addCategory(c);
		JSONObject obj = new JSONObject();
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (suc) {
			obj.put("suc", "true");
			out.println(obj);
		} else {
			obj.put("suc", "false");
			out.println(obj);
		}
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

	@RequestMapping(value = "/addCropCategory.htm", method = RequestMethod.POST)
	@ResponseBody
	public void saveCropCategory(@RequestParam String categoryName, HttpServletResponse res) {
		CropCategory cc=new CropCategory();
		cc.setName(categoryName);
		boolean suc = farmerDao.addCropCategory(cc);
		JSONObject obj = new JSONObject();
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (suc) {
			obj.put("suc", "true");
			out.println(obj);
		} else {
			obj.put("suc", "false");
			out.println(obj);
		}
	}
		
	@RequestMapping(value="/getCropCategories.htm",method=RequestMethod.GET)
	@ResponseBody
	public void getCropCategories(HttpServletResponse response)
	{
		
		
		List<CropCategory> cc=farmerDao.getCropCategories();
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("cats",cc);
		out.println(obj);

	}
	
	@RequestMapping(value="/logout.htm")
	public String logOut(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		session.invalidate();
		return "redirect:login.htm";
	}
    
}
