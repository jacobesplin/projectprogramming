package com.project;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class Projects {
    String url = "https://skygods.servegame.com:8443";
    API api = new API();

    private JSONArray getJsonArray(String value, String getObjectValue) {
        Object obj = JSONValue.parse(value);
        JSONObject jsonObject = (JSONObject) obj;
        try {
            return (JSONArray) jsonObject.get(getObjectValue);
        } catch (Exception e) {

        }
        return null;
    }

    private String getJsonObject(JSONObject jsonString, String object) {
        return (String) jsonString.get(object);
    }

    public ArrayList<ProjectList> getProjects(String projectName) {
        ArrayList<ProjectList> projectList = new ArrayList<>();
        JSONArray response = getJsonArray(
                api.pullData(url + "/api/v12/projectprogramming/projects"),
                "data");
        for (int i = 0; i < response.size(); i++) {
            projectList.add(new ProjectList(getJsonObject((JSONObject) response.get(i), "name"),
                    getJsonObject((JSONObject) response.get(i), "img"),
                    getJsonObject((JSONObject) response.get(i), "id")));
        }
        return projectList;
    }

    public String projectConents(String id) {
        API api = new API();
        String results = api.postData(url + "/api/v12/projectprogramming/projects/contents",
                "{\"project\":\"" + id + "\""+
                "{\"data\":\"getContents\""+
                "}");
        return results;
    }

}
