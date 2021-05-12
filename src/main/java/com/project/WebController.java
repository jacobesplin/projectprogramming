package com.project;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


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
			String url = "https://hacker30.pythonanywhere.com/todo/api/v1.0/tasks/test";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"email\":"+"\""+contactForm.getEmail()+"\"}";
			System.out.println(api.postData(url,call));
		}catch(Exception e){
			System.out.println(e);
		}

        System.out.println(contactForm.getEmail());
		return "redirect:/confirmation";
	}
    @GetMapping("/appointment")
	public String showAppointmentForm(ContactForm contactForm) {
		return "appointment";
	}
    @PostMapping("/appointment")
	public String checkAppointment(@Valid ContactForm contactForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "appointment";
		}

		return "redirect:/results";
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
			String url = "https://hacker30.pythonanywhere.com/todo/api/v1.0/tasks/test";
			String call = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\",\"code\":"+"\""+approveForm.getApprovalCode()+
				"\",\"business\":"+"\""+approveForm.getBusiness()+
				"\",\"name\":"+"\""+approveForm.getName()+"\"}";
			System.out.println(api.postData(url,call));
		}catch(Exception e){
			System.out.println(e);
		}
		return "redirect:/confirmation";
	}
}