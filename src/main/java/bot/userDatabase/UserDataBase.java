package bot.userDatabase;

import java.io.*;

public class UserDataBase {

    public void addUserToBase(String chatID) {
        String fileName = "id.txt";
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(fileName, true));
             BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            if (!recordIsPresent(bufferedReader, chatID)){
                bufferWriter.write(chatID + " \n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean recordIsPresent(BufferedReader reader, String id) throws IOException{
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(id))
                return true;
        }
        return false;
    }
}
