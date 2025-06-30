package model;

import exception.ValidatorException;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class UserModel {
    private long id;
    private String name;
    private String email;
    private LocalDate birthday;


    public UserModel(long id, String name, String email, String birthday) throws ValidatorException {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthday = parseBirthday(birthday);

    }
    private LocalDate parseBirthday(String input) throws ValidatorException {
        if (input == null || input.isBlank()) {
            throw new ValidatorException("Informe uma data de nascimento");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(input, formatter);

            if (parsedDate.isAfter(LocalDate.now())) {
                throw new ValidatorException("A data de nascimento não pode estar no futuro.");
            }

            if (parsedDate.isBefore(LocalDate.of(1900, 1, 1))) {
                throw new ValidatorException("A data de nascimento é muito antiga.");
            }
            return parsedDate;

        } catch (DateTimeParseException e) {
            throw new ValidatorException("Data inválida. Use o formato dd/MM/yyyy");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
    public int getAge(){
        if(birthday == null)return -1;
        return Period.between(birthday,LocalDate.now()).getYears();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                Objects.equals(name, userModel.name) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(birthday, userModel.birthday);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, birthday);
    }
    @Override
    public String toString() {
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedBirthday = birthday != null ? birthday.format(formater) : "N/A";
        int age = getAge();
        return String.format("""
            ┌───────────── Usuário ─────────────┐
            │ ID      : %d
            │ Nome    : %s
            │ E-mail  : %s
            │ Nasc.   : %s
            │ Idade   : %d anos
            └───────────────────────────────────┘
            """, id, name, email, formattedBirthday, age);
    }
}
