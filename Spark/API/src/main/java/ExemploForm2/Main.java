package ExemploForm2;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import Template.VelocityTemplateEngine;
import spark.ModelAndView;

public class Main {
    public static void main(String[] args) {
        staticFileLocation("/public");
        final String layout = "templates/layout.vtl";

        get("/favorite_photos", (request, response) -> {
            Map model = new HashMap();
            model.put("Template", "templates/OutrasAtividades/favorite_photos.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/form", (request, response) -> {
            HashMap model = new HashMap();
            model.put("Template", "templates/OutrasAtividades/form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/greeting_card", (request, response) -> {
            HashMap model = new HashMap();
            String remetente = request.queryParams("remetente"); // form name
            String destinatario = request.queryParams("destinatario");

            model.put("remetente", remetente);
            model.put("destinatario", destinatario);
            model.put("Template", "templates/OutrasAtividades/greeting_card.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    }
}