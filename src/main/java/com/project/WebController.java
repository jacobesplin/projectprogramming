package com.project;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.ui.Model;
import java.util.Map;


@Controller
public class WebController implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
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
		try{
			API api = new API();
			String url = "https://appsolutions.pythonanywhere.com/api/v12/data/post/contact";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"body\":"+"\""+contactForm.toString()+
			"\",\"subject\":\"Project Programming Contact Form\"}";
			api.postData(url,call);
		}catch(Exception e){
			System.out.println(e);
		}

        //System.out.println(contactForm.getEmail());
		return "redirect:/confirmation";
	}
    @GetMapping("/appointment")
	public String showAppointmentForm(AppointmentForm appointmentForm,Model model) {
		String[] times = {"8:00 AM","9:00 AM","10:00 AM","11:00 AM","12:00 PM","1:00 PM","2:00 PM","3:00 PM","4:00 PM","5:00 PM"};
		model.addAttribute("timeFrame",times);
		return "appointment";
	}
    @PostMapping("/appointment")
	public String checkAppointment(@Valid AppointmentForm appointmentForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "appointment";
		}
		try{
			API api = new API();
			String url = "https://appsolutions.pythonanywhere.com/api/v12/data/post/contact";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"body\":"+"\""+appointmentForm.toString()+
			"\",\"subject\":\"Project Programming Appointment Form\"}";
			api.postData(url,call);
		}catch(Exception e){
			//System.out.println(e);
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
		try{
			API api = new API();
			String url = "https://appsolutions.pythonanywhere.com/api/v12/data/post/contact";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"code\":"+"\""+approveForm.getApprovalCode()+
				"\",\"body\":"+"\"business:"+approveForm.getBusiness()+"<br>Name:"+approveForm.getName()+"<br>code:"+approveForm.getApprovalCode()+"\",\"subject\":\"Project Programming Contact Form\"}";
			api.postData(url,call);
		}catch(Exception e){
			System.out.println(e);
		}
		return "redirect:/confirmation";
	}
	@GetMapping("/search")
	public String search(@RequestParam String q){
		return Search.search(q);
	}
	@GetMapping("/html")
   	String html (Map<String, Object> model,@RequestParam String q) {
		API api = new API();
		model.put("body", api.pullData("https://appsolutions.pythonanywhere.com/api/v12/data/html/"+q));
		return "knowledge";
	}
}