package com.nagarro.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nagarro.Constants.Locations;
import com.nagarro.model.Flight;
import com.nagarro.model.FlightDetails;
import com.nagarro.model.User;
import com.nagarro.service.IFlightService;

@Controller
public class FlightController {
	@Autowired
	private IFlightService flightService;

	@RequestMapping(value = "/flightsearch", method = RequestMethod.GET)
	public String displaySearchFlightPage(ModelMap model, HttpSession session) {
		String resultUrl = "flightsearch";
		User user = (User) session.getAttribute("user");
		if (user == null || user.getUsername() == null) {
			resultUrl = "redirect:login.html";
		} else {
			model.addAttribute("flightdetails", new FlightDetails());
		}

		return resultUrl;
	}

	@RequestMapping(value = "/flightsearch", method = RequestMethod.POST)
	public String processFlightData(@ModelAttribute("flightdetails") @Valid FlightDetails flightdetails,
			BindingResult result, Model model) {
		String resultUrl = "forward:displayflights.html";
		if (result.hasErrors()) {
			resultUrl = "flightsearch";
		} else {
			List<Flight> list = flightService.getDataFomDb(flightdetails.getFlight().getDepLoc(),
					flightdetails.getFlight().getArrLoc(), flightdetails.getFlight().getValidTill(),
					flightdetails.getFlightClass().getFlightClass(), flightdetails.getOutputPreference());
			model.addAttribute("flights", list);
		}
		return resultUrl;
	}

	@ModelAttribute("locations")
	public Map<String, String> grtLocations() {
		return Locations.getMap();
	}

	@RequestMapping(value = "/displayflights")
	public String displayFlights(Model model, HttpSession session) {
		String resultUrl = "displayflights";
		User user = (User) session.getAttribute("user");
		if (user == null || user.getUsername() == null) {
			System.out.println("ssssssssssssssssss-----" + user);
			resultUrl = "redirect:login.html";
		}
		return resultUrl;
	}
}
