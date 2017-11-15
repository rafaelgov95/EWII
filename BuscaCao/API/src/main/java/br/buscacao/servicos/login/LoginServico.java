package br.buscacao.servicos.login;

import br.buscacao.models.login.Login;
import br.buscacao.factory.FactorConexao;
import br.buscacao.models.usuario.Dono;
import br.buscacao.util.Config;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.DuplicateKeyException;
import com.sun.org.apache.bcel.internal.generic.FADD;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.junit.Assert;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;
import sun.rmi.runtime.Log;


import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

public class LoginServico {
    Logger logger = Logger.getLogger("lopes");

    public static String authenticate(Request req, Response res) {
        Gson gson = new Gson();
        Login login = gson.fromJson(req.body(), Login.class);
        List<Login> listLogin = FactorConexao.getInstance().db().createQuery(Login.class)
                .filter("email ==", login.getEmail())
                .asList();

        if (!listLogin.isEmpty()) {
            if (BCrypt.checkpw(login.getPassword(), listLogin.get(0).getPassword())) {
                try {

                    Map<String, Object> headerClaims = new HashMap<>();
                    headerClaims.put("token",listLogin.get(0).getId_conta());
                    Algorithm algorithm = Algorithm.HMAC256(Config.Secret);
                    String token = JWT.create()
                            .withHeader(headerClaims)
                            .sign(algorithm);
                    res.body(token);
                } catch (UnsupportedEncodingException exception) {
                    //UTF-8 encoding not supported
                } catch (JWTCreationException exception) {
                    //Invalid Signing configuration / Couldn't convert Claims.
                }
            }
        }else{
            res.body("ERRO");
        }
        return res.body();
    }

    public static boolean requeriToken(Request req, Response res) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(Config.Secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .acceptLeeway(1)   //1 sec for nbf and iat
                    .acceptExpiresAt(1200)
                    .build(); //Reusable verifier instance

            DecodedJWT jwt = verifier.verify(req.headers("X-API-TOKEN"));


            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("Erro Token Inválido");
        return false;
    }

    public static String create(Request req, Response res) {
        // Hash a password for the first time
        Gson gson = new Gson();
        Login login = gson.fromJson(req.body(), Login.class);
        try {
            String senha = BCrypt.hashpw(login.getPassword(), BCrypt.gensalt(12));
            Dono dono = gson.fromJson(req.body(), Dono.class);
            dono.setPassword(senha);
            login.setId_conta(FactorConexao.getInstance().db().save(dono).getId().toString());
            System.out.println(dono.getPassword());
            login.setPassword(senha);
            FactorConexao.getInstance().db().save(login);

            res.body("OK");
            res.status(200);

        } catch (DuplicateKeyException e) {
            res.body("Error: Email já cadastrado!!");
            res.status(400);
        }
        return res.body();
    }

    public static List<Login> getAll(Request req, Response res) {
        return FactorConexao.getInstance().db().createQuery(Login.class).asList();
    }

    public static Login getId(Request req) {
        Gson gson = new Gson();
        Login login = gson.fromJson(req.body(), Login.class);
        System.out.println(req.params("id"));
        return FactorConexao.getInstance().db().createQuery(Login.class)
                .filter("id ==", req.params("id")).asList().get(0);

    }

    public static Login getEmail(Request req, Response res) {
        return FactorConexao.getInstance().db().createQuery(Login.class)
                .filter("nome ==", req.params("email")).asList().get(0);
    }

    public static Object remover(Request req, Response res) {
        Gson gson = new Gson();
        System.out.println(req.body());
        System.out.println("serapara");
        System.out.println(req.params("id"));
        return FactorConexao.getInstance().db().delete(req.params("id"));
    }
}
