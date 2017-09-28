package Formulario;
import java.util.HashMap;
import Template.VelocityTemplateEngine;
import spark.ModelAndView;
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        final String layout = "templates/layout.vtl";

        get("/form", (request, response) -> {
            HashMap model = new HashMap();

            model.put("Template", "templates/OutrasAtividades/form_madlib.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/story", (request, response) -> {
            HashMap model = new HashMap();

            model.put("person1", request.queryParams("person1"));
            model.put("person2", request.queryParams("person2"));
            model.put("animal", request.queryParams("animal"));
            model.put("exclamation", request.queryParams("exclamation"));
            model.put("verb", request.queryParams("verb"));
            model.put("noun", request.queryParams("noun"));

            model.put("Template", "templates/story.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());
    }
}