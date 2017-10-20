package br.buscacao.factory;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

public class FactorConexao {
    private final Morphia morphia;
    private final Datastore datastore;

    private FactorConexao(){
        this.morphia = new Morphia();
        morphia.getMapper().getOptions().setMapSubPackages(true);
        morphia.mapPackage("br.buscacao.models");
        this.datastore = morphia.createDatastore(new MongoClient(), "BuscaCao");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();
    }
    public Datastore db(){
        return datastore;
    }
    public static FactorConexao getInstance() {
        return FactorConexaoHolder.INSTANCE;
    }
    /**
     * Classe privada que armazena a única instância de FactorConexao.
     */
    private static class FactorConexaoHolder {

        private static final FactorConexao INSTANCE = new FactorConexao();
    }
}
