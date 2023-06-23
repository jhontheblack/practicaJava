package com.example.practicajava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.practicajava.databinding.ActivityResultsRegistroBinding;

public class resultsRegistro extends AppCompatActivity {

    private ActivityResultsRegistroBinding binding;
    private Persona person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResultsRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundleIntent();
        asignarData();
    }


    private void getBundleIntent()
    {
        Bundle bundle=this.getIntent().getExtras();
        person=(Persona) bundle.getSerializable(messages.keyPersonIntent);
        Log.i("objetoPersonita",person.getNombres());
    }


    private void asignarData()
    {
        binding.Rcedula.setText(person.getCedula());
        binding.Rnombre.setText(person.getNombres());
        binding.Rfecha.setText(person.getFecha());
        binding.Rciudad.setText(person.getCiudad());
        binding.Rgenero.setText(person.getGenero());
        binding.Rcoreo.setText(person.getCorreo());
        binding.Rtelefono.setText(person.getTelefono());
    }

}