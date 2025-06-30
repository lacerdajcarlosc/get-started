package Program;

import dao.UserDAO;
import exception.EmptyStorageException;
import exception.UserNotFoundException;
import exception.ValidatorException;
import model.MenuOption;
import model.UserExporter;
import model.UserModel;
import model.UserTablePrinter;

import java.util.Scanner;

import static validator.UserValidator.verifyModel;

public class Main {

    private final static UserDAO dao = new UserDAO();
    private final static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        while (true) {
            System.out.println("Bem vindo ao cadastro de usuarios. Selecione a opcao desejada");
            System.out.println("1 - Cadastrar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Buscar por identificador");
            System.out.println("5 - Listar");
            System.out.println("6 - Sair");
            var userInput = scanner.nextInt();

            var selectedOption = MenuOption.values()[userInput - 1];
            switch (selectedOption) {
                case SAVE -> {
                    try {
                        var user = dao.save(requestToSave());
                        System.out.printf("Usuario cadastrado %n%s%n", user);
                    } catch (ValidatorException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }

                }
                case UPDATE -> {
                    try {
                        var user = dao.update(requestToUpdate());
                        System.out.printf("Usuario atualizado %n%s%n ", user);
                    } catch (UserNotFoundException | EmptyStorageException ex) {
                        System.out.println(ex.getMessage());
                    } catch (ValidatorException ex) {
                        System.out.println(ex.getMessage());
                        ex.printStackTrace();
                    }
                }
                case DELETE -> {
                    try {
                        dao.delete(requestToDelete());
                        System.out.println("Usuario excluido ");
                    } catch (UserNotFoundException | EmptyStorageException ex) {
                        System.out.println(ex.getMessage());
                    } finally {
                        System.out.println("===================================");
                    }
                }
                case FIND_BY_ID -> {
                    try {
                        var id = requestId();
                        var user = dao.findById(id);
                        System.out.printf("Usuario com id %s:%n", id);
                        System.out.println(user);
                    } catch (UserNotFoundException | EmptyStorageException ex) {
                        System.out.println(ex.getMessage());
                    }

                }
                case FIND_ALL -> {
                    try {
                        var users = dao.findAll();
                        System.out.println("Usuarios cadastrados");
                        System.out.println();
                        UserTablePrinter.printTable(users);

                        // Exporta para CSV
                        UserExporter.exportToCsv(users, "usuarios.csv");

                    } catch (UserNotFoundException | EmptyStorageException ex) {
                        System.out.println(ex.getMessage());
                    }


                }
                case EXIT -> System.exit(0);

            }

        }
    }

    private static long requestId() {
        System.out.println("Informe o identificador do usuario");
        return scanner.nextLong();
    }

    private static UserModel requestToSave() throws ValidatorException {
        scanner.nextLine();
        System.out.println("Informe o nome do usuario");
        var name = scanner.nextLine();
        System.out.println("Informe o e-mail do usuario");
        var email = scanner.nextLine();
        System.out.println("Informe a data de nascimento do usuario (dd/MM/yyyy)");
        var birthdayString = scanner.nextLine();

        return validateInputs(name, email, birthdayString);

    }


    private static UserModel validateInputs(final String name, final String email, final String birthdayString) throws ValidatorException {

        var user = new UserModel(0, name, email, birthdayString);
        verifyModel(user);
        return user;
    }


    private static UserModel requestToUpdate() throws ValidatorException {
        scanner.nextLine();
        System.out.println("Informe o identificador do usuario");
        var id = scanner.nextLong();
        System.out.println("Informe o nome do usuario");
        var name = scanner.next();
        System.out.println("Informe o e-mail do usuario");
        var email = scanner.next();
        System.out.println("Informe a data de nascimento do usuario (dd/MM/yyyy)");
        var birthdayString = scanner.next();

        var user = new UserModel(id, name, email, birthdayString);
        verifyModel(user);
        return user;
    }

    private static long requestToDelete() {
        System.out.println("Informe o identificador do usuario");
        return scanner.nextLong();

    }


}
