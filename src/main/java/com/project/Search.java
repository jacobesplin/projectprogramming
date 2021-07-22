package com.project;

public class Search{
    public static String search(String q){
        if(q.contains("home"))return "redirect:/";
        if(q.contains("contact"))return "redirect:/contact";
        if(q.contains("appointment"))return "redirect:/appointment";
        if(q.contains("about"))return "redirect:/about";
        if(q.contains("minecraft") || q.contains("minecraftserver"))return "redirect:/minecraftserver";
        if(q.contains("request") || q.contains("approve"))return "redirect:/approve";

        return "searchFailed";
    }
}