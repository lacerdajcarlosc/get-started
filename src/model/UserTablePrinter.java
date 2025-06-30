package model;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserTablePrinter {
    public static void printTable(List<UserModel> users){
        if(users == null || users.isEmpty()){
            System.out.println("Nenhum usuario encontrado.");
            return;
        }
        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.printf("%-5s %-20s %-25s %-12s %-5s%n", "ID", "Nome", "E-mail", "Nascimento", "Idade");
        System.out.println("=".repeat(75));

        for(UserModel user : users){
            String formattedBirthday = user.getBirthday() != null
                    ? user.getBirthday().format(formatter)
                    :"N/A";
            int age = user.getAge();
            System.out.printf(
                    "%-5d %-20s %-25s %-12s %-5d%n",
                    user.getId(),
                    user.getName(),
                    user.getEmail(),
                    formattedBirthday,
                    age
            );

        }
        System.out.println("=".repeat(75));



    }

}
