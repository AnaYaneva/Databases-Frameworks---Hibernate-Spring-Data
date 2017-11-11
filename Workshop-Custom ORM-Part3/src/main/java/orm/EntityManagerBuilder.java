package orm;

import orm.strategies.AbstractStrategy;
import orm.strategies.DropCreateStrategy;
import orm.strategies.SchemaInitializationStrategy;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class EntityManagerBuilder {
    private Connection connection;
    private String dataSource; //database name
    private AbstractStrategy strategy;

    public Connector configureConnectionString(){
        return new Connector(this);
    }

    public StrategyConfigurer configureCreationType(){
        return new StrategyConfigurer(this);
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public EntityManagerBuilder setDataSource(String dataSource) {
        this.dataSource = dataSource;

        return this;
    }

    public EntityManager build() throws SQLException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        this.strategy.setConnection(this.connection);
        this.strategy.setDataSource(this.dataSource);

        return new EntityManager(this.connection, this.dataSource, this.strategy);
    }

    public void setStrategy(AbstractStrategy strategy) {
        this.strategy = strategy;
    }
}
