package BancoDeDados;

import Template.VelocityTemplateEngine;
import spark.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class Main {
    public static void main(String[] args) {
        String layout = "templates/index.vtl";
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("Template", "templates/index-1.vtl");
            return new ModelAndView(model, layout); // permite passar informações dinâmicas (como variáveis) do Java para nossos arquivos de Template
        }, new VelocityTemplateEngine());
        get("/index-1", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("Template", "templates/index-1.vtl");
            return new ModelAndView(model, "templates/index-1.vtl"); // permite passar informações dinâmicas (como variáveis) do Java para nossos arquivos de Template
        }, new VelocityTemplateEngine());
        get("/index-2", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("Template", "templates/index-1.vtl");
            return new ModelAndView(model, "templates/index-2.vtl"); // permite passar informações dinâmicas (como variáveis) do Java para nossos arquivos de Template
        }, new VelocityTemplateEngine());
        get("/index-3", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("Template", "templates/index-1.vtl");
            return new ModelAndView(model, "templates/index-3.vtl"); // permite passar informações dinâmicas (como variáveis) do Java para nossos arquivos de Template
        }, new VelocityTemplateEngine());
        get("/index-4", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
//            model.put("Template", "templates/index-1.vtl");
            return new ModelAndView(model, "templates/index-4.vtl"); // permite passar informações dinâmicas (como variáveis) do Java para nossos arquivos de Template
        }, new VelocityTemplateEngine());
        get("/detector", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("Template", "templates/OutrasAtividades/detector.vtl");

            String year = request.queryParams("year");
            Integer integerYear = Integer.parseInt(year);
            Boolean isLeapYear = isLeapYear(integerYear);

            model.put("isLeapYear", isLeapYear);
            model.put("year", request.queryParams("year"));
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }

    public static Boolean isLeapYear(Integer year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else {
            return year % 4 == 0;
        }
    }
}