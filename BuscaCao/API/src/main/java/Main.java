import static spark.Spark.*;

import br.buscacao.controller.CaoServico;
import br.buscacao.controller.DonoServico;
import br.buscacao.controller.LoginServico;

import com.google.gson.Gson;


public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
        path("/", () -> {
            path("/login", () -> {
                post("", (req, res) -> LoginServico.authenticate(req, res),gson::toJson);
                post("/save", (req, res) -> LoginServico.create(req, res),gson::toJson);

            });
            path("/api", () -> {
                before("/*", (q, a) -> LoginServico.requeriToken(q, a));
                path("/cao", () -> {
                    post("/save", (req, res) -> CaoServico.create(req),gson::toJson);
                    get("/getAll", (req, res) -> CaoServico.getAll(req),gson::toJson);
                    get("/get/:nome", (req, res) -> CaoServico.get(req),gson::toJson);
                });
                path("/dono", () -> {
                    post("/save", (req, res) -> DonoServico.create(req, res),gson::toJson);
                    get("/get/:nome", (req, res) -> DonoServico.getNome(req),gson::toJson);
                    get("/get/:email", (req, res) -> DonoServico.getEmail(req),gson::toJson);
                    get("/getAll", (req, res) -> DonoServico.getAll(req),gson::toJson);
                    delete("/delete/:email", (req, res) -> CaoServico.create(req),gson::toJson);
                });
            });
        });
    }
}
