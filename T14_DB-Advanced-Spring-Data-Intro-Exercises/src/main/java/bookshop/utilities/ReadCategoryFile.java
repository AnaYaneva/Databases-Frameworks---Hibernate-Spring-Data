package bookshop.utilities;

import bookshop.entities.Author;
import bookshop.entities.Category;
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
public class ReadCategoryFile {
    public ReadCategoryFile() {
    }

    public List<Category> read(String filePath) throws IOException {
        List<Category> categories = new ArrayList<>();
        Path categoryPath = Paths.get(filePath);

        for (String line : Files.readAllLines(categoryPath).stream().skip(1).toArray(String[]::new)) {
            String[] tokens = line.split("\\s+");
            try {
                Constructor<Category> declaredConstructor = Category.class.getDeclaredConstructor();
                Category category = declaredConstructor.newInstance();

                category.setName(tokens[0]);
                categories.add(category);
            } catch (NoSuchMethodException |
                    InvocationTargetException |
                    InstantiationException |
                    IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return categories;
    }
}


