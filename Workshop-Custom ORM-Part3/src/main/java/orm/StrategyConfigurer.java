package orm;

import orm.strategies.DropCreateStrategy;
import orm.strategies.UpdateStrategy;

public class StrategyConfigurer {
    private EntityManagerBuilder entityManagerBuilder;

    public StrategyConfigurer(EntityManagerBuilder entityManagerBuilder) {
        this.entityManagerBuilder=entityManagerBuilder;
    }

    public EntityManagerBuilder setDropCreateStrategy(){
        DropCreateStrategy dropCreateStrategy=
                new DropCreateStrategy();

        this.entityManagerBuilder.setStrategy(dropCreateStrategy);

        return this.entityManagerBuilder;
    }

    public EntityManagerBuilder setUpdateStrategy(){
        UpdateStrategy updateStrategy=
                new UpdateStrategy();
        this.entityManagerBuilder.setStrategy(updateStrategy);

        return this.entityManagerBuilder;
    }
}
