package noam.app.hackeruapp;

/*
A class that represents the item object, holds data about what we display in List
*fullName
* level
* identified
 */
public class Item
{
    private String fullName;
    private int level;
    private boolean identified;

    public Item(String fullName, int level, boolean identified) {
        this.fullName = fullName;
        this.level = level;
        this.identified = identified;
    }

    public String getFullName() {
        return fullName;
    }

    public int getLevel() {
        return level;
    }

    public boolean isIdentified() {
        return identified;
    }

}
