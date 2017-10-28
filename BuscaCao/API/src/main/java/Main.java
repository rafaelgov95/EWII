import static spark.Spark.*;

import br.buscacao.servicos.CaoServico;
import br.buscacao.servicos.DonoServico;
import br.buscacao.servicos.LoginServico;

import com.google.gson.Gson;
import org.bson.types.ObjectId;
import spark.servlet.SparkApplication;


public class Main implements SparkApplication{

    public static void main(String[] args)  {new Main().init();}
    @Override
    public void init() {
            Gson gson = new Gson();
            path("/", () -> {
                path("/login", () -> {
                    post("", (req, res) -> LoginServico.authenticate(req, res),gson::toJson);
                    post("/save", (req, res) -> LoginServico.create(req, res),gson::toJson);
                });
                path("/api", () -> {
                    before("/*", (q, a) -> LoginServico.requeriToken(q, a));
                    path("/cao", () -> {
                        post("/save", (req, res) -> CaoServico.create(req,res),gson::toJson);
                        get("/getall", (req, res) -> CaoServico.getAll(req,res),gson::toJson);
                        get("/get/:nome", (req, res) -> CaoServico.get(req,res),gson::toJson);
                        delete("/delete/:id", (req, res) -> CaoServico.remover(req,res),gson::toJson);
                    });
                    path("/dono", () -> {
                        post("/save", (req, res) -> DonoServico.create(req, res),gson::toJson);
                        get("/get/:nome", (req, res) -> DonoServico.getNome(req),gson::toJson);
                        get("/getall", (req, res) -> DonoServico.getAll(req),gson::toJson);
                        delete("/delete/:id", (req, res) -> DonoServico.remover(req,res),gson::toJson);
                        path("/cao", () -> {
                            post("/save", (req, res) -> DonoServico.create(req, res),gson::toJson);
                            get("/get/:nome", (req, res) -> DonoServico.getNome(req),gson::toJson);
                            get("/getall", (req, res) -> DonoServico.getAll(req),gson::toJson);
                            delete("/delete/:id", (req, res) -> DonoServico.remover(req,res),gson::toJson);
                        });

                    });
                    path("/login", () -> {
                        get("/get/:id", (req, res) -> LoginServico.getId(req),gson::toJson);
                        get("/getall", (req, res) -> LoginServico.getAll(req, res),gson::toJson);
                        delete("/remover/:id",(req,res)->LoginServico.remover(req,res),gson::toJson);
                    });

                });
            });
        }

}
