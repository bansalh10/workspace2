package com.nagarro.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {
	@RequestMapping(value = "/logout")
	public String displayFlights(Model model, HttpSession session) {
		String resultUrl = "redirect:login.html";
		session.setAttribute("user", null);
		return resultUrl;
	}
}
