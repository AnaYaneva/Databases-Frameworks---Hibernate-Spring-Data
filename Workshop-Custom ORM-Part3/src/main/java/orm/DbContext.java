package orm;

import java.sql.SQLException;

public interface DbContext <E>{
    boolean persist(E entity) throws IllegalAccessException, SQLException;

    Iterable<E> find(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException;

    Iterable<E> find(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException;

    E findFirst(Class<E> table) throws SQLException, IllegalAccessException, InstantiationException;

    E findFirst(Class<E> table, String where) throws SQLException, IllegalAccessException, InstantiationException;
}
