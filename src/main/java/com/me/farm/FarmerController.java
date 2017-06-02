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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.Dao.FarmerDao;
import com.me.Dao.RegistrationDao;
import com.me.Dao.RetailerDao;
import com.me.Dao.SupplierDao;
import com.me.business.Business;
import com.me.business.ImageValidator;
import com.me.pojo.Bids;
import com.me.pojo.Cart;
import com.me.pojo.CartList;
import com.me.pojo.Crop;
import com.me.pojo.CropCategory;
import com.me.pojo.Farmer;
import com.me.pojo.FarmerOrder;
import com.me.pojo.OrderItem;
import com.me.pojo.Person;
import com.me.pojo.Product;
import com.me.pojo.ProductCategory;

@Controller
@RequestMapping(value="/farmer")
public class FarmerController {
	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private FarmerDao farmerDao;
	@Autowired
	private RetailerDao retailerDao;
	@Autowired
	private SupplierDao supplierDao;
	
	
	@RequestMapping(value="/viewAllProducts.htm")
	public String displayProducts(HttpServletRequest res)
	{
		HttpSession session=res.getSession();
		if(null==session.getAttribute("CartList"))
		{
			CartList cartList=new CartList();
			session.setAttribute("CartList",cartList);
		}
		return "viewProducts";
	}
	
	@RequestMapping(value="/viewOrders.htm")
	public String viewFarmerOrders()
	{
		return "viewFarmerOrders";
	}
	@RequestMapping(value="/addCropPage.htm")
	public String addCropPage(Model model)
	{
		List<CropCategory> cropList=farmerDao.getCropCategories();
		model.addAttribute("ccList",cropList);
		return "addCrop";
	}
	
	@RequestMapping(value="/viewCrops.htm")
	public String viewCropPage()
	{
		return "viewCrops";
	}
	
	@RequestMapping(value="/getAllProducts/{pageNo}")
	public void getAllProducts(@PathVariable String pageNo,HttpServletResponse res)
	{
		long totalProducts=farmerDao.getTotalCount();
		List<Product> proList=farmerDao.getAllProducts(Integer.valueOf(pageNo));
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("tp",totalProducts);
		obj.put("products", proList);
		out.println(obj);	
		
	}
	
	@RequestMapping(value="addCrop.htm",method=RequestMethod.POST)
	public String addCrop(@Valid @ModelAttribute("crop") Crop crop,BindingResult result,HttpServletRequest req,Model model)
	{
		List<CropCategory> cropList=farmerDao.getCropCategories();
		model.addAttribute("ccList",cropList);
		ImageValidator im=new ImageValidator();
		String photoError=im.validateImage(crop.getCropPhoto());
		if(result.hasErrors())
		{
			model.addAttribute("pError",photoError);
			return "addCrop";
		}
		if(photoError!="")
		{
			model.addAttribute("pError",photoError);
			return "addCrop"; 
		}
		HttpSession session=req.getSession();
		Person person=(Person)session.getAttribute("Person");
		Farmer farmer=farmerDao.getFarmer(person.getPersonId());
		CropCategory c=registrationDao.getcc(crop.getCropCategory().getName());
		crop.setFarmer(farmer);
		crop.setCropCategory(c);
		crop.setStatus("active");
		String path=im.saveImage(crop.getCropPhoto());
		crop.setCropPhotoName(path);
		boolean suc=farmerDao.addCrop(crop);
		if(suc)
			model.addAttribute("message","Crop Addedd Successfully");
		else
			model.addAttribute("errorMessage","Sorry Unable to Add.Try After Sometime");
		
		return "addCrop";
	}
	
	@RequestMapping(value="/getFarmerCrops/{pn}")
	public void getCrops(@PathVariable String pn,HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session=req.getSession();
		Person person=(Person)session.getAttribute("Person");
		Farmer farmer=farmerDao.getFarmer(person.getPersonId());
		long tp=farmerDao.getTotalCropsCount(farmer.getFarmerId());
		List<Crop> cropList=farmerDao.getCrops(farmer.getFarmerId(),Integer.valueOf(pn));
		
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("tp",tp);
		obj.put("crops", cropList);
		out.println(obj);
	}
	
	
	
	
	
	@RequestMapping(value="myCart.htm")
	public String displayCart()
	{
		return "farmerCart";
	}
	
	@RequestMapping(value="addToCart.htm",method=RequestMethod.POST)
	public void addToCart(@RequestParam("id")String id,@RequestParam("selQuantity")String q,@RequestParam("cartPrice")String cp,HttpServletResponse res,HttpServletRequest request)
	{
		boolean suc=false;
		boolean cartPresent=false;
		HttpSession session=request.getSession();
		CartList cartList=(CartList)session.getAttribute("CartList");
		List<Cart> cList=cartList.getCartList();
		for(Cart cart:cList)
		{
			if(cart.getProduct().getProductId()==Integer.valueOf(id))
			{
				cart.setSelectedQuantity(Integer.valueOf(q));
				cart.setTotalPrice(Integer.valueOf(cp));
				cartPresent=true;
				break;
			}
		}
		if(!cartPresent)
		{
		Cart cart=new Cart();
		Product product=supplierDao.getProductToAddToCart(Integer.valueOf(id));
		cart.setProduct(product);
		cart.setSelectedQuantity(Integer.valueOf(q));
		cart.setTotalPrice(Integer.valueOf(cp));
		suc=cList.add(cart);
		}
		session.setAttribute("CartList",cartList);
		session.setAttribute("cartSize",cList.size());
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		if(suc)
		obj.put("mes","You Just Added One Item to cart");
		else if(cartPresent)
		obj.put("mes","Your Cart Updated Successfully");
		else
		obj.put("mes","Sorry Unable to add Item to Cart");	
		obj.put("tp",cList.size());
		out.println(obj);
	}
	
	@RequestMapping(value="getCartSize.htm")
	public void getCartSize(HttpServletResponse res,HttpServletRequest req)
	{
		HttpSession session=req.getSession();
		CartList cl=(CartList)session.getAttribute("CartList");
		List<Cart> cList=cl.getCartList();
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("tp",cList.size());
		out.println(obj);	
	}
	
	@RequestMapping(value="getCarts.htm")
	public void getCartList(HttpServletResponse res,HttpServletRequest req)
	{
		HttpSession session=req.getSession();
		CartList cl=(CartList)session.getAttribute("CartList");
		List<Cart> cList=cl.getCartList();
		Business b=new Business();
		long tp=b.calculateCartTotalPrice(cl);
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("carts",cList);
		obj.put("tp",tp);
		out.println(obj);	
	}
	
	@RequestMapping(value="/updateCart.htm",method=RequestMethod.POST)
	public void updateCartItem(@RequestParam("id")String id,@RequestParam("selQuantity")String q,@RequestParam("cartPrice")String cp,HttpServletResponse res,HttpServletRequest req)
	{
		HttpSession session=req.getSession();
		CartList cl=(CartList)session.getAttribute("CartList");
		Business business=new Business();
		cl=business.updateCartItem(Integer.valueOf(id),Integer.valueOf(q),Integer.valueOf(cp),cl);
		List<Cart> cList=cl.getCartList();
		long tp=business.calculateCartTotalPrice(cl);
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("mes","Cart updated Successfully");
		obj.put("carts",cList);
		obj.put("tp",tp);
		out.println(obj);
	}
	
	@RequestMapping(value="/deleteCart/{id}")
	public void deleteCartItem(@PathVariable String id,HttpServletResponse res,HttpServletRequest req)
	{
		HttpSession session=req.getSession();
		CartList cl=(CartList)session.getAttribute("CartList");
		Business business=new Business();
		cl=business.deleteCartItem(Integer.valueOf(id),cl);
		long tc=business.calculateCartTotalPrice(cl);
		List<Cart> cList=cl.getCartList();
		System.out.println("Sizeeeeee..........." + cList.size());
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("carts",cList);
		obj.put("tp",cList.size());
		obj.put("tc",tc);
		out.println(obj);
	}
	
	
	@RequestMapping(value="placeOrder.htm",method=RequestMethod.POST)
	public String placeOrder(HttpServletRequest request,Model model)
	{
		HttpSession session=request.getSession();
		Person person=(Person)session.getAttribute("Person");
		if(null==session.getAttribute("CartList"))
		{
			return "redirect:viewAllProducts.htm";
			
		}
		CartList cl=(CartList)session.getAttribute("CartList");
		List<Cart> cList=cl.getCartList();
		Farmer farmer=farmerDao.getFarmer(person.getPersonId());
		Business business=new Business();
		FarmerOrder order=business.generateOrder(farmer, cl);
		boolean suc=farmerDao.saveOrder(order);
		for(Cart cart:cList)
		{
			Product p=cart.getProduct();
		    int qu=p.getAvailableQuantity();
		    int uq=qu-cart.getSelectedQuantity();
			supplierDao.updatePrduct(cart.getProduct().getProductId(),uq);
		}
		if(suc)
		{
		model.addAttribute("mes","Order Placed Successfully");
		}
		session.removeAttribute("CartList");
		return "confirmOrder";
	}
	
	
	@RequestMapping(value="/getBidsForCrop/{id}")
	public void viewBidsofFarmer(@PathVariable String id,HttpServletResponse res,HttpServletRequest req)
	{	
		HttpSession session=req.getSession();
		Person person=(Person)session.getAttribute("Person");
		Farmer farmer=farmerDao.getFarmer(person.getPersonId());
		List<Bids> bid=farmerDao.getBidsForCrop(Integer.valueOf(id));	
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("bids",bid);
		System.out.println(obj);
		out.println(obj);
	}
	
	@RequestMapping(value="/getFarmerOrders/{pn}")
	public void getFarmerOrders(@PathVariable String pn,HttpServletRequest req,HttpServletResponse res)
	{
		
		HttpSession session=req.getSession();
		Person person=(Person)session.getAttribute("Person");
		Farmer farmer=farmerDao.getFarmer(person.getPersonId());
		List<OrderItem> oList=farmerDao.getOrderOfFarmers(farmer.getFarmerId(),Integer.valueOf(pn));
		long to=farmerDao.getFarmerOrderCount(farmer.getFarmerId());
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("orders",oList);
		obj.put("count",to);	
		out.println(obj);
		
	}
	
	@RequestMapping(value="/getProductsByFilter/{name}/{p}/{p1}")
	public void getProductsByFiler(@PathVariable String name,@PathVariable String p,@PathVariable String p1,HttpServletRequest req,HttpServletResponse res)
	{
		
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Product> pList=supplierDao.getProductsByFilter(name,Integer.valueOf(p),Integer.valueOf(p1));
		long tc=supplierDao.getProductsByFilterCount(name,Integer.valueOf(p),Integer.valueOf(p1));
		JSONObject obj = new JSONObject();
		obj.put("products",pList);
		obj.put("tp",tc);
		out.println(obj);
	}
	
	
	@RequestMapping(value="/updateBidStatus/{mes}/{id}")
	public void updateBidStatus(@PathVariable String mes,@PathVariable String id,HttpServletResponse res)
	{
		
		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean suc=farmerDao.updateBidStatus(mes,Integer.valueOf(id));
		JSONObject obj = new JSONObject();
		if(suc)
			obj.put("suc",true);
		else
			obj.put("suc",false);
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
	
	
	
	
	
	

