package com.example.listadetarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";

    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { //cria o db, só é executado 1 vez, a n ser que seja chamado novamente

        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_TAREFAS
                    + "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " nome TEXT NOT NULL);";
        try {
            db.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao Criar Tabela");
        }catch (Exception e) {
            Log.i("INFO DB", "Erro ao Criar Tabela" + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //alterações de tabela, novas versoes
    }
}
