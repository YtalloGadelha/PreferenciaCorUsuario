package com.example.android.preferenciacorusuario;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnClickListener{

    private RadioGroup radioGroup;
    private RadioButton radioButtonSelecionado;
    private Button botaoSalvar;
    private RelativeLayout layout;

    private static  final String ARQUIVO_PREFEFENCIA = "ArqPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroupId);
        botaoSalvar = (Button) findViewById(R.id.botaoSalvarId);
        layout = (RelativeLayout) findViewById(R.id.layoutId);

        botaoSalvar.setOnClickListener(MainActivity.this);

        //Recuperar a cor salva no SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFEFENCIA, 0);
        if(sharedPreferences.contains("corEscolhida")){
            String corRecuperada = sharedPreferences.getString("corEscolhida", "Laranja");
            setBackground(corRecuperada);
        }
    }

    @Override
    public void onClick(View v) {

        int idRadioButtonSelecionado = radioGroup.getCheckedRadioButtonId();

        if(idRadioButtonSelecionado > 0){

            radioButtonSelecionado = (RadioButton) findViewById(idRadioButtonSelecionado);

            SharedPreferences sharedPreferences = getSharedPreferences(ARQUIVO_PREFEFENCIA, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String corEscolhida = radioButtonSelecionado.getText().toString();
            editor.putString("corEscolhida", corEscolhida);
            editor.commit();

            setBackground(corEscolhida);

        }
    }

    private void setBackground(String cor){

        if(cor.equals("Azul")){
            layout.setBackgroundColor(Color.parseColor("#495b7c"));
        }else if(cor.equals("Laranja")){
            layout.setBackgroundColor(Color.parseColor("#ffce26"));
        }else if(cor.equals("Verde")){
            layout.setBackgroundColor(Color.parseColor("#009670"));
        }
    }
}
