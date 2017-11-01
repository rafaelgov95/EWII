package br.rv.buscacao.config;

/**
 * Created by rafael on 29/10/17.
 */

public class Config {
    public static String host="http://192.168.43.165:4567";
//        public static String host="http://192.168.1.105:4567";
//    public static String host = "http://engweb2.ddns.net:8080/api";
    public static String SHARED_PREF_NAME ="Token";
    public static String TOKEN = "X-TOKEN";
    public static String LOGGEDIN_SHARED_PREF = "isLogin";
    public static String login = host + "/login";
    public static String cadastrar = host + "/login/save";

}
