package com.project;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/WIbmwWiNOGWzxn4fBc7xLC7Q8C1muUuu")
public class ApprovalController implements WebMvcConfigurer {

    public String makeJson(String id) {
        String json = "{\"apiKey\":\"8ny3Ea8N2w4PCm5E\"," +
                "\"jobid\":" +
                "\"" + id + "\"}";
        return json;

    }

    public boolean isJob(String id) {
        API api = new API();
        String url = "https://hacker30.pythonanywhere.com/todo/api/v1.0/tasks/check/for/job";
        String results = api.postData(url, makeJson(id));
        System.out.println(results);
        if (results.equals("true")) {
            return true;
        }
        return false;
    }

    // /WIbmwWiNOGWzxn4fBc7xLC7Q8C1muUuu/job?id=#
    @RequestMapping(value = "/job", method = RequestMethod.GET)
    public ModelAndView getJobApproval(@RequestParam(value = "id", required = false, defaultValue = "0") String id) {
        if (isJob(id)) {
            System.out.println(id);
            return new ModelAndView("redirect:/confirmation");
        } else {
            return new ModelAndView("redirect:/");
        }
        // return "";
    }
}