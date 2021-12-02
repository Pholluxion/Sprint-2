package com.misiontic.sprint2.helpers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ValidatorTest {

    private Validator validator;

    @Before
    public void setUp() throws Exception {
        this.validator = new Validator();
    }

    @Test
    public void confirmInputsRegister() throws Exception{

        assertEquals(true,validator.confirmInputsRegister("emai@mail.com","123","123"));

    }

    @Test
    public void confirmInputsLogin()throws Exception {

        assertEquals(true,validator.confirmInputsLogin("emai@mail.com","123"));

    }

    @Test
    public void confirmInputsRegisterPass() throws Exception{
        assertEquals(false,validator.confirmInputsRegisterPass("321","123"));
    }
}