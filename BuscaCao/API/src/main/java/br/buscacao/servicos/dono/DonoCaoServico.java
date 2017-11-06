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
import spark.Request;
import spark.Response;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DonoCaoServico {


    public static Cao create(Request req, Response res) {
        Gson gson = new Gson();
        String token =  req.headers("X-API-TOKEN");
        try {
            Algorithm algorithm = Algorithm.HMAC256(Config.Secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            Claim claim = jwt.getHeaderClaim("token");
            System.out.println(claim.asString());
            JsonParser parser = new JsonParser();
            JsonObject o = parser.parse(claim.asString()).getAsJsonObject();
            System.out.println(req.body());
            Cao cao = gson.fromJson(req.body(), Cao.class);
            cao.setDono(o.get("id").getAsString());
            FactorConexao.getInstance().db().save(cao);
            return cao;
        } catch (UnsupportedEncodingException exception){
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }
return null;
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
