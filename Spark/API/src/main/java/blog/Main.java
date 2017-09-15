package blog;

import static spark.Spark.*;
public class Main {
    public static void main(String[] args) {
        port(8081); // Spark will run on port 8080
        externalStaticFileLocation("/home/rafael/Documentos/EWII/Speck/front-end/public/");
//        get("api/posts/listar", (request, response) ->{
//            ;
//        });

        get("/teste", (request, response) ->{response.redirect("/"); return null;});

    }
}
