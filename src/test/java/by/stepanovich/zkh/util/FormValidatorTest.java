package by.stepanovich.zkh.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class FormValidatorTest {

    @Test
    public void checkPassword() {
        assertTrue(FormValidator.getInstance().checkPassword("Aaaaaaaaa8"));
    }

    @Test
    public void checkPasswordFalse() {
        assertFalse(FormValidator.getInstance().checkPassword("Aaa8"));
    }

    @Test
    public void checkFirstName() {
        assertTrue(FormValidator.getInstance().checkFirstName("Виктор"));
    }

    @Test
    public void checkFirstNameFalse() {
        assertFalse(FormValidator.getInstance().checkFirstName("Виктор5"));
    }

    @Test
    public void checkLastName() {
        assertTrue(FormValidator.getInstance().checkLastName("Бабанова"));
    }
    @Test
    public void checkLastNameFalse() {
        assertFalse(FormValidator.getInstance().checkLastName("Бабанова2"));
    }

    @Test
    public void checkPhone() {
        assertTrue(FormValidator.getInstance().checkPhone("+37525665863"));
    }
    @Test
    public void checkPhoneFalse() {
        assertFalse(FormValidator.getInstance().checkPhone("+3752566+"));
    }

    @Test
    public void checkEmail() {
        assertTrue(FormValidator.getInstance().checkEmail("mail@mail.ru"));
    }

    @Test
    public void checkEmailFalse() {
        assertFalse(FormValidator.getInstance().checkEmail("mailmail.ru"));
    }
}