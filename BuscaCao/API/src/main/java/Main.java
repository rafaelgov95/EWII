import static spark.Spark.*;

import br.buscacao.controller.CaoServico;
import br.buscacao.controller.DonoServico;
import br.buscacao.controller.LoginServico;
import br.buscacao.util.FactorConexao;

import com.google.gson.Gson;


public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        post("/login", "application/json", (req, res) ->LoginServico.authenticate(req, res), gson::toJson);
        post("/login/save", "application/json", (req, res) -> LoginServico.create(req, res), gson::toJson);

        before("/api/*", (req, res) -> LoginServico.requeriToken(req));


        post("/api/cao/save", "application/json", (req, res) -> CaoServico.create(req), gson::toJson);
        get("/api/cao/getAll", (req, res) -> CaoServico.List(req), gson::toJson);
        get("/api/cao/get/:nome", (req, res) -> CaoServico.Buscar(req), gson::toJson);


        post("/api/dono/save", "application/json", (req, res) ->DonoServico.create(req) , gson::toJson);
        get("/api/dono/getAll", (request, response) -> FactorConexao.getInstance().db().createQuery(Dono.class).asList(), gson::toJson);
        get("/api/dono/get/:email", (req, res) ->
                FactorConexao.getInstance().db().createQuery(Login.class)
                        .filter("nome ==", req.params("nome"))
                        .asList(), gson::toJson);

    }
}
