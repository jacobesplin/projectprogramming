/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.project;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@SpringBootApplication
public class Main {

  @Value("${spring.datasource.url}")
  private String dbUrl;

  @Autowired
  private DataSource dataSource;

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index(HttpServletRequest request) {
    TrackVisitors.trackMe(request.getRemoteAddr());
    return "index";
  }

  @RequestMapping("/about")
  String about() {
    return "about";
  }

  /*
   * @RequestMapping("/team")
   * String team (Map<String, Object> model) {
   * model.put("msg","Welcome!");
   * return "team";
   * }
   */
  @RequestMapping("/confirmation")
  String confirmation() {
    return "confirmation";
  }

  // languages
  @RequestMapping("/java")
  String java(Map<String, Object> model) {
    ArrayList<ProjectList> project = new ArrayList<>();
    project.add(new ProjectList("Network Tool", "/pics/NetworkTool.jpg"));
    project.add(new ProjectList("Algorithms & Data Structurs", "/pics/AlgorithmsDataStructures.JPG"));
    model.put("Projects", project);
    return "languages";
  }

  @RequestMapping("/python")
  String python(Map<String, Object> model) {
    ArrayList<ProjectList> project = new ArrayList<>();
    project.add(new ProjectList("Python", "/pics/NetworkTool.jpg"));
    model.put("Projects", project);
    ;
    return "languages";
  }

  @RequestMapping("/javascript")
  String javascript(Map<String, Object> model) {
    String[] projects = new String[] {};

    model.put("Projects", projects);
    return "languages";
  }

  @RequestMapping("/cplusplus")
  String cplusplus(Map<String, Object> model) {
    String[] projects = new String[] {};

    model.put("Projects", projects);
    return "languages";
  }

  @RequestMapping("/csharp")
  String csharp(Map<String, Object> model) {
    String[] projects = new String[] {};

    model.put("Projects", projects);
    return "languages";
  }

  @RequestMapping("/react")
  String react(Map<String, Object> model) {
    ArrayList<ProjectList> project = new ArrayList<>();
    project.add(new ProjectList("petsmart", "/pics/petsmart.JPG"));
    model.put("Projects", project);
    return "languages";
  }

  @RequestMapping(value = "/languages/{urlParameter}")
  String viewProject(@RequestParam("project") String project, Map<String, Object> model) {
    API api = new API();
    String urlApi = "https://appsolutions.pythonanywhere.com/api/v12/data/html/" + project;
    String html = api.pullData(urlApi);
    model.put("body", html);
    // System.out.println(project);
    return "viewProject";
  }

  // This is web frameworks
  @RequestMapping("/django")
  String django() {
    return "confirmation";
  }

  @RequestMapping("/flask")
  String flask() {
    return "confirmation";
  }

  @RequestMapping("/tomcat")
  String tomcat() {
    return "confirmation";
  }

  @RequestMapping("/node")
  String node() {
    return "confirmation";
  }

  @RequestMapping("/pythonanywhere")
  String pythonanywhere() {
    return "confirmation";
  }

  @RequestMapping("/heroku")
  String heroku() {
    return "confirmation";
  }

  @RequestMapping("/personal")
  String personal() {
    return "confirmation";
  }

  @RequestMapping("/minecraftserver")
  String minecraft(Map<String, Object> model) {
    String commentSection = "{\"comments\":[{\"user\":\"John\", \"comment\":\"Doe\"},{\"user\":\"Anna\", \"comment\":\"Smith\"},{\"user\":\"Peter\", \"comment\":\"Jones\"}";

    model.put("commentSections", commentSection);
    return "minecraft";
  }

  @RequestMapping("/minecraft/server")
  String minecraftServer(Map<String, Object> model) {

    return "minecraftServer";
  }

  @RequestMapping("/db")
  String db(Map<String, Object> model) {
    try (Connection connection = dataSource.getConnection()) {
      Statement stmt = connection.createStatement();
      stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
      stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
      ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

      ArrayList<String> output = new ArrayList<String>();
      while (rs.next()) {
        output.add("Read from DB: " + rs.getTimestamp("tick"));
      }

      model.put("records", output);
      return "db";
    } catch (Exception e) {
      model.put("message", e.getMessage());
      return "error";
    }
  }

  @Bean
  public DataSource dataSource() throws SQLException {
    if (dbUrl == null || dbUrl.isEmpty()) {
      return new HikariDataSource();
    } else {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(dbUrl);
      return new HikariDataSource(config);
    }
  }

}