package com.example.practicajava;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.practicajava.databinding.ActivityRegistroPersonBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

public class registroPerson extends AppCompatActivity {

    private ActivityRegistroPersonBinding binding;
    private Persona person;
    private validators valid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistroPersonBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        evenClickEditTextDate();
        person=new Persona();
        valid=new validators();
        ObtenerInformacion();
        ObtenerBotonYAsignarEventoClick();
        ObtenerBotonLimpiar();
    }

    private void ObtenerBotonYAsignarEventoClick()
    {
        Button btnGuardar=binding.btnGuardar;
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (valid.getMessagesErros().size()>0)
                        AppearAndHide(true);
                ObtenerInformacion();
                AddErrorsAndDelete();


            }
        });
    }


    //asigna el evento al boton para limpiar campos
    private void ObtenerBotonLimpiar()
    {
        Button btnLimpiar=binding.btnLimpiar;
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    LimpiarCampos();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    private void ObtenerInformacion()
    {
        /*Persona person=new Persona();
        person.setCedula("12508083739");
        person.setNombres("Jhon Byron");
        person.setFecha("01/02/2017");
        person.setCiudad("Quevedo");
        person.setGenero("Hombre");
        person.setCorreo("jhonleturne19@hotmail.com");
        person.setTelefono("0988710070");
        validators valid=new validators(person);
        Log.i("jajajaJhon",valid.getMessagesErros().toString());
        Log.i("jajajaJhonBOOO",valid.getFormatValidate().toString());*/

        person.setNombres(binding.ETnombres.getText().toString());
        person.setCedula(binding.ETcedula.getText().toString());
        person.setFecha(binding.ETfecha.getText().toString());
        //person.setFecha("23/05/2023");
        person.setCiudad(binding.ETciudad.getText().toString());
        obtenerGeneroAsignar();
        person.setCorreo(binding.ETcorreo.getText().toString());
        person.setTelefono(binding.ETtelefono.getText().toString());
        valid=new validators(person);
        Log.i("jajajaJhon",person.getGenero());
        Log.i("jajajaJhon",valid.getMessagesErros().toString());
        Log.i("jajajaJhonBOOO",valid.getFormatValidate().toString());

    }


    private void AddErrorsAndDelete() {
        if (!valid.getFormatValidate() && valid.getMessagesErros().size()>0)
        {
            binding.errCedula.setText(getMessageForKey(messages.keyCedula));
            binding.errNombres.setText(getMessageForKey(messages.keyNombres));
            binding.errFecha.setText(getMessageForKey(messages.keyFecha));
            binding.errCiudad.setText(getMessageForKey(messages.keyCiudad));
            binding.errCorreo.setText(getMessageForKey(messages.keyCorreo));
            binding.errTelefono.setText(getMessageForKey(messages.keyTelefono));
            AppearAndHide(false);

        }else{
            //Inicia la ventana de resultados
            Intent intent=new Intent(this, resultsRegistro.class);
            Bundle b=new Bundle();
            b.putSerializable(messages.keyPersonIntent,person);
            intent.putExtras(b);
            startActivity(intent);
        }
    }


    //muestra o esconde los mensajes de error si no cumplen el formato.
    private void AppearAndHide(Boolean AppHid){
        if(valid.getMessagesErros().size()>0) {
            if (!getMessageForKey(messages.keyCedula).equals(messages.msgSuccessful))
                binding.errCedula.setVisibility(AppHid ? View.GONE : View.VISIBLE);
            if (!getMessageForKey(messages.keyNombres).equals(messages.msgSuccessful))
                binding.errNombres.setVisibility(AppHid ? View.GONE : View.VISIBLE);
            if (!getMessageForKey(messages.keyFecha).equals(messages.msgSuccessful))
                binding.errFecha.setVisibility(AppHid ? View.GONE : View.VISIBLE);
            if (!getMessageForKey(messages.keyCiudad).equals(messages.msgSuccessful))
                binding.errCiudad.setVisibility(AppHid ? View.GONE : View.VISIBLE);
            if (!getMessageForKey(messages.keyCorreo).equals(messages.msgSuccessful))
                binding.errCorreo.setVisibility(AppHid ? View.GONE : View.VISIBLE);
            if (!getMessageForKey(messages.keyTelefono).equals(messages.msgSuccessful))
                binding.errTelefono.setVisibility(AppHid ? View.GONE : View.VISIBLE);
        }
    }


    //obtiene el valor a travez de la llave pasada por parametro
    private String getMessageForKey(String key)
    {
        return valid.getMessagesErros().get(key);
    }

    //obtiene el genero de los radioButtons
    private void obtenerGeneroAsignar() {
        RadioGroup grupoGenero=binding.rdTipo;
        int idRBSelect=grupoGenero.getCheckedRadioButtonId();
        RadioButton rbGenero=findViewById(idRBSelect);
        person.setGenero(rbGenero.getText().toString());
    }

    //Limpia los campos de ingreso de textos
    private void LimpiarCampos() throws InterruptedException {
        if(valid.getMessagesErros().size()>0) {
            AppearAndHide(true);
            binding.ETnombres.setText("");
            binding.ETcedula.setText("");
            binding.ETfecha.setText("");
            binding.ETciudad.setText("");
            binding.ETcorreo.setText("");
            binding.ETtelefono.setText("");
        }
    }


    //create dateTimePicker event
    private void evenClickEditTextDate()
    {
        binding.ETfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    private void showDatePickerDialog()
    {
        DatePickerDialog datePickerDialog= null;
        Integer dia=0,mes=0,anio=0;
        //se ejecuta la parte verdadera del if si el sistema android es de 8.0 (OREO) para delante
        //  https://apilevels.com/
        // en el link se pueden ver los niveles de android en los cuales se puede guiar.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            dia=LocalDate.now().getDayOfMonth();
            mes=LocalDate.now().getMonthValue();
            anio=LocalDate.now().getYear();
            datePickerDialog=dialogDateTimePicker(dia,mes,anio);
            Log.i("telefonoj5","parte true");
        }else{
            Log.i("telefonoj5","parte false");
            Date allDate=new Date();
            dia=Integer.parseInt(new SimpleDateFormat("dd").format(allDate));
            mes=Integer.parseInt(new SimpleDateFormat("MM").format(allDate));
            anio=Integer.parseInt(new SimpleDateFormat("yyyy").format(allDate));

            datePickerDialog=dialogDateTimePicker(dia,mes,anio);
        }
       datePickerDialog.show();
    }


    private DatePickerDialog dialogDateTimePicker(int dia,int mes,int anio)
    {
        DatePickerDialog datePickerDialog=null;
        return datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Log.i("yearJhon",year+"");
                String DateInCad=dayOfMonth+"/"+month+"/"+year;
                binding.ETfecha.setText(formatDate(DateInCad));
            }
        },anio,mes-1 ,dia); //siempre va en año/mes/dia  si no se respeta al iniciar
                                   // aparece erroneo la fecha actual del datePicker
    }

    private String formatDate(String date)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(format.parse(date));
        }catch (Exception ex){
            Log.i("ErrorJhon",ex.getMessage());
        }
        return null;
    }

}