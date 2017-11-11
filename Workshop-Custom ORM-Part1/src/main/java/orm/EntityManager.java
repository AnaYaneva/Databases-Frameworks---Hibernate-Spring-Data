package orm;

import annotations.Column;
import annotations.Entity;
import annotations.Id;

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
        String query="INSERT INTO " + tableName + " (";
        String fieldNames="";
        String values="";

        Field[] fields=entity.getClass().getDeclaredFields();
        for (int i = 1; i <fields.length; i++) {
            Field current=fields[i];
            current.setAccessible(true);

            fieldNames+="`"+current.getAnnotation(Column.class).name()+"`";
            if (current.get(entity) instanceof Date){
                values+="'"+new SimpleDateFormat("yyyy-MM-dd").format(current.get(entity))+"'";
            }else {
                values += "'" + current.get(entity) + "'";
            }

            if (i<fields.length-1){
                fieldNames+=", ";
                values+=", ";
            }
        }
        query+=fieldNames+")"+" VALUES ("+values+");";
        return connection.prepareStatement(query).execute();
    }

    private String getTableName(Class entity){
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
}

























