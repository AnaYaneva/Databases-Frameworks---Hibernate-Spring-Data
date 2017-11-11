package orm.strategies;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import entities.User;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DropCreateStrategy extends AbstractStrategy {
    private final String DROP_DATABASE_QUERY = "DROP DATABASE IF EXISTS `%s`;";
    private final String CREATE_DATABASE_QUERY = "CREATE DATABASE `%s`;";


    //public DropCreateStrategy(String dataSource, Connection connection) {
    //    this.dataSource = dataSource;
    //    this.connection = connection;
    //}

    public DropCreateStrategy() {
    }

    @Override
    public void execute() throws SQLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String dropQuery = String.format(DROP_DATABASE_QUERY, this.dataSource);
        this.connection.prepareStatement(dropQuery).execute();

        String createQuery = String.format(CREATE_DATABASE_QUERY, this.dataSource);
        this.connection.prepareStatement(createQuery).execute();

        List<Class> entities = getAllEntities();

        for (Class entity : entities) {
            this.doCreate(entity);
        }
    }
}