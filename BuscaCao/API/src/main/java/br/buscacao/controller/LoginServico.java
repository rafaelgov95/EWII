package br.buscacao.controller;

import br.buscacao.models.login.Login;
import br.buscacao.factory.FactorConexao;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;
import org.mongodb.morphia.query.UpdateOperations;
import spark.Request;
import spark.Response;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class LoginServico {
    Logger logger = Logger.getLogger("lopes");

    public static boolean authenticate(Request req, Response res) {
        Gson gson = new Gson();
        Login login = gson.fromJson(req.body(), Login.class);
        List<Login> listLogin = FactorConexao.getInstance().db().createQuery(Login.class)
                .filter("email ==", login.getEmail())
                .asList();
        if (!listLogin.isEmpty()) {
            if (BCrypt.checkpw(login.getPassword(), listLogin.get(0).getPassword())) {
                try {
                    Map<String, Object> headerClaims = new HashMap<>();
                    headerClaims.put("token", gson.toJson(listLogin.get(0), Login.class));
                    Algorithm algorithm = Algorithm.HMAC256("meucaodevolta");
                    String token = JWT.create()
                            .withHeader(headerClaims)
                            .sign(algorithm);
                    res.header("X-API-TOKEN", token);
                    return true;
                } catch (UnsupportedEncodingException exception) {
                    //UTF-8 encoding not supported
                } catch (JWTCreationException exception) {
                    //Invalid Signing configuration / Couldn't convert Claims.
                }
            }
        }
        return false;

    }

    public static boolean requeriToken(Request req, Response res) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("meucaodevolta");
            JWTVerifier verifier = JWT.require(algorithm)
                    .acceptLeeway(1)   //1 sec for nbf and iat
                    .acceptExpiresAt(1200)
                    .build(); //Reusable verifier instance

            DecodedJWT jwt = verifier.verify(req.headers("X-API-TOKEN"));
            Claim claim = jwt.getHeaderClaim("token");
            System.out.println(claim.asString());

            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println("Erro Token Inválido");
        return false;
    }

    public static Login create(Request req, Response res) {
        // Hash a password for the first time
        Gson gson = new Gson();
        Login login = gson.fromJson(req.body(), Login.class);
        String hashed = BCrypt.hashpw(login.getPassword(), BCrypt.gensalt(12));
        login.setPassword(hashed);
        FactorConexao.getInstance().db().save(login);
        return login;
    }
//    public static void prePersist()->{ LoginServico.] = new Date()}
}
