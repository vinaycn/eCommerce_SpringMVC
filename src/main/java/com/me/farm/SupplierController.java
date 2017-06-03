package com.me.farm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.Dao.FarmerDao;
import com.me.Dao.RegistrationDao;
import com.me.Dao.RetailerDao;
import com.me.Dao.SupplierDao;
import com.me.business.ImageValidator;
import com.me.pojo.CartList;
import com.me.pojo.OrderItem;
import com.me.pojo.Person;
import com.me.pojo.Product;
import com.me.pojo.ProductCategory;
import com.me.pojo.Supplier;

@Controller
@RequestMapping(value="/supplierr")
public class SupplierController {
	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private FarmerDao farmerDao;
	@Autowired
	private RetailerDao retailerDao;
	@Autowired
	private SupplierDao supplierDao;
	
	
	@RequestMapping(value = "/addProductPage.htm", method = RequestMethod.GET)
	public String displayaddProduct(Model model) {
		List<ProductCategory> catList=registrationDao.getProductCategories();
		model.addAttribute("categoryList",catList);
		return "addProduct";
	}

	@RequestMapping(value = "/viewProducts.htm", method = RequestMethod.GET)
	public String viewSupplierProducts(HttpServletRequest res,HttpServletResponse response) {
		
		return "viewSupplierProducts";
	}
	@RequestMapping(value = "/viewMyOrders.htm", method = RequestMethod.GET)
	public String viewSupplierOrders(HttpServletRequest res,HttpServletResponse response) {
		return "viewSupplierOrders";
	}
	
	@RequestMapping(value="addProduct.htm", method=RequestMethod.POST)
	public String addProductDetails(@Valid @ModelAttribute("product") Product product,BindingResult result,HttpServletRequest request,Model model)
	{
		List<ProductCategory> catList=registrationDao.getProductCategories();
		model.addAttribute("categoryList",catList);
		ImageValidator im=new ImageValidator();
		String photoError=im.validateImage(product.getProductImage());
		if(result.hasErrors())
		{
			model.addAttribute("pError",photoError);
			return "addProduct"; 
			
		}
		if(photoError!="")
		{
			model.addAttribute("pError",photoError);
			return "addProduct"; 
		}
		HttpSession ses=request.getSession();
		Person person=(Person)ses.getAttribute("Person");
		Supplier supplier=supplierDao.getSupplier(person.getPersonId());
		ProductCategory pc=registrationDao.getPc(product.getpCategory().getName());
		product.setSupplier(supplier);
		product.setCategory(pc);
		String path=im.saveImage(product.getProductImage());
		product.setGetPhotoName(path);
		boolean conf=supplierDao.addProduct(product);
		
	    if(conf)
	    	model.addAttribute("message","Product added Successfully!");
	    else
	    	model.addAttribute("message","Sorry.Unable to add Product");
		
		return "addProduct";
	}
	
	@RequestMapping(value="/getSupplierProducts/{pn}", method=RequestMethod.GET)
    @ResponseBody
	public void getProducts(@PathVariable String pn,HttpServletRequest res,HttpServletResponse response)
	{
		HttpSession session=res.getSession();
		Person person=(Person)session.getAttribute("Person");
		Supplier supplier=supplierDao.getSupplier(person.getPersonId());
		int totalProducts=supplierDao.getTotalProductsOfSupplier(supplier.getSupplierId());
		List<Product> proList=supplierDao.getProduct(supplier.getSupplierId(),Integer.valueOf(pn));
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("productList", proList);
		obj.put("tc",totalProducts);
		System.out.println(obj);
		out.println(obj);	
	}
	
	@RequestMapping(value="/updateProducts/{id}/{price}/{quantity}",method=RequestMethod.GET)
	@ResponseBody
	public void updateProductDetails(@PathVariable String id,@PathVariable String price,@PathVariable String quantity,
			                              HttpServletResponse res)
	{
		System.out.println(id + price + quantity);
		int success=supplierDao.updateProducts(Integer.valueOf(id),Integer.valueOf(price),Integer.valueOf(quantity));
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("message",success);
		out.println(obj);	
	}
	
	@RequestMapping(value="getOrders/{pn}")
	public void getSupplierOrders(@PathVariable String pn,HttpServletResponse res,HttpServletRequest req)
	{
		HttpSession session=req.getSession();
		Person person=(Person)session.getAttribute("Person");
		Supplier supplier=supplierDao.getSupplier(person.getPersonId());
		List<OrderItem> oList=supplierDao.getSuppliersOrderItem(supplier.getSupplierId(),Integer.valueOf(pn));
		long to=supplierDao.getTotalSupplierOrderCount(supplier.getSupplierId());
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("order",oList);
		obj.put("to",to);
		System.out.println(obj);
		out.println(obj);	
	}
	
	@RequestMapping(value="changeOrderStatus/{mes}/{id}")
	public void chageOrderStatus(@PathVariable String mes,@PathVariable String id,HttpServletResponse res)
	{
		String ns="";
		if(mes.equalsIgnoreCase("Processing"))
		{
			ns="Dispatached From WareHouse";
		}
		else
		{
			ns="Delivered";
		}
		
		boolean suc=supplierDao.changeOrderStatus(Integer.valueOf(id),ns);
		
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		if(suc)
		obj.put("mes",ns);
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
