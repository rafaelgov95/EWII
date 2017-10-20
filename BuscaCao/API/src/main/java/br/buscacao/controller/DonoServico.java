package br.buscacao.controller;

import br.buscacao.models.usuario.Dono;
import br.buscacao.factory.FactorConexao;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.List;

public class DonoServico {

    public static Dono create(Request req, Response res) {
        Gson gson = new Gson();
        Dono dono = gson.fromJson(req.body(), Dono.class);
        FactorConexao.getInstance().db().save(dono);
        return dono;

    }

    public static List<Dono> getAll(Request req){
        return FactorConexao.getInstance().db().createQuery(Dono.class).asList();
    }
    public static Dono getEmail(Request req){
        return FactorConexao.getInstance().db().createQuery(Dono.class)
                .filter("nome ==", req.params("nome")).asList().get(0);
    }
    public static Dono getNome(Request req){
        return FactorConexao.getInstance().db().createQuery(Dono.class)
                .filter("email ==", req.params("email")).asList().get(0);
    }

    public static Dono update(Request req){
        Gson gson = new Gson();
        Dono dono = gson.fromJson(req.body(), Dono.class);

        return FactorConexao.getInstance().db().createQuery(Dono.class)
                .filter("email ==", req.params("email")).asList().get(0);
    }
}
