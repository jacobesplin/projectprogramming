package com.project;

class ProjectList{
    private String name;
    private String imgSrc;

    // Constructor or setter
    
    ProjectList(String name, String imgSrc) {
        this.name = name;
        this.imgSrc = imgSrc;
    }
    /*
    public void setName(String name){
        this.name = name;
    }
    public void setImgSrc(String imgSrc){
        this.imgSrc = imgSrc;
    }
    */
    // getters

    public String getName() {
        return this.name;
    }

    public String getImgSrc() {
        return this.imgSrc;
    }
}