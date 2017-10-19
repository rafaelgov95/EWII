package br.buscacao.controller;

import br.buscacao.models.user.Address;
import br.buscacao.models.user.Dono;
import br.buscacao.util.FactorConexao;
import com.google.gson.Gson;
import spark.Request;

public class DonoServico {

    public static boolean create(Request req) {
        Gson gson = new Gson();
        Dono dono = gson.fromJson(req.body(), Dono.class);
        Address endereco = gson.fromJson(dono.getAddress().toString(), Address.class);
        FactorConexao.getInstance().db().save(dono);
        System.out.printf(endereco.getState());
        return true;

    }
}
