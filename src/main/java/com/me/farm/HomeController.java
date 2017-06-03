package com.me.farm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.Dao.FarmerDao;
import com.me.Dao.RegistrationDao;
import com.me.Dao.RetailerDao;
import com.me.Dao.SupplierDao;
import com.me.pojo.CartList;
import com.me.pojo.CropCategory;
import com.me.pojo.EmailSender;
import com.me.pojo.Farmer;
import com.me.pojo.Person;
import com.me.pojo.ProductCategory;
import com.me.pojo.Retailer;
import com.me.pojo.Supplier;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
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
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String getHome(Locale locale, Model model) {
		return "home";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		model.addAttribute("error","Please login to access your page!");
		return "login";
	}

	@RequestMapping(value = "/signUp.htm", method = RequestMethod.GET)
	public String signUp(Locale locale, Model model) {
		return "signUp";
	}

	@RequestMapping(value = "/registration.htm", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("person") Person person, BindingResult result, Model model) {
		person.getUserAccount().setStatus("Disabled");
		
		EmailSender e = new EmailSender();
		boolean con = false;
		if (result.hasErrors()) {

			return "signUp";
		}
		if (person.getUserAccount().getRole().equalsIgnoreCase("Farmer")) {
			Farmer farmer = new Farmer();
			farmer.setPerson(person);
			con = registrationDao.addPerson(person);
			con = farmerDao.addFarmer(farmer);
			SimpleMailMessage mes = e.sendMail("vinayaed@gmail.com", person.getEmailId(),
					"Your Registration Successfull", "You Can login Once your Account is activated");
			try {
				mailSender.send(mes);
			} catch (Exception exp) {
				System.out.println("Carry On.....");
			}

		} else if (person.getUserAccount().getRole().equalsIgnoreCase("Supplier")) {
			Supplier supplier = new Supplier();
			supplier.setPerson(person);
			con = registrationDao.addPerson(person);
			con = supplierDao.addSupplier(supplier);
			
			SimpleMailMessage mes = e.sendMail("vinayaed@gmail.com", person.getEmailId(),
					"Your Registration Successfull", "You Can login Once your Account is activated");
			try {
				mailSender.send(mes);
			} catch (Exception exp) {
				System.out.println("Carry On.....");
			}
		} else if (person.getUserAccount().getRole().equalsIgnoreCase("Retailer")) {
			Retailer retailer = new Retailer();
			retailer.setPerson(person);
			con = registrationDao.addPerson(person);
			con = retailerDao.addRetailer(retailer);
			SimpleMailMessage mes = e.sendMail("vinayaed@gmail.com", person.getEmailId(),
					"Your Registration Successfull", "You Can login Once your Account is activated");
			try {
				mailSender.send(mes);
			} catch (Exception exp) {
				System.out.println("Carry On.....");
			}

		} else {
			person.getUserAccount().setStatus("active");
			con = registrationDao.addPerson(person);
		}
		if (con)
			model.addAttribute("success", "Registration Successfull.You can login Once your Account is Activated");
		else
			model.addAttribute("success", "Sorry.Please Register After Some Time");
		return "signUp";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/access.htm", method = RequestMethod.POST)
	public String verifyUser(@RequestParam("username") String userName, @RequestParam("password") String password,
			HttpServletRequest request, Model model) {
		Person person = registrationDao.findUser(userName, password);
		if (null == person) {
			model.addAttribute("error", "Check Your Username and Password");
			return "login";
		}
		HttpSession session = request.getSession();
		session.setAttribute("Person", person);
		session.setAttribute("name", person.getName());

		if (person.getUserAccount().getRole().equalsIgnoreCase("farmer")) {
			if (null == session.getAttribute("CartList")) {
				CartList cList = new CartList();
				session.setAttribute("CartList", cList);
			}
			return "redirect:farmerf/viewAllProducts.htm";
		} else if (person.getUserAccount().getRole().equalsIgnoreCase("admin")) {
			return "redirect:admina/adminHome.htm";
		} else if (person.getUserAccount().getRole().equalsIgnoreCase("Supplier")) {
			return "redirect:supplierr/addProductPage.htm";
		} else {
			return "redirect:retailerr/viewAllCrops.htm";
		}

	}

	@RequestMapping(value = "/checkUserName.htm", method = RequestMethod.POST)
	@ResponseBody
	public void checkUserName(@RequestParam String userName, HttpServletResponse res) {

		boolean cun = registrationDao.checkUsernameUnique(userName);

		PrintWriter out = null;
		try {
			out = res.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		obj.put("ue", cun);
		out.println(obj);
	}

}
