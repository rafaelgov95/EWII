package BancoDeDados;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class TarefaTest {

    @After
    public void limparBancoDeDados() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM tarefas *;";
            con.createQuery(sql).executeUpdate();
        }
    }

    @Test
    public void Tarefa_instanciaCorretamente() {
        Tarefa minhaTarefa = new Tarefa("Cortar a grama");
        assertEquals(true, minhaTarefa instanceof Tarefa);
    }

    @Test
    public void getDescription_instanciaTarefaComDescricao() {
        Tarefa minhaTarefa = new Tarefa("Cortar a grama");
        assertEquals("Cortar a grama", minhaTarefa.getDescription());
    }

    @Test
    public void tudoVazioPrimeiraExecucao() {
        assertEquals(Tarefa.all().size(), 0);
    }

    @Test
    public void retornaTrueSeDescriptionIgual() {
        Tarefa primeiraTarefa = new Tarefa("Cortar a grama");
        Tarefa segundaTarefa = new Tarefa("Cortar a grama");
        assertTrue(primeiraTarefa.equals(segundaTarefa));
    }

    @Test
    public void salvarRetornaVerdadeiroSeDesciptionIgual() {
        Tarefa minhaTarefa = new Tarefa("Cortar a grama");
        minhaTarefa.save();
        assertTrue(Tarefa.all().get(0).equals(minhaTarefa));
    }

    @Test
    public void salvarAtribuicaoIdParaObjeto() {
        Tarefa minhaTarefa = new Tarefa("Cortar a grama");
        minhaTarefa.save();
        Tarefa tarefaJaSalva = Tarefa.all().get(0);
        assertEquals(minhaTarefa.getId(), tarefaJaSalva.getId());
    }

    @Test
    public void encontrarTarefaNoBd() {
        Tarefa minhaTarefa = new Tarefa("Cortar a grama");
        minhaTarefa.save();
        Tarefa tarefaJaSalva = Tarefa.find(minhaTarefa.getId());
        assertTrue(minhaTarefa.equals(tarefaJaSalva));
    }

}