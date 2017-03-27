package maffre.com.outerspacemanager.outerspacemanager;

/**
 * Created by maffreh on 21/03/2017.
 */
public class BuildingDB {
    private int level;
    private String name;
    private long timeToBuildByLevel;
    private long timeToBuildLevel0;
    private int id;
    private long currentDate;




    public BuildingDB(int level, String name, long timeToBuildByLevel, long timeToBuildLevel0, int id, long currentTime) {
        this.level = level;
        this.currentDate = currentTime;
        this.name = name;
        this.timeToBuildByLevel = timeToBuildByLevel;
        this.timeToBuildLevel0 = timeToBuildLevel0;
        this.id = id;
    }



    public BuildingDB() {
    }


    public long getCurrentDate() {
        return currentDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeToBuildByLevel() {
        return timeToBuildByLevel;
    }

    public void setTimeToBuildByLevel(int timeToBuildByLevel) {
        this.timeToBuildByLevel = timeToBuildByLevel;
    }

    public long getTimeToBuildLevel0() {
        return timeToBuildLevel0;
    }

    public void setTimeToBuildLevel0(int timeToBuildLevel0) {
        this.timeToBuildLevel0 = timeToBuildLevel0;
    }


    public void setCurrentDate(long currentDate) {
        this.currentDate = currentDate;
    }
}
