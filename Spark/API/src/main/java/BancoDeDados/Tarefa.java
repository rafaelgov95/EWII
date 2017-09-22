package BancoDeDados;

        import java.util.List;
        import org.sql2o.Connection;
        import org.sql2o.logging.SysOutLogger;

public class Tarefa {
    private int id;
    private String descricao;

    public Tarefa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescription() {
        return descricao;
    }

    public int getId() {
        return id;
    }

    public static List<Tarefa> all() {
        String sql = "SELECT id, descricao FROM tarefas";
        try (Connection con = DB.sql2o.open()) {
			/*
			 * executeAndFetch: transforma cada registro de informação em um objeto Java baseado no argumento, ex: Tarefa.class
			 */
            return con.createQuery(sql).executeAndFetch(Tarefa.class);
        }
    }

    @Override
    public boolean equals(Object outraTarefa) {
        if (!(outraTarefa instanceof Tarefa)) {
            return false;
        } else {
            Tarefa novaTarefa = (Tarefa) outraTarefa;
            return this.getDescription().equals(novaTarefa.getDescription()) && this.getId() == novaTarefa.getId();
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO tarefas(descricao) VALUES (:descricao)";
			/*
			 * true: Sql2o adiciona id, salvo como pk, para a query
			 */
            this.id = (int) con.createQuery(sql, true).addParameter("descricao", this.descricao).executeUpdate()
                    .getKey();
        }
    }

    public static Tarefa find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM tarefas where id=:id";
			/*
			 * Retornará o primeiro item na coleção retornado pelo bd, convertido (cast) para um objeto Tarefa
			 */
            Tarefa tarefa = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Tarefa.class);
            return tarefa;
        }
    }
}