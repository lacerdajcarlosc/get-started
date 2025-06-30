package validator;

import exception.ValidatorException;
import model.UserModel;

import java.time.LocalDate;

public class UserValidator {

    private UserValidator(){

    }
    public static void verifyModel(final UserModel model) throws ValidatorException {
        if(model == null)
            throw new ValidatorException("Usuario Invalido!");

        if(stringIsBlank(model.getName()))
            throw new ValidatorException("Informe um nome valido.");

        if(model.getName().length() <= 1)
            throw new ValidatorException("O nome deve ter mais que 1 caracter.");

        if(stringIsBlank(model.getEmail()))
            throw new ValidatorException("Informe um e-mail valido!");

        if((!model.getEmail().contains("@")) || (!model.getEmail().contains(".")))
           throw new ValidatorException("Informe um e-mail valido");

        if (model.getBirthday() == null || model.getBirthday().isAfter(LocalDate.now()))
           throw new ValidatorException("Informe uma data de nascimento válida (no passado)");

    }

    private static boolean stringIsBlank(final String value){
        return value == null || value.isBlank();
    }
}

