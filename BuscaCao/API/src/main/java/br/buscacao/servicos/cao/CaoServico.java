package br.buscacao.servicos.cao;

import br.buscacao.models.cao.Cao;
import br.buscacao.factory.FactorConexao;
import br.buscacao.models.usuario.Dono;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.List;

public class CaoServico {


    public static boolean create(Request req, Response res) {
        Gson gson = new Gson();
        Cao cao =gson.fromJson(req.body(), Cao.class);
        if (cao != null) {
            FactorConexao.getInstance().db().save(cao);
            return true;
        }
        return false;
    }

    public static List<Cao> getAll(Request req, Response res){
        return FactorConexao.getInstance().db().createQuery(Cao.class).asList();
    }

    public static Cao get(Request req, Response res){
        return FactorConexao.getInstance().db().createQuery(Cao.class)
                .filter("nome ==", req.params("nome")).asList().get(0);

    }
    public static Object remover(Request req, Response res){
        Gson gson = new Gson();
        return FactorConexao.getInstance().db().delete(gson.fromJson(req.body(), Cao.class));
    }

}
