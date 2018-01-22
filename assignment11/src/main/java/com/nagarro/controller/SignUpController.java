package com.nagarro.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.context.support.UiApplicationContextUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.model.User;
import com.nagarro.model.UserRegisteration;
import com.nagarro.service.IUserService;

@Controller
public class SignUpController {
	@Autowired
	IUserService userService;
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String displaySignUpPage(Model model) {
		String resultUrl = "signup";
		model.addAttribute("userRegistration", new UserRegisteration());
		return resultUrl;

	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("userRegistration") @Valid UserRegisteration userRegistration,
			BindingResult result, HttpSession session, Model model) {
		String resultUrl = "redirect:login.html";
		String username_notunique="username should be unique";
		if (result.hasErrors() || !userRegistration.getUser().getPassword().equals(userRegistration.getcPassword())
				|| userRegistration.getUser().getPassword().length() < 6) {
			resultUrl = "signup";
		}
		else{
			userService.addUser(userRegistration.getUser());
		}
		return resultUrl;

	}
}
