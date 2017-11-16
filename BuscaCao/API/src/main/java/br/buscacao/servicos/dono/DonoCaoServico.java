package br.buscacao.servicos.dono;

import br.buscacao.factory.FactorConexao;
import br.buscacao.models.cao.Cao;
import br.buscacao.util.Config;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;
import org.mongodb.morphia.query.UpdateOperations;
import spark.Request;
import spark.Response;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DonoCaoServico {


    public static String create(Request req, Response res) {

        Gson gson = new Gson();
        String token = req.headers("X-API-TOKEN");

        String id = decoderJWT(token,"user_name");
        Cao cao = gson.fromJson(req.body(), Cao.class);

        if (id != null) {
            cao.setDono(id);
            return FactorConexao.getInstance().db().save(cao).toString();
        }
        return null;
    }





    private synchronized static String decoderJWT(String token,String at) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(Config.Secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Claim claim = jwt.getHeaderClaim(at);
            System.out.println(claim.asString());
            return claim.asString();

        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
        }
        return null;
    }

    public static String getAll(Request req) {
        Gson gson = new Gson();
        return gson.toJson(FactorConexao.getInstance().db().createQuery(Cao.class).asList());
    }


    public static String update(Request req,Response res) {
        Gson gson = new Gson();
        Cao cao = gson.fromJson(req.body(),Cao.class);
        System.out.println(cao.getNome());
         Query<Cao> c = FactorConexao.getInstance().db().createQuery(Cao.class).field("_id").equal(new ObjectId(cao.getId()));

        UpdateOperations<Cao> ops = FactorConexao.getInstance().db().createUpdateOperations(Cao.class);
        ops.set("apelido",cao.getApelido());
        ops.set("data_nasc",cao.getData_nasc());
        ops.set("data_p",cao.getData_p());
        ops.set("nome",cao.getNome());
        ops.set("raca",cao.getRaca());
        ops.set("resumo",cao.getResumo());
        ops.set("sexo",cao.getSexo());
        ops.set("imagen",cao.getImagen());
        ops.set("local",cao.getLocal());
        return FactorConexao.getInstance().db().update(c,ops).toString();

    }


    public static String myGetAll(Request req, Response resp) {
        Gson gson = new Gson();
        String token = req.headers("X-API-TOKEN");
        String id = decoderJWT(token,"user_name");
        if (id != null) {
            return gson.toJson(FactorConexao.getInstance().db().createQuery(Cao.class)
                    .filter("dono ==", id).asList());
        }
        return null;
    }

    public static Cao getNome(Request req) {

        return FactorConexao.getInstance().db().createQuery(Cao.class)
                .filter("nome ==", req.params("nome")).asList().get(0);
    }

    public static Cao getApelido(Request req) {

        return FactorConexao.getInstance().db().createQuery(Cao.class)
                .filter("apelido ==", req.params("apelido")).asList().get(0);
    }

    public static String remover(Request req, Response res) {
        System.out.println(req.params("id"));
        return FactorConexao.getInstance().db().delete(Cao.class,new ObjectId(req.params("id"))).toString();
    }
}
