package com.example.practicajava;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class validators {

    private Persona person;
    private Map<String,String> messagesErros;
    private Boolean formatValidate;


    public validators(){
        setFormatValidate(false);
        messagesErros=new HashMap<>();
    }

    public validators(Persona person){
        setFormatValidate(false);
        messagesErros=new HashMap<>();
        this.person=person;
        ParametersCapitalLetters();
        validateCedula();
        validateEmail();
        validateTelefono();
        validateFecha();
        ComplyFormat();
    }

    private void ParametersCapitalLetters()
    {
        if(person.getNombres().length()!=0) {
            person.setNombres(person.getNombres().toUpperCase());
            keysAndValue(messages.keyNombres,messages.msgSuccessful);
        }else
            keysAndValue(messages.keyNombres,messages.msgAllProperties);

        if(person.getCiudad().length()!=0)
        {
            person.setCiudad(person.getCiudad().toUpperCase());
            keysAndValue(messages.keyCiudad,messages.msgSuccessful);
        }else
            keysAndValue(messages.keyCiudad,messages.msgAllProperties);
    }

    private void validateCedula()
    {
        if(person.getCedula().length()<10)
            keysAndValue(messages.keyCedula,messages.msgCedula);
        else if(person.getCedula().length()==0)
            keysAndValue(messages.keyCedula,messages.msgAllProperties);
        else
            keysAndValue(messages.keyCedula,messages.msgSuccessful);
    }


    private void validateEmail()
    {
        String valiEmail="^(([^<>()\\[\\]\\\\.,;:\\s@”]+(\\.[^<>()\\[\\]\\\\.,;:\\s@”]+)*)|(“.+”))@((\\[[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}\\.[0–9]{1,3}])|(([a-zA-Z\\-0–9]+\\.)+[a-zA-Z]{2,}))$";
        if(!person.getCorreo().matches(valiEmail))
            keysAndValue(messages.keyCorreo,messages.msgCorreo);
        else
            keysAndValue(messages.keyCorreo,messages.msgSuccessful);
    }

    private void validateTelefono()
    {
        String vali="^09\\d{8}$";
        Log.i("telefono",person.getTelefono()+" este telefon");
        if(!person.getTelefono().matches(vali))
            keysAndValue(messages.keyTelefono,messages.msgTelefono);
        else
            keysAndValue(messages.keyTelefono,messages.msgSuccessful);
    }


    private void validateFecha()
    {
        String valiFecha="^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$";
        if(!person.getFecha().matches(valiFecha))
            keysAndValue(messages.keyFecha,messages.msgFecha);
        else
            keysAndValue(messages.keyFecha,messages.msgSuccessful);
    }

    //valida que todas los campos sean correctos y asigna true a format validate
    public void ComplyFormat()
    {
        if(messagesErros.get(messages.keyNombres).equals(messages.msgSuccessful) &&
           messagesErros.get(messages.keyCorreo).equals(messages.msgSuccessful) &&
           messagesErros.get(messages.keyFecha).equals(messages.msgSuccessful) &&
           messagesErros.get(messages.keyTelefono).equals(messages.msgSuccessful) &&
           messagesErros.get(messages.keyCiudad).equals(messages.msgSuccessful) &&
           messagesErros.get(messages.keyCedula).equals(messages.msgSuccessful))
            setFormatValidate(true);
    }


    public Boolean getFormatValidate()
    {
        return formatValidate;
    }

    private void setFormatValidate(Boolean formatValidate)
    {
        this.formatValidate=formatValidate;
    }

    private void keysAndValue(String key,String value)
    {
        messagesErros.put(key,value);
    }

    public Map<String,String> getMessagesErros()
    {
        return messagesErros;
    }

}
