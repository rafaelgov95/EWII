import static spark.Spark.*;

import br.buscacao.api.UserApi;
import br.buscacao.models.User.Login;
import com.google.gson.Gson;
import com.mongodb.MongoClient;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class Main {
    public static void main(String[] args) {
        final Morphia morphia = new Morphia();
        Gson gson = new Gson();
        morphia.mapPackage("org.mongodb.morphia.example");

        final Datastore datastore = morphia.createDatastore(new MongoClient(), "BuscaCao");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();

        get("/login/getAll", (request, response) -> {
            return  datastore.createQuery(Login.class).asList();
        }, gson::toJson);

        get("/login/:email", (req, res) -> {
            String email = req.params("email");
            return datastore.createQuery(Login.class)
                    .filter("email ==", email)
                    .asList();
        }, gson::toJson);

        post("/login/save", "application/json", (request, response) -> {
            datastore.save(gson.fromJson(request.body(), Login.class));
            response.status(200);
            return null;
        }, gson::toJson);



    }
}
