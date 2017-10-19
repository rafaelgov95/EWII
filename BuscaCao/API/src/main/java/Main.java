import static spark.Spark.*;

import br.buscacao.controller.CaoServico;
import br.buscacao.controller.DonoServico;
import br.buscacao.controller.LoginServico;

import com.google.gson.Gson;


public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Rotas Login
        post("/login", (req, res) -> LoginServico.authenticate(req, res), gson::toJson);
        post("/login/save", (req, res) -> LoginServico.create(req, res), gson::toJson);

        before("/api/*", (req, res) -> LoginServico.requeriToken(req));

        // Rotas Cao
        post("/api/cao/save", (req, res) -> CaoServico.create(req), gson::toJson);
        get("/api/cao/getAll", (req, res) -> CaoServico.getAll(req), gson::toJson);
        get("/api/cao/get/:nome", (req, res) -> CaoServico.get(req), gson::toJson);

        // Rotas Dono
        post("/api/dono/save", (req, res) -> DonoServico.create(req), gson::toJson);
        get("/api/dono/getAll", (req, res) -> DonoServico.getAll(req), gson::toJson);
        get("/api/dono/get/:email", (req, res) -> DonoServico.getEmail(req), gson::toJson);
        get("/api/dono/get/:nome", (req, res) -> DonoServico.getNome(req), gson::toJson);

    }
}
