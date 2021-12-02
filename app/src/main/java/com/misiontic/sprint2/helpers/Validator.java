package com.misiontic.sprint2.helpers;

public class Validator {

    public Validator() {
    }

    public boolean confirmInputsRegister(String email, String pass_1, String pass_2){

        if(email.isEmpty() || pass_1.isEmpty() || pass_2.isEmpty()){

            return false;

        }else {
            return true;
        }

    }

    public boolean confirmInputsRegisterPass(String pass_1, String pass_2){

        if(pass_1.equals(pass_2)){

            return true;

        }else {
            return false;
        }

    }










}
