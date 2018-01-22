package com.nagarro.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.model.User;
import com.nagarro.service.IUserService;

@Controller
public class LoginController {

	@Autowired
	IUserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLoginPage(Model model, HttpSession session) {
		String resultUrl = "login";
		User user = (User) session.getAttribute("user");
		if (user != null && user.getUsername() != null) {
			resultUrl = "redirect:flightsearch.html";
		} else {
			model.addAttribute("user", new User());
		}
		return resultUrl;

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processUser(@ModelAttribute("user") @Valid User user, BindingResult result, HttpSession session,
			Model model) {
		String resultUrl = "redirect:flightsearch.html";
		if (result.hasErrors()) {
			resultUrl = "login";
		} else if (!userService.verifyUser(user.getUsername(), user.getPassword())) {
			User user1 = (User) session.getAttribute("user");
			session.setAttribute("user", null);
			boolean status = true;
			model.addAttribute("errormsg", status);
			resultUrl = "login";

		} else {
			session.setAttribute("user", user);
		}
		return resultUrl;

	}
}
