package com.project;

public class Search{
    public static String search(String q){
        if(q.contains("home"))return "redirect:/index";
        if(q.contains("contact"))return "redirect:/contact";
        if(q.contains("appointment"))return "redirect:/appointment";
        if(q.contains("about"))return "redirect:/about";

        return "searchFailed";
    }
}