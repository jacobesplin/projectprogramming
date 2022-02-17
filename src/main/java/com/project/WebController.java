package com.project;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

@Controller
public class WebController implements WebMvcConfigurer {

	private String url = "https://jacobsmuzik.ddns.net";
	// private String url ="https://192.168.1.101";

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}

	@RequestMapping("/charge")
	public String charge(Model model, HttpServletRequest request) {
		// TrackVisitors.trackMe(request.getRemoteAddr(), "charge");
		model.addAttribute("amount", 50);
		model.addAttribute("item", "T-Shirt");
		return "charge";
	}

	@RequestMapping("/checkout")
	public String checkout(Model model) {
		model.addAttribute("item", "T-Shirt");
		model.addAttribute("amount", 50 * 100); // in cents
		model.addAttribute("stripePublicKey",
				"pk_test_51JIIdSBSUlyikFovVon5LMB8heiHW4l6Xrz3IQrYykA1PFNlrjwiizrXdBGi9sas3kMkU0HsgABuM1BR0JqYeRJ300yUOos8Ps");
		model.addAttribute("currency", ChargeRequest.Currency.USD);
		return "checkout";
	}

	@GetMapping(value = "/login")
	String login(LoginForm loginForm) {
		return "login/login";
	}

	@PostMapping("/login")
	public String postLogin(@Valid LoginForm loginForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "login/login";
		}
		try {
			API api = new API();
			// System.out.println(loginForm.getEmail());
			String url = "https://appsolutions.pythonanywhere.com/api/v12/data/post/contact";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"body\":" + "\"" + loginForm.toString() +
					"\",\"subject\":\"Project Programming Contact Form\"}";
			// api.postData(url, call);
		} catch (Exception e) {
			System.out.println(e);
		}

		// System.out.println(contactForm.getEmail());
		return "redirect:/home";
	}

	@GetMapping("/contact")
	public String showForm(ContactForm contactForm) {
		return "contact";
	}

	@PostMapping("/contact")
	public String checkPersonInfo(@Valid ContactForm contactForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "contact";
		}
		try {
			API api = new API();
			String url = "https://appsolutions.pythonanywhere.com/api/v12/data/post/contact";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"body\":" + "\"" + contactForm.toString() +
					"\",\"subject\":\"Project Programming Contact Form\"}";
			api.postData(url, call);
		} catch (Exception e) {
			System.out.println(e);
		}

		// System.out.println(contactForm.getEmail());
		return "redirect:/confirmation";
	}

	@GetMapping("/appointment")
	public String showAppointmentForm(AppointmentForm appointmentForm, Model model) {
		String[] times = { "8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM",
				"4:00 PM", "5:00 PM" };
		model.addAttribute("timeFrame", times);
		return "appointment";
	}

	@PostMapping("/appointment")
	public String checkAppointment(@Valid AppointmentForm appointmentForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "appointment";
		}
		try {
			API api = new API();
			String url = "https://appsolutions.pythonanywhere.com/api/v12/data/post/contact";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"body\":" + "\"" + appointmentForm.toString() +
					"\",\"subject\":\"Project Programming Appointment Form\"}";
			api.postData(url, call);
		} catch (Exception e) {
			// System.out.println(e);
		}

		return "redirect:/confirmation";
	}

	@GetMapping("/approve")
	public String showApproveForm(ApproveForm approveForm) {
		return "approval";
	}

	@PostMapping("/approve")
	public String checkApprove(@Valid ApproveForm approveForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "approval";
		}
		try {
			API api = new API();
			String url = "https://appsolutions.pythonanywhere.com/api/v12/data/post/contact";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"code\":" + "\"" + approveForm.getApprovalCode() +
					"\",\"body\":" + "\"business:" + approveForm.getBusiness() + "<br>Name:" + approveForm.getName()
					+ "<br>code:" + approveForm.getApprovalCode()
					+ "\",\"subject\":\"Project Programming Contact Form\"}";
			api.postData(url, call);
		} catch (Exception e) {
			System.out.println(e);
		}
		return "redirect:/confirmation";
	}

	@GetMapping("/search")
	public String search(@RequestParam String q) {
		// System.out.println("value of q " + q);
		return Search.search(q);
	}

	@GetMapping("/html")
	String html(Map<String, Object> model, @RequestParam String q) {
		API api = new API();
		model.put("body", api.pullData("https://appsolutions.pythonanywhere.com/api/v12/data/html/" + q));
		return "knowledge";
	}

	@RequestMapping(value = "/api/v12/storeinfo", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String storeInfoApi(HttpServletRequest request) {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		String response = "";
		try {
			BufferedReader reader = request.getReader();
			StringBuilder buffer = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			String data = buffer.toString();
			System.out.println(data);
			API api = new API();
			response = api.postData("https://petsmartstorefinder.pythonanywhere.com/api/v12/storeinfo", data);
		} catch (IOException e) {

		}
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		// System.out.println(ipAddress);
		return response;
	}

	@RequestMapping(value = "/api/v12/gameProfile", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	@ResponseBody
	public String gameProfile(HttpServletRequest request) {

		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		String response = "";
		try {
			BufferedReader reader = request.getReader();
			StringBuilder buffer = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			String data = buffer.toString();
			APISelfSigned api = new APISelfSigned();
			response = api.sendDataToServer(url + "/ReactSpringBoot/api/v12/data/get/gameProfile", data);
			// response =
			// api.sendDataToServer("https://192.168.1.101/ReactSpringBoot/api/v12/data/get/gameProfile",
			// data);
		} catch (IOException e) {

		}
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		// System.out.println(ipAddress);
		return response;
	}

	@RequestMapping(value = "/api/v12/data/get/minecraft", method = RequestMethod.POST, produces = "application/text", consumes = "application/json")
	@ResponseBody
	public String getMineCraftStatus(HttpServletRequest request) {

		String response = "";
		String data = "";
		try {
			BufferedReader reader = request.getReader();
			StringBuilder buffer = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			data = buffer.toString();
			APISelfSigned api = new APISelfSigned();
			response = api.sendDataToServer(url + "/ReactSpringBoot/api/v12/data/get/minecraft", data);

		} catch (IOException e) {

		}

		// System.out.println("this is the data " + data);

		return response;
	}
}