package br.buscacao.controller;

import br.buscacao.models.cao.Cao;
import br.buscacao.factory.FactorConexao;
import com.google.gson.Gson;
import spark.Request;

import java.util.List;

public class CaoServico {


    public static boolean create(Request req) {
        Gson gson = new Gson();
        Cao cao =gson.fromJson(req.body(), Cao.class);
        if (cao != null) {
            FactorConexao.getInstance().db().save(cao);
            return true;
        }
        return false;
    }

    public static List<Cao> getAll(Request req){
        return FactorConexao.getInstance().db().createQuery(Cao.class).asList();
    }

    public static Cao get(Request req){
        return FactorConexao.getInstance().db().createQuery(Cao.class)
                .filter("nome ==", req.params("nome")).asList().get(0);

    }
}
