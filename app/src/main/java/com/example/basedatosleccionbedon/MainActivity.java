package com.example.basedatosleccionbedon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //Variables de las clases que referencian a los controles
    private Button btnCrear, btnVer, btnEliminar, btnDireccionGo;
    private EditText etxtNombreRegistrado, etxtDireccionRegistrado;
    private EditText etxtNombreConsultado, etxtDireccionConsultado;
    private Spinner spinClientes;

    //Variable de Clase que nos permite invocar los metodos CRUD en SQLite
    private MySQLiteHelper db;

    //Variables de clase
    private ArrayList<Cliente> listaClientes;
    private ArrayAdapter spinnerAdaptador;
    private Cliente cliente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCrear = findViewById(R.id.btnRegistrar);
        btnVer = findViewById(R.id.btnConsultarComentario);
        btnEliminar = findViewById(R.id.btnEliminarCliente);
        btnDireccionGo =findViewById(R.id.btnIrDireccion);

        etxtNombreRegistrado = findViewById(R.id.etxtNombre);
        etxtDireccionRegistrado = findViewById(R.id.etxtDireccion);
        etxtNombreConsultado = findViewById(R.id.etxtNombreConsultado);
        etxtDireccionConsultado = findViewById(R.id.etxtDireccionConsultado);

        spinClientes = findViewById(R.id.spinListaClientes);
        //Asocio el evento onItemSelected con el spinner
        spinClientes.setOnItemSelectedListener(this);

        db = new MySQLiteHelper(this);
        //Traigo la lista de Clientes desde la BD
        cargarListaClientesEnSpinner();
    }

    private void cargarListaClientesEnSpinner(){
        //Traigo la lista de comentarios desde la bd
        listaClientes = db.obtenerClientes();

        //Cargo la lista en el control spinner
        spinnerAdaptador = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,listaClientes);
        spinClientes.setAdapter(spinnerAdaptador);
    }

    public void guardarCliente(View view){
            String nombre = etxtNombreRegistrado.getText().toString();
            String direccion = etxtDireccionRegistrado.getText().toString();
            db.insertar(nombre,direccion);
            //Traigo la lista de comentarios desde la bd
            cargarListaClientesEnSpinner();
            //Vacio los controles donde el usuario ingreso la informacion
            etxtNombreRegistrado.setText("");
            etxtDireccionRegistrado.setText("");
    }

    public void consultarCliente(View view){
        if(cliente !=null){
            etxtNombreConsultado.setText(cliente.getNombre());
            etxtDireccionConsultado.setText(cliente.getDireccion());
        }

    }

    public void eliminarCliente(View view){
        if(cliente !=null){
            db.eliminar(cliente.getId());
            cargarListaClientesEnSpinner();
            etxtDireccionConsultado.setText("");
            etxtNombreConsultado.setText("");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.spinListaClientes){
            if(listaClientes.size()>0){
                //Obtengo el elemento que se encuentra en la posicion seleccionada del Spinner
                cliente = listaClientes.get(position);
            }
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
