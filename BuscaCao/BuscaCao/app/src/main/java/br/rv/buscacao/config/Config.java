package br.rv.buscacao.config;

/**
 * Created by rafael on 29/10/17.
 */

public class Config {
//    public static String host = "http://192.168.1.106:4567";
//            public static String host="http://192.168.43.165:4567";
    public static String host = "http://engweb2.ddns.net:8080/bc";
    public static String Secret = "meucaodevolta";
    public static String SHARED_PREF_NAME = "BD_Busca_Cao";
    public static String BD_TOKEN = "";
    public static String ID = "id";

    public static String EMAIL = "email";
    public static String TOKEN = "X-API-TOKEN";
    public static String USUARIO = "usuario";
    public static String USER_NAME = "user_name";
    public static String LOGGEDIN_SHARED_PREF = "isLogin";
    public static String login = host + "/login";
    public static String cadastrar = host + "/login/save";
    public static String cadastrar_cao = host + "/api/dono/cao/save";
    public static String my_get_all = host + "/api/dono/cao/mygetall";
    public static String mapa_get_all = host + "/api/dono/cao/getall";
    public static String dono_cao_update = host + "/api/dono/cao/update/";

    public static String dono_cao_delete = host + "/api/dono/cao/delete/";


}