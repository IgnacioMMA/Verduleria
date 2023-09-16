package cl.izan.verduleria;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity3 extends AppCompatActivity {

    private ListView listViewResultados;
    private List<Integer> resultados = new ArrayList<>(); // Lista para almacenar los resultados
    private ArrayAdapter<Integer> adapter;
    private Button btnVolver;
    private Button btnSalir;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listViewResultados = findViewById(R.id.listViewResultados);

        // Obtener el resultado de la Intent
        int resultado = getIntent().getIntExtra("resultado", 0);

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("resultados", Context.MODE_PRIVATE);

        // Cargar resultados anteriores desde SharedPreferences
        Set<String> resultadoSet = sharedPreferences.getStringSet("resultados", new HashSet<String>());
        for (String result : resultadoSet) {
            resultados.add(Integer.parseInt(result));
        }

        // Agregar el resultado recién calculado
        resultados.add(resultado);

        // Guardar los resultados en SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Set<String> resultadosSet = new HashSet<>();
        for (Integer res : resultados) {
            resultadosSet.add(res.toString());
        }
        editor.putStringSet("resultados", resultadosSet);
        editor.apply();

        // Configurar el adaptador para la ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultados);
        listViewResultados.setAdapter(adapter);

        // Configurar el botón para volver a MainActivity2
        btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed(); // Volver a la actividad anterior
            }
        });

        // Configurar el botón para salir de la aplicación
        btnSalir = findViewById(R.id.btnSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Cerrar la actividad actual
            }
        });
    }
}
