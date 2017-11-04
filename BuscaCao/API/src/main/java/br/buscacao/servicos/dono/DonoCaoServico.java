package br.buscacao.servicos.dono;

import br.buscacao.factory.FactorConexao;
import br.buscacao.models.cao.Cao;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.List;

public class DonoCaoServico {


    public static Cao create(Request req, Response res) {
        Gson gson = new Gson();
        Cao cao = gson.fromJson(req.body(), Cao.class);
        FactorConexao.getInstance().db().save(cao);
        return cao;
    }

    public static List<Cao> getAll(Request req){
        return FactorConexao.getInstance().db().createQuery(Cao.class).asList();
    }
    public static Cao getNome(Request req){

        return FactorConexao.getInstance().db().createQuery(Cao.class)
                .filter("nome ==", req.params("nome")).asList().get(0);
    }
    public static Cao getApelido(Request req){

        return FactorConexao.getInstance().db().createQuery(Cao.class)
                .filter("apelido ==", req.params("apelido")).asList().get(0);
    }
    public static Object remover(Request req, Response res){
        Gson gson = new Gson();
        return FactorConexao.getInstance().db().delete(gson.fromJson(req.body(), Cao.class));
    }
}
