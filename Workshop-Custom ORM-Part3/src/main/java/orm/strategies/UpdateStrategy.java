package orm.strategies;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import entities.User;
import orm.strategies.SchemaInitializationStrategy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UpdateStrategy extends AbstractStrategy {


    @Override
    public void execute() throws SQLException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
       //getAllTables();
        List<Class> allEntities=getAllEntities();

        for (Class entity : allEntities) {
            Field primary=this.getId(entity);
            primary.setAccessible(true);

            updateTable(entity,primary);
        }
    }

    private Field getId(Class entity){
        return Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(()->
                        new UnsupportedOperationException("Object does not have primary key"));
    }

    private boolean updateTable(Class entity,Field primary) throws IllegalAccessException, SQLException {
        String tableName=this.getTableName(entity);
        if(!this.checkIfTableExists(tableName)){
            this.doCreate(entity);
        }

        Field[] fields=entity.getDeclaredFields();
        for (int i = 1; i <fields.length; i++) {
            Field current=fields[i];
            current.setAccessible(true);

            Column columnAn=current.getAnnotation(Column.class);
            if(this.checkIfFieldExists(tableName,columnAn.name())){
                this.doAlter(tableName, current);
            }
       }
        return false;
    }

    public void doDelete(Class<?> table, String criteria) throws Exception {
        String tableName=table.getAnnotation(Entity.class).name();
        if(!this.checkIfTableExists(tableName)){
            throw new Exception("Table dosn't exists!");
        }

        String query="DELETE FROM "+this.dataSource+"."+tableName+" WHERE "+criteria;
        this.connection.prepareStatement(query).execute();
    }

     private boolean checkIfTableExists(String tableName) throws SQLException {
        String query="SELECT table_name FROM information_schema.tables WHERE table_schema = '" +this.dataSource+"' AND table_name='"+tableName+"' LIMIT 1;";
        ResultSet rs= this.connection.prepareStatement(query).executeQuery();

        if(!rs.next()){
            return false;
        }
        return true;
    }

    private <E> boolean checkIfFieldExists(String tableName, String fieldName) throws SQLException {
        String query="SELECT column_name FROM information_schema.columns WHERE table_schema = '"+this.dataSource+"' AND table_name='"+tableName+"' AND column_name = '"+fieldName+"';";
        ResultSet rs= this.connection.prepareStatement(query).executeQuery();

        if(!rs.next()){
            return false;
        }
        return true;
    }

    private void doAlter(String tableName, Field field) throws SQLException {
        String query="ALTER TABLE "+this.dataSource+"."+tableName+" ADD '"+field.getAnnotation(Column.class).name()+"' "+this.getDbType(field);
        this.connection.prepareStatement(query).execute();
    }
}
