package com.example.listadetarefas.helper;

//Data Acess Object

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.listadetarefas.model.Tarefa;

import java.util.ArrayList;
import java.util.List;

public class TarefaDAO implements ITarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        escreve = db.getWritableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try {
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO ", "Tarefa Salva com Sucesso");
        }catch (Exception e){
            Log.e("INFO ", "Erro ao Salvar Tarefa " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {

        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa());

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args );
            Log.i("INFO ", "Tarefa Atualizada com Sucesso!");
        }catch (Exception e){
            Log.e("INFO ", "Erro ao Atualizar Tarefa " + e.getMessage());
            return false;
        }


        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.delete(DbHelper.TABELA_TAREFAS, "id=?", args);
            Log.i("INFO ", "Tarefa Removida com Sucesso!");
        }catch (Exception e){
            Log.e("INFO ", "Erro ao Remover Tarefa." + e.getMessage());
            return false;
        }


        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TABELA_TAREFAS + ";";
        Cursor c = ler.rawQuery(sql, null); // o null poderiam ser filtros

        while (c.moveToNext()) {
            Tarefa tarefa = new Tarefa();

            Long id = c.getLong(c.getColumnIndex("id"));
            String nomeTarefa = c.getString(c.getColumnIndex("nome"));

            tarefa.setId(id);
            tarefa.setNomeTarefa(nomeTarefa);

            tarefas.add(tarefa);
        }
        return tarefas;
    }
}
