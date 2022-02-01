package by.stepanovich.zkh.util;


public class FormValidator {

    private static final String CHECK_LOGIN_REGEX = "[A-Za-z0-9]{3,20}";

    private static final String CHECK_PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}";

    private static final String CHECK_FIRST_AND_LAST_NAME = "^[A-ZА-Я]{1}[a-zа-я]{2,20}$";

    private static final String CHECK_EMAIL_REGEX =
            "^(?=.{1,45}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String CHECK_PHONE = "^(\s*)?(\\+)?([- _():=+]?\\d[- _():=+]?){10,14}(\s*)?$";

    private FormValidator() {
    }

    public static class FormValidatorHolder {
        public static final FormValidator HOLDER_INSTANCE = new FormValidator();
    }

    public static FormValidator getInstance() {
        return FormValidator.FormValidatorHolder.HOLDER_INSTANCE;
    }


    public boolean checkLogin(String login) {
        return login != null && login.matches(CHECK_LOGIN_REGEX);
    }

    public boolean checkPassword(String password) {
        return password != null && password.matches(CHECK_PASSWORD_REGEX);
    }

    public boolean checkFirstName(String firstName) {
        return firstName != null && firstName.matches(CHECK_FIRST_AND_LAST_NAME);
    }

    public boolean checkLastName(String lastName) {
        return lastName != null && lastName.matches(CHECK_FIRST_AND_LAST_NAME);
    }

    public boolean checkPhone(String phone) {
        return phone != null && phone.matches(CHECK_PHONE);
    }

    public boolean checkEmail(String email) {
        return email != null && email.matches(CHECK_EMAIL_REGEX);
    }

}
