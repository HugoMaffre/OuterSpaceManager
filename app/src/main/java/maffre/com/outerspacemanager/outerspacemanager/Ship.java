package maffre.com.outerspacemanager.outerspacemanager;

/**
 * Created by mac2 on 14/03/2017.
 */

public class Ship {




    public Ship(String name, int gasCost, int life, int maxAttack, int minAttack, int mineralCost, int shipId, int shield, int spatioportLevelNeeded, int speed, int timeToBuild, int amount) {
        this.name = name;
        this.amount = amount;
        this.gasCost = gasCost;
        this.life = life;
        this.maxAttack = maxAttack;
        this.minAttack = minAttack;
        this.mineralCost = mineralCost;
        this.shipId = shipId;
        this.shield = shield;
        this.spatioportLevelNeeded = spatioportLevelNeeded;
        this.speed = speed;
        this.timeToBuild = timeToBuild;
    }

    public String name;
    public int gasCost;
    public int amount;
    public int life;
    public int maxAttack;
    public int minAttack;
    public int mineralCost;
    public int shipId;
    public int shield;
    public int spatioportLevelNeeded;
    public int speed;
    public int timeToBuild;





    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getGasCost() {
        return gasCost;
    }

    public int getLife() {
        return life;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public int getMineralCost() {
        return mineralCost;
    }

    public int getShipId() {
        return shipId;
    }

    public int getShield() {
        return shield;
    }

    public int getSpatioportLevelNeeded() {
        return spatioportLevelNeeded;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTimeToBuild() {
        return timeToBuild;
    }






}
