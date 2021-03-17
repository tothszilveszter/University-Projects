package model.validation;

import model.Client;
import model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientValidator {

    private static final int CNP_LENGTH = 13;


    public List<String> getErrors() {
        return errors;
    }

    private final List<String> errors;

    private final Client client;

    public ClientValidator(Client client) {
        errors=new ArrayList<>();
        this.client = client;
    }

    public boolean validateClient(){
        validateName(client.getName());
        validateCNP(client.getCNP());
        validateCardNumber(client.getCardNumber());
        validateAddress(client.getAddress());
        return errors.isEmpty();
    }

    private void validateAddress(String address) {
        if (address.equals(""))
            errors.add("Address must not be null");
    }

    private void validateCardNumber(String cardNumber) {
        if (cardNumber.equals(""))
            errors.add("Card number must not be null");
        if (!containsOnlyDigits(cardNumber))
            errors.add("Card number must contain only digits");
    }

    private void validateCNP(String cnp) {
        if (cnp.equals(""))
            errors.add("CNP must not be null");

        if (cnp.length() != CNP_LENGTH)
            errors.add("CNP must contain exactly " +CNP_LENGTH + " digits!");

        if (!containsOnlyDigits(cnp))
            errors.add("CNP must contain only digits");
    }

    private void validateName(String name) {
        if (name.equals(""))
            errors.add("Name must not be null");
        if (containsSpecialCharacter(name))
            errors.add("Name must not contain special characters");
        if (containsDigit(name))
            errors.add("Name must not contain numbers");
    }

    private boolean containsSpecialCharacter(String s) {
        if (s == null || s.trim().isEmpty()) {
            return false;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        return m.find();
    }

    private boolean containsDigit(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsOnlyDigits(String s) {
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }

}
