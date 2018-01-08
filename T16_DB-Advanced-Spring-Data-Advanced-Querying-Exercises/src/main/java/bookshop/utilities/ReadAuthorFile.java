package bookshop.utilities;

import bookshop.entities.Author;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReadAuthorFile {

    public ReadAuthorFile() {
    }

    public List<Author> read(String filePath) throws IOException {
        List<Author> authors = new ArrayList<>();
        Path authorPath = Paths.get(filePath);

        for (String line : Files.readAllLines(authorPath).stream().skip(1).toArray(String[]::new)) {
            String[] tokens = line.split("\\s+");
            try {
                Constructor<Author> declaredConstructor = Author.class.getDeclaredConstructor();
                Author author = declaredConstructor.newInstance();

                author.setFirstName(tokens[0]);
                author.setLastName(tokens[1]);
                authors.add(author);
            } catch (NoSuchMethodException |
                    InvocationTargetException |
                    InstantiationException |
                    IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return authors;
    }
}

