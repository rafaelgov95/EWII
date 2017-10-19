package br.buscacao.controller;

import br.buscacao.models.login.Login;
import br.buscacao.util.FactorConexao;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;
import spark.Request;
import spark.Response;


import java.io.UnsupportedEncodingException;
import java.util.List;

public class LoginServico {
    public static boolean authenticate(Request req, Response res) {
        Gson gson = new Gson();
        Login login = gson.fromJson(req.body(), Login.class);
        List<Login> listLogin = FactorConexao.getInstance().db().createQuery(Login.class)
                .filter("email ==", login.getEmail())
                .asList();
        if (!listLogin.isEmpty()) {
            if (BCrypt.checkpw(login.getPassword(), listLogin.get(0).getPassword())) {
                System.out.println("It matches");
                try {
                    Algorithm algorithm = Algorithm.HMAC256("meucaodevolta");
                    String token = JWT.create()
                            .withIssuer("auth0")
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

    public static void requeriToken(Request req) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("meucaodevolta");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(req.headers("X-API-TOKEN"));
        } catch (JWTVerificationException exception) {

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

}
