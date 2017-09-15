package bissexto;
import java.util.Map;

import template.VelocityTemplateEngine;

import java.util.HashMap;
import spark.ModelAndView;

import static spark.Spark.*;

public class AnoBissexto {
    public static void main(String[] args) {
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/home.vtl");
            return new ModelAndView(model, layout); // permite passar informações dinâmicas (como variáveis) do Java para nossos arquivos de template
        }, new VelocityTemplateEngine());

        get("/detector", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/detector.vtl");

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