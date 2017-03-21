package maffre.com.outerspacemanager.outerspacemanager;

import android.content.Context;

import java.util.UUID;
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
    private String imageUrl;
    private int id;






    public Building(int amountOfEffectByLevel, int amountOfEffectLevel, boolean building, String effect, int gasCostByLevel, int gasCostLevel, int level, int mineralCostByLevel, int mineralCostLevel, String name, int timeToBuildByLevel, int timeToBuildLevel0, String imageUrl) {
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
        this.imageUrl = imageUrl;
    }

    public Building(int id, String name, int timeToBuildLevel0, int timeToBuildByLevel, int level) {
        this.id = id;
        this.name = name;
        this.timeToBuildLevel0 = timeToBuildLevel0;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.level = level;
    }

    public Building() {

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

    public int getId() {
        return id;
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

    public String getImageUrl() { return imageUrl; }


    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAmountOfEffectByLevel(int amountOfEffectByLevel) {
        this.amountOfEffectByLevel = amountOfEffectByLevel;
    }

    public void setAmountOfEffectLevel(int amountOfEffectLevel) {
        this.amountOfEffectLevel = amountOfEffectLevel;
    }

    public void setBuilding(boolean building) {
        this.building = building;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setGasCostByLevel(int gasCostByLevel) {
        this.gasCostByLevel = gasCostByLevel;
    }

    public void setGasCostLevel(int gasCostLevel) {
        this.gasCostLevel = gasCostLevel;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMineralCostByLevel(int mineralCostByLevel) {
        this.mineralCostByLevel = mineralCostByLevel;
    }

    public void setMineralCostLevel(int mineralCostLevel) {
        this.mineralCostLevel = mineralCostLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimeToBuildByLevel(int timeToBuildByLevel) {
        this.timeToBuildByLevel = timeToBuildByLevel;
    }

    public void setTimeToBuildLevel0(int timeToBuildLevel0) {
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }
}
