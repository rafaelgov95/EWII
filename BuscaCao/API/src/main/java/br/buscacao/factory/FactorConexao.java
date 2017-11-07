package br.buscacao.factory;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.ValidationExtension;

import javax.management.MXBean;

public class FactorConexao {
    private final Morphia morphia;
    private final Datastore datastore;
    private final ValidationExtension validate;
    private FactorConexao(){
        this.morphia = new Morphia();

        morphia.getMapper().getOptions().setMapSubPackages(true);
        morphia.mapPackage("br.buscacao.models");
        validate = new ValidationExtension(morphia);
        this.datastore = morphia.createDatastore(new MongoClient(), "busca_cao");
        datastore.getDB().dropDatabase();
        datastore.ensureIndexes();

    }

    public ValidationExtension validatingMongoEventListener() {
        return   validate;
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
