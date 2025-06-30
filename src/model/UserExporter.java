package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserExporter {
    public static void exportToCsv(List<UserModel> users,String fileName) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            writer.write("ID;Nome;E-mail;Nascimento;Idade");
            writer.newLine();

            for(UserModel user : users){
                String line = String.format("%d;%s;%s;%s;%d",
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getBirthday().format(formatter),
                        user.getAge()
                );
                writer.write(line);
                writer.newLine();

            }
            System.out.println("Arquivo salvo com sucesso em: " + fileName);

        }catch (IOException e) {
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }

    }

}
