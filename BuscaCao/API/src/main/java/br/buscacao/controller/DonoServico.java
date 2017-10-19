package br.buscacao.controller;

import br.buscacao.models.user.Address;
import br.buscacao.models.user.Dono;
import br.buscacao.factory.FactorConexao;
import com.google.gson.Gson;
import spark.Request;

import java.util.List;

public class DonoServico {

    public static boolean create(Request req) {
        Gson gson = new Gson();
        Dono dono = gson.fromJson(req.body(), Dono.class);
        Address endereco = gson.fromJson(dono.getAddress().toString(), Address.class);
        FactorConexao.getInstance().db().save(dono);
        System.out.printf(endereco.getState());
        return true;

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
}
