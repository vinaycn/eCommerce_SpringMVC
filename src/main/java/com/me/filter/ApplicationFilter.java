package com.me.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.pojo.Person;

public class ApplicationFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP																			// 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		String requestUrl = request.getRequestURI();

		

		if (requestUrl.contains("/admina/")) {
			System.out.println(requestUrl);
			if ((session == null || session.getAttribute("Person") == null)
					|| (!((Person) session.getAttribute("Person")).getUserAccount().getRole()
							.equalsIgnoreCase("Admin"))) {
				response.sendRedirect("/login.htm");
			} else {
				chain.doFilter(req, res); // Logged-in user found, so just
											// continue
				// request.
			}
		} else if (requestUrl.contains("/farmerf/")) {

			System.out.println(requestUrl);
			if ((session == null || session.getAttribute("Person") == null)
					|| (!((Person) session.getAttribute("Person")).getUserAccount().getRole()
							.equalsIgnoreCase("farmer"))) {
				response.sendRedirect("/login.htm");
			} else {
				chain.doFilter(req, res); // Logged-in user found, so just
				System.out.println("Its farrrrrrrrrrrrrr");								// continue
				// request.
			}
		} else if (requestUrl.contains("/supplierr/")) {
			System.out.println(requestUrl);
			if ((session == null || session.getAttribute("Person") == null)
					|| (!((Person) session.getAttribute("Person")).getUserAccount().getRole()
							.equalsIgnoreCase("supplier"))) {
				response.sendRedirect("/login.htm");
			} else {
				chain.doFilter(req, res); // Logged-in user found, so just
				System.out.println("Its supleeeeeeeeee");						// continue
				// request.
			}

		} else if (requestUrl.contains("/retailerr/")) {
			System.out.println(requestUrl);
			if ((session == null || session.getAttribute("Person") == null)
					|| (!((Person) session.getAttribute("Person")).getUserAccount().getRole()
							.equalsIgnoreCase("retailer"))) {
				response.sendRedirect("/login.htm");	
			} else {
				chain.doFilter(req, res); // Logged-in user found, so just
					System.out.println("Its Retailerrrrrrrrrrrrrrr");						// continue
				// request.
			}

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
