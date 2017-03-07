package maffre.com.outerspacemanager.outerspacemanager;

/**
 * Created by mac2 on 07/03/2017.
 */

public class Building {

    private int amountOfEffectByLevel;
    private int amountOfEffectLevel;
    private boolean building;
    private String effect;
    private int gasCostByLevel;
    private int gasCostLevel;
    private int level;
    private int mineralCostByLevel;
    private int mineralCostLevel;
    private String name;
    private int timeToBuildByLevel;
    private int timeToBuildLevel0;







    public Building(int amountOfEffectByLevel, int amountOfEffectLevel, boolean building, String effect, int gasCostByLevel, int gasCostLevel, int level, int mineralCostByLevel, int mineralCostLevel, String name, int timeToBuildByLevel, int timeToBuildLevel0) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
        this.amountOfEffectLevel = amountOfEffectLevel;
        this.building = building;
        this.effect = effect;
        this.gasCostByLevel = gasCostByLevel;
        this.gasCostLevel = gasCostLevel;
        this.level = level;
        this.mineralCostByLevel = mineralCostByLevel;
        this.mineralCostLevel = mineralCostLevel;
        this.name = name;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }




    public int getAmountOfEffectByLevel() {
        return amountOfEffectByLevel;
    }

    public int getAmountOfEffectLevel() {
        return amountOfEffectLevel;
    }

    public boolean isBuilding() {
        return building;
    }

    public String getEffect() {
        return effect;
    }

    public int getGasCostByLevel() {
        return gasCostByLevel;
    }

    public int getGasCostLevel() {
        return gasCostLevel;
    }

    public int getLevel() {
        return level;
    }

    public int getMineralCostByLevel() {
        return mineralCostByLevel;
    }

    public int getMineralCostLevel() {
        return mineralCostLevel;
    }

    public String getName() {
        return name;
    }

    public int getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public int getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }



}
