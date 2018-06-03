package br.com.usjt.skyllaion.geodata.controller;

import android.provider.BaseColumns;

public class Contract {
    public static abstract class PaisEntry implements BaseColumns {
        public static final String TABLE_NAME = "pais";
        public static final String NOME = "nome";
        public static final String REGIAO = "regiao";
        public static final String CAPITAL = "capital";
        public static final String BANDEIRA = "bandeira";
        public static final String CODIGO = "codigo";
    }
}
