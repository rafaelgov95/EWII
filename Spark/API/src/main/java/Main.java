
import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(8081); // Spark will run on port 8080
        externalStaticFileLocation("/home/rafael/Documentos/EWII/Spark/front-end/public/");

    }
}
