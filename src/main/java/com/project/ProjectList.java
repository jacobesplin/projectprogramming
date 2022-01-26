package com.project;

public class ProjectList {
    private String name;
    private String imgSrc;
    private String id;

    // Constructor or setter

    ProjectList(String name, String imgSrc, String id) {
        this.name = name;
        this.imgSrc = imgSrc;
        this.id = id;
    }
    /*
     * public void setName(String name){
     * this.name = name;
     * }
     * public void setImgSrc(String imgSrc){
     * this.imgSrc = imgSrc;
     * }
     */
    // getters

    public String getName() {
        return this.name;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }

    public String getId() {
        return this.id;
    }
}