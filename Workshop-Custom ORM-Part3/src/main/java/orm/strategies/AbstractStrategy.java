package orm.strategies;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import entities.User;
import orm.scanner.EntityScanner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public abstract class AbstractStrategy implements SchemaInitializationStrategy {

    public String dataSource;
    public Connection connection;

    public void setConnection(Connection connection) {
        this.connection=connection;
    }

    public void setDataSource(String dataSource) {
        this.dataSource=dataSource;
    }


    public List<Class> getAllEntities() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return EntityScanner.getAllEntities(System.getProperty("user.dir"));
    }

    public boolean doCreate(Class entity) throws SQLException {
        String query = "CREATE TABLE "+this.dataSource+"." + this.getTableName(entity) + "( ";

        Field[] fields = entity.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field currentField = fields[i];
            currentField.setAccessible(true);

            query += " `" + currentField.getAnnotation(Column.class).name() + "` " + this.getDbType(currentField);

            if (currentField.isAnnotationPresent(Id.class)) {
                query += " PRIMARY KEY AUTO_INCREMENT ";
            }

            if (i < fields.length - 1) {
                query += ", ";
            }
        }
        query += ");";
        return connection.prepareStatement(query).execute();
    }

    public String getTableName(Class<?> entity) {
        String tableName = "";
        if (entity.isAnnotationPresent(Entity.class)) {
            Entity annotation = (Entity) entity.getAnnotation(Entity.class);
            tableName = annotation.name();
        }
        if (tableName.equals("")) {
            tableName = entity.getClass().getSimpleName();
        }
        return tableName;
    }

    public String getDbType(Field currentField) {
        currentField.setAccessible(true);
        switch (currentField.getType().getSimpleName()) {
            case "int":
            case "Integer":
                return " INT ";
            case "String":
                return " VARCHAR(150) ";
            case "Date":
                return " DATETIME ";
        }
        return "";
    }
}
