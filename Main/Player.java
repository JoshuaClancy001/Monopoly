package Main;

import java.util.ArrayList;



public class Player {

    private String name;
    private int total_money;
    private int position;
    private ArrayList<Property> properties;
    private boolean get_out_of_jail;
    private boolean in_jail;
    private int num_houses;
    private int num_hotels;
    private int jail_count = 0;

    public int getJailCount() {
        return jail_count;
    }

    public void incrementJailCount() {
        this.jail_count += 1;
    }
    public void resetJailCount(){
        this.jail_count = 0;
    }

    public Player(String name, int total_money, int position, ArrayList<Property> properties,
                  boolean get_out_of_jail, boolean in_jail, int num_houses, int num_hotels){

        this.name = name;
        this.total_money = total_money;
        this.position = position;
        this.properties = properties;
        this.get_out_of_jail = get_out_of_jail;
        this.in_jail = in_jail;
        this.num_houses = num_houses;
        this.num_hotels = num_hotels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal_money() {
        return total_money;
    }

    public void addTotalMoney(int added_money) {
        this.total_money += added_money;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property){
        this.properties.add(property);
        property.setOwned(true);
    }

    public void removeProperty(Property property){
        this.properties.remove(property);
        property.setOwned(false);
    }

    public boolean isGetOutOfJail() {
        return get_out_of_jail;
    }

    public void setGetOutOfJail(boolean get_out_of_jail) {
        this.get_out_of_jail = get_out_of_jail;
    }

    public boolean isInJail() {
        return in_jail;
    }

    public void setInJail(boolean in_jail) {
        this.in_jail = in_jail;
    }

    public int getNumHouses() {
        return num_houses;
    }

    public void setNumHouses(int num_houses) {
        this.num_houses = num_houses;
    }

    public int getNumHotels() {
        return num_hotels;
    }

    public void setNumHotels(int num_hotels) {
        this.num_hotels = num_hotels;
    }
}


