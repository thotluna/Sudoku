package repositoy;

import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.Repository;
import dtos.GameDto;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileRepository implements Repository {

    private static final String DIRECTORY = "c:\\saved";
    private static final String EXT = ".json";

    private static final String PREFIX_EXCEPTION = "Exception: ";


    @Override
    public void save(GameDto dto) {

        createIfNotExitDirectory();

        String nameFile = DIRECTORY + "\\" + System.currentTimeMillis() + EXT;
        Path path = Paths.get(nameFile);

        try {
            Files.createFile(path);

            ObjectMapper mapper = new ObjectMapper();
            String gameString = mapper.writeValueAsString(dto);
            writeGame(path, gameString);

        } catch (IOException e) {
            System.out.println(PREFIX_EXCEPTION + e);
            e.printStackTrace();
        }

    }

    private void createIfNotExitDirectory() {
        Path directory = Paths.get(DIRECTORY);
        if (!Files.exists(directory)){
            try {
                Files.createDirectory(directory);
            }catch (Exception e){
                System.out.println(PREFIX_EXCEPTION + e);
                e.printStackTrace();
            }
        }
    }

    private static void writeGame(Path path, String gameString) throws IOException {
        try(FileWriter fw = new FileWriter(path.toFile())){

            fw.append(gameString);
        }catch (IOException e){
            System.out.println(PREFIX_EXCEPTION + e);
            e.printStackTrace();
        }
    }

}
