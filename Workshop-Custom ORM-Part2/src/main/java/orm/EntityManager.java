package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;
import org.omg.PortableInterceptor.ServerRequestInfo;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class EntityManager<E> implements DbContext<E>{
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    public boolean persist(E entity) throws IllegalAccessException, SQLException {
        Field primary=this.getId(entity.getClass());
        primary.setAccessible(true);
        Object value=primary.get(entity);

        if (value==null||(Integer)value<=0 ){
            return this.doInsert(entity, primary);
        }

        return this.doUpdate(entity, primary);
    }

    public Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException {
        String query = "SELECT * FROM " + this.getTableName(table) + ";";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        List<E> result = new ArrayList<E>();

        while (rs.next()) {
            E entity = table.newInstance();
            this.fillEntity(table, rs, entity);
            result.add(entity);
        }


        return result;
    }

    public Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException {
        String query="SELECT * FROM "+this.getTableName(table)+" WHERE "+where +";";
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);

        List<E> result=new ArrayList<E>();

        while(rs.next()){
            E entity=table.newInstance();
            this.fillEntity(table,rs,entity);
            result.add(entity);
        }


        return result;
    }

    public E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException {
        String query="SELECT * FROM "+this.getTableName(table) +" LIMIT 1;";
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        rs.next();
        E entity=table.newInstance();
        this.fillEntity(table,rs,entity);

        return entity;
    }

    public E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException {
        String query="SELECT * FROM "+this.getTableName(table)+" WHERE "+where +" LIMIT 1;";
        Statement stmt=connection.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        rs.next();
        E entity=table.newInstance();
        this.fillEntity(table,rs,entity);

        return entity;
    }

    public void doDelete(Class<?> table, String criteria) throws Exception {
        String tableName=table.getAnnotation(Entity.class).name();
        if(!this.checkIfTableExists(tableName)){
            throw new Exception("Table dosn't exists!");
        }

        String query="DELETE FROM "+tableName+" WHERE "+criteria;
        this.connection.prepareStatement(query).execute();
    }

    private void fillEntity(Class table, ResultSet rs, E entity) throws SQLException, IllegalAccessException {
        Field[] fields=table.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field current=fields[i];
            current.setAccessible(true);
            this.fillField(current,rs,current.getAnnotation(Column.class).name(), entity);
        }
    }

    private void fillField(Field current, ResultSet rs, String name, Object entity) throws SQLException, IllegalAccessException {
        current.setAccessible(true);
        if (current.getType()==Integer.class||
                current.getType()==int.class) {
            current.set(entity, rs.getInt(name));
        } else if (current.getType()==String.class) {
            current.set(entity, rs.getString(name));
        } else if (current.getType()==Date.class) {
            current.set(entity, rs.getDate(name));
        }
    }

    private Field getId(Class entity){
        return Arrays.stream(entity.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(()->
                        new UnsupportedOperationException("Object does not have primary key"));
    }

    private boolean doUpdate(E entity, Field primary) throws IllegalAccessException, SQLException {
        String query="UPDATE " +this.getTableName(entity.getClass())+" SET ";
        String fieldNamesAndValues="";
        String where="";

        Field[] fields=entity.getClass().getDeclaredFields();
        for (int i = 0; i <fields.length; i++) {
            Field current = fields[i];
            current.setAccessible(true);

            if (current.getName().equals(primary.getName())) {
                where += " WHERE `" + current.getAnnotation(Column.class).name() + "` = " + current.get(entity) + ";";
                continue;
            } else {
                if (current.get(entity) instanceof Date) {
                    fieldNamesAndValues += "`" + current.getAnnotation(Column.class).name() + "` = '" + new SimpleDateFormat("yyyy-MM-dd").format(current.get(entity)) + "'";
                } else {
                    fieldNamesAndValues += "`" + current.getAnnotation(Column.class).name() + "` = '" + current.get(entity) + "'";
                }
            }

            if (i < fields.length - 1) {
                fieldNamesAndValues += ", ";
            }
        }
        query+=fieldNamesAndValues+where;
        return connection.prepareStatement(query).execute();
    }

    private boolean doInsert(E entity,Field primary) throws IllegalAccessException, SQLException {
        String tableName=this.getTableName(entity.getClass());

        if(!checkIfTableExists(tableName)){
            this.doCreate(entity.getClass());
        }

        String query="INSERT INTO `" + tableName + "` (";
        String fieldNames="";
        String values="";

        Field[] fields=entity.getClass().getDeclaredFields();
        for (int i = 1; i <fields.length; i++) {
            Field current=fields[i];
            current.setAccessible(true);

            Column columnAn=current.getAnnotation(Column.class);
            if(this.checkIfFieldExists(tableName,columnAn.name())){
                this.doAlter(tableName, current);
            }

            fieldNames+=" `"+current.getAnnotation(Column.class).name()+"` ";
            if (current.get(entity) instanceof Date){
                values+=" '"+new SimpleDateFormat("yyyy-MM-dd").format(current.get(entity))+"' ";
            }else {
                values += " '" + current.get(entity) + "' ";
            }

            if (i<fields.length-1){
                fieldNames+=", ";
                values+=", ";
            }
        }
        query+=fieldNames+")"+" VALUES ("+values+");";
        return connection.prepareStatement(query).execute();
    }

    private String getTableName(Class<?> entity){
        String tableName="";
        if (entity.isAnnotationPresent(Entity.class)){
            Entity annotation= (Entity) entity.getAnnotation(Entity.class);
            tableName=annotation.name();
        }
        if (tableName.equals("")){
            tableName=entity.getClass().getSimpleName();
        }
        return tableName;
    }

    private <E> void doCreate(Class entity) throws SQLException {
        String query="CREATE TABLE `"+this.getTableName(entity)+"`( ";

        Field[] fields=entity.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field currentField=fields[i];
            currentField.setAccessible(true);

            query+=" `"+currentField.getAnnotation(Column.class).name()+"` "+this.getDbType(currentField);

            if(currentField.isAnnotationPresent(Id.class)){
                query+=" PRIMARY KEY AUTO_INCREMENT ";
            }

            if (i<fields.length-1){
                query+=", ";
            }
        }
        query+=");";
        connection.prepareStatement(query).execute();
    }

    private String getDbType(Field currentField) {
        currentField.setAccessible(true);
        switch (currentField.getType().getSimpleName()){
            case "int":
            case "Integer":
                return " INT ";
            case "String":
                return " VARCHAR(50) ";
            case "Date":
                return " DATETIME ";
        }
        return "";
    }

    private boolean checkIfTableExists(String tableName) throws SQLException {
        String query="SELECT table_name FROM information_schema.tables WHERE table_schema = \'orm-db\' AND table_name='"+tableName+"' LIMIT 1;";
       ResultSet rs= this.connection.prepareStatement(query).executeQuery();

       if(!rs.next()){
           return false;
       }
       return true;
    }

    private <E> boolean checkIfFieldExists(String tableName, String fieldName) throws SQLException {
        String query="SELECT column_name FROM information_schema.columns WHERE table_schema = \'orm-db\' AND table_name='"+tableName+"' AND column_name = '"+fieldName+"';";
        ResultSet rs= this.connection.prepareStatement(query).executeQuery();

        if(!rs.next()){
            return false;
        }
        return true;
    }

    private void doAlter(String tableName, Field field) throws SQLException {
        String query="ALTER TABLE '"+tableName+"' ADD '"+field.getAnnotation(Column.class).name()+"' "+this.getDbType(field);
        this.connection.prepareStatement(query).execute();
    }

}
























