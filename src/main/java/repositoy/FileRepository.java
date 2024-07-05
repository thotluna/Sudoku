package repositoy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.Repository;
import dtos.GameDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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


    @Override
    public List<String> getListFileName() {
        Path path = Paths.get(DIRECTORY);
        List<String> filesName = new ArrayList<>();
        if (Files.exists(path)){
            File directory = new File(path.toUri());
            filesName.addAll(Arrays.stream(Objects.requireNonNull(directory.list())).toList());
        }
        return filesName;
    }

    @Override
    public boolean hasFile() {
        return !getListFileName().isEmpty();
    }

    @Override
    public GameDto load(String fileName) {
        String name = fileName.toLowerCase();
        Path path = Paths.get(DIRECTORY, name);

        if(!Files.exists(path)) return null;

        File file = path.toFile();
        StringBuilder data = new StringBuilder();
        try (Scanner sc = new Scanner(file)){

            while (sc.hasNext()){
                data.append(sc.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println(PREFIX_EXCEPTION + e);
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        GameDto game = null;

        try {
            game = mapper.readValue(data.toString(), GameDto.class);
        } catch (JsonProcessingException e) {
            System.out.println(PREFIX_EXCEPTION + e);
            e.printStackTrace();
        }

        return game;
    }
}
