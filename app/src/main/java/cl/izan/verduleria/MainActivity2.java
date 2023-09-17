package cl.izan.verduleria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    private EditText editTextVer;
    private EditText editTextNHec;
    private Button btnCalcular;

    private Button btnLimpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTextVer = findViewById(R.id.editTextVer);
        editTextNHec = findViewById(R.id.editTextHec);
        btnCalcular = findViewById(R.id.btnCalcular);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextVer.setText("");
                editTextNHec.setText("");
            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcularResultado();
            }
        });
    }

    private void calcularResultado() {
        String strVerduras = editTextVer.getText().toString();
        String strHectareas = editTextNHec.getText().toString();

        try {
            int verduras = Integer.parseInt(strVerduras);
            int hectareas = Integer.parseInt(strHectareas);

            int resultado = verduras * hectareas;

            // Crear un Intent para enviar el resultado a MainActivity3
            Intent intent = new Intent(this, MainActivity3.class);
            intent.putExtra("resultado", resultado);
            startActivity(intent);
        } catch (NumberFormatException e) {
            // Manejar error de formato
        }
    }

}
