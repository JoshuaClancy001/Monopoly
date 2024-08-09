package Main;

import java.util.ArrayList;
import java.util.Objects;


public class Property {



    public static String toString(ArrayList<Property> game_square) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-7s %-10s %-8s %-8s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s\n",
                "Name", "Owned", "Color", "Cost", "Rent", "1 House Rent", "2 House Rent", "3 House Rent", "4 House Rent",
                "Hotel Rent", "House Cost", "Mortgage Price", "Unmortgage Price", "Rent Doubled", "Num Houses", "Num Hotels",
                "Main.Property Mortgaged", "Original Rent"));
        sb.append("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for (Property property : game_square) {
            if (property.name.equals("Nothing")){

            }
            else{
                sb.append(property.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        if (cost == 0 || name.equals("Go") || color_group.equals("0")) {
            return String.format("%-20s %-8s %-10s %-8s %-8s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s",
                    name, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        } else {
            return String.format("%-20s %-7b %-10s %-8d %-8d %-15d %-15d %-15d %-15d %-15d %-15d %-15d %-15d %-15b %-15d %-15d %-15b %-15d",
                    name, owned,
                    (!color_group.equals("0") ? color_group : ""),
                    (cost != 0 ? cost : 0),
                    (rent != 0 ? rent : 0),
                    (one_house_rent != 0 ? one_house_rent : 0),
                    (two_house_rent != 0 ? two_house_rent : 0),
                    (three_house_rent != 0 ? three_house_rent : 0),
                    (four_house_rent != 0 ? four_house_rent : 0),
                    (hotel_rent != 0 ? hotel_rent : 0),
                    (house_cost != 0 ? house_cost : 0),
                    (mortgage_price != 0 ? mortgage_price : 0),
                    (unmortgage_price != 0 ? unmortgage_price : 0),
                    rent_doubled,
                    (num_houses != 0 ? num_houses : 0),
                    (num_hotels != 0 ? num_hotels : 0),
                    property_is_mortgaged,
                    (original_rent != 0 ? original_rent : 0));
        }
    }

    public boolean isColorGroup(Player player, Property property, ArrayList<Property> game_square){

        for (Property i : game_square){
            if (i.color_group.equals(property.color_group)){
                if (!player.getProperties().contains(i)){
                    return false;
                }
            }
        }
        return true;
    }

    public void updatePropertiesRent(Player player, Property property, ArrayList<Property> game_square){
        if (!isColorGroup(player, property, game_square)){
            return;
        }
        for (Property i : game_square){
            if (i.color_group.equals(property.color_group)){
                i.rent *= 2;
                i.rent_doubled = true;
            }
        }
    }

    public void updateRailroads(Player player){
        int numRailroadsOwned = 0;
        for (Property playerProperties : player.getProperties()){
            switch (playerProperties.name){
                case "Reading Railroad", "B&O Railroad", "Pennsylvania Railroad", "Short Line" -> numRailroadsOwned += 1;
            }
        }
        switch (numRailroadsOwned){
            case 1 -> {
                for (Property playerProperties : player.getProperties()){
                    switch (playerProperties.name){
                        case "Reading Railroad", "B&O Railroad", "Pennsylvania Railroad", "Short Line" -> playerProperties.rent = 25;
                    }
                }
            }
            case 2 -> {
                for (Property playerProperties : player.getProperties()){
                    switch (playerProperties.name){
                        case "Reading Railroad", "B&O Railroad", "Pennsylvania Railroad", "Short Line" -> playerProperties.rent = 50;
                    }
                }
            }
            case 3 -> {
                for (Property playerProperties : player.getProperties()){
                    switch (playerProperties.name){
                        case "Reading Railroad", "B&O Railroad", "Pennsylvania Railroad", "Short Line" -> playerProperties.rent = 100;
                    }
                }
            }
            case 4 -> {
                for (Property playerProperties : player.getProperties()){
                    switch (playerProperties.name){
                        case "Reading Railroad", "B&O Railroad", "Pennsylvania Railroad", "Short Line" -> playerProperties.rent = 200;
                    }
                }
            }
        }
    }



    public static ArrayList<Property> game_square(){

        ArrayList<Property> game_square = new ArrayList<Property>();

        game_square.add(new Property("Nothing", false, "Nothing",
                0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Mediterranean", false, "Purple",
                60, 2, 10, 30,
                90, 160, 250,
                50, 30, 33,
                false, 0, 0,
                false, 60));

        game_square.add(new Property("Community Chest", false, "0",
                0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Baltic", false, "Purple",
                60, 4, 8, 60,
                180, 320, 450,
                50, 30, 33,
                false, 0, 0,
                false, 4));

        game_square.add(new Property("Income Tax", false, "0",
                0, 200, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 200));

        game_square.add(new Property("Reading Railroad", false, "Black",
                200, 0, 25, 50,
                100, 200, 0,
                0, 100, 110,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Oriental", false, "Light Blue",
                100, 6, 30, 90,
                270, 400, 550,
                50, 50, 55,
                false, 0, 0,
                false, 6));

        game_square.add(new Property("Chance", false, "0",
                0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Vermont", false, "Light Blue",
                100, 6, 30, 90,
                270, 400, 550,
                50, 50, 55,
                false, 0, 0,
                false, 6));

        game_square.add(new Property("Connecticut", false, "Light Blue",
                120, 8, 40, 100,
                300, 450, 600,
                50, 60, 66,
                false, 0, 0,
                false, 8));

        game_square.add(new Property("Jail", false, "0",
                0, 50, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 50));

        game_square.add(new Property("St. Charles Place", false, "Hot Pink",
                140, 10, 50, 150,
                450, 625, 750,
                100, 70, 77,
                false, 0, 0,
                false, 10));

        game_square.add(new Property("Electric Company", false, "Utilities",
                150, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("States", false, "Hot Pink",
                140, 10, 50, 150,
                450, 625, 750,
                100, 70, 77,
                false, 0, 0,
                false, 10));

        game_square.add(new Property("Virgina", false, "Hot Pink",
                160, 12, 60, 180,
                500, 700, 900,
                100, 80, 88,
                false, 0, 0,
                false, 12));

        game_square.add(new Property("Pennsylvania Railroad", false, "Black",
                200, 0, 25, 50,
                100, 200, 0,
                0, 100, 110,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("St. James Place", false, "Orange",
                180, 14, 70, 200,
                550, 750, 950,
                100, 90, 99,
                false, 0, 0,
                false, 14));

        game_square.add(new Property("Community Chest", false, "0",
                0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Tennessee", false, "Orange",
                180, 14, 70, 200,
                550, 750, 950,
                100, 90, 99,
                false, 0, 0,
                false, 14));

        game_square.add(new Property("New York", false, "Orange",
                200, 16, 80, 220,
                600, 800, 1000,
                100, 100, 110,
                false, 0, 0,
                false, 16));

        game_square.add(new Property("Free Parking", false, "0",
                500, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Kentucky", false, "Red",
                220, 18, 90, 250,
                700, 875, 1050,
                150, 110, 121,
                false, 0, 0,
                false, 18));

        game_square.add(new Property("Chance", false, "0",
                0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Indiana", false, "Red",
                220, 18, 90, 250,
                700, 875, 1050,
                150, 110, 121,
                false, 0, 0,
                false, 18));

        game_square.add(new Property("Illinois", false, "Red",
                240, 20, 100, 300,
                750, 925, 1100,
                150, 120, 132,
                false, 0, 0,
                false, 20));

        game_square.add(new Property("B&O Railroad", false, "Black",
                200, 0, 25, 50,
                100, 200, 0,
                0, 100, 110,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Atlantic", false, "Yellow",
                260, 22, 110, 330,
                800, 975, 1150,
                150, 130, 143,
                false, 0, 0,
                false, 22));

        game_square.add(new Property("Ventnor", false, "Yellow",
                260, 22, 110, 330,
                800, 975, 1150,
                150, 130, 143,
                false, 0, 0,
                false, 22));

        game_square.add(new Property("Water Works", false, "Utilities",
                150, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Marvin Gardens", false, "Yellow",
                280, 24, 110, 330,
                800, 975, 1150,
                150, 130, 143,
                false, 0, 0,
                false, 24));

        game_square.add(new Property("Go To Jail", false, "0",
                0, 50, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 50));

        game_square.add(new Property("Pacific", false, "Green",
                300, 26, 130, 390,
                900, 1100, 1275,
                200, 150, 165,
                false, 0, 0,
                false, 26));

        game_square.add(new Property("North Carolina", false, "Green",
                300, 26, 130, 390,
                900, 1100, 1275,
                200, 150, 165,
                false, 0, 0,
                false, 26));

        game_square.add(new Property("Community Chest", false, "0",
                0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Pennsylvania", false, "Green",
                320, 28, 150, 450,
                1000, 1200, 1400,
                200, 160, 176,
                false, 0, 0,
                false, 28));

        game_square.add(new Property("Short Line", false, "Black",
                200, 0, 25, 50,
                100, 200, 0,
                0, 100, 110,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Chance", false, "0",
                0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 0));

        game_square.add(new Property("Park Place", false, "Dark Blue",
                350, 35, 175, 500,
                1100, 1300, 1500,
                200, 175, 193,
                false, 0, 0,
                false, 35));

        game_square.add(new Property("Luxury Tax", false, "0",
                75, 0, 0, 0,
                0, 0, 0,
                0, 0, 0,
                false, 0, 0,
                false, 75));

        game_square.add(new Property("Boardwalk", false, "Dark Blue",
                400, 50, 200, 600,
                1400, 1700, 2000,
                200, 175, 193,
                false, 0, 0,
                false, 50));

        game_square.add(new Property("Go",false,"None",
                200,0,0,0,
                0,0,0,
                0,0,0,
                false,0,0,
                false,0));

        return game_square;
    }


   private String name;
    private boolean owned;
    private String color_group;
    private int cost;
    private int rent;
    private int one_house_rent;
    private int two_house_rent;
    private int three_house_rent;
    private int four_house_rent;
    private int hotel_rent;
    private int house_cost;
    private int mortgage_price;
    private int unmortgage_price;
    private boolean rent_doubled;
    private int num_houses;
    private int num_hotels;
    private boolean property_is_mortgaged;
    private int original_rent;

    public Property(   String name,boolean owned,String color_group,int cost,
                int rent,int one_house_rent,int two_house_rent,int three_house_rent,
                int four_house_rent,int hotel_rent,int house_cost,int mortgage_price,
                int unmortgage_price,boolean rent_doubled,int num_houses,int num_hotels,
                boolean property_is_mortgaged,int original_rent){

        this.name = name;
        this.owned = owned;
        this.color_group = color_group;
        this.cost = cost;
        this.rent = rent;
        this.one_house_rent = one_house_rent;
        this.two_house_rent = two_house_rent;
        this.three_house_rent = three_house_rent;
        this.four_house_rent = four_house_rent;
        this.hotel_rent = hotel_rent;
        this.house_cost = house_cost;
        this.mortgage_price = mortgage_price;
        this.unmortgage_price = unmortgage_price;
        this.rent_doubled = rent_doubled;
        this.num_houses = num_houses;
        this.num_hotels = num_hotels;
        this.property_is_mortgaged = property_is_mortgaged;
        this.original_rent = original_rent;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public String getColorGroup() {
        return color_group;
    }

    public void setColorGroup(String color_group) {
        this.color_group = color_group;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getOneHouseRent() {
        return one_house_rent;
    }
    public int getTwoHouseRent() {
        return two_house_rent;
    }

    public int getThreeHouseRent() {
        return three_house_rent;
    }

    public int getFourHouseRent() {
        return four_house_rent;
    }

    public int getHotelRent() {
        return hotel_rent;
    }

    public int getHouseCost() {
        return house_cost;
    }

    public int getMortgagePrice() {
        return mortgage_price;
    }

    public int getUnmortgagePrice() {
        return unmortgage_price;
    }

    public boolean isRentDoubled() {
        return rent_doubled;
    }

    public void setRentDoubled(boolean rent_doubled) {
        this.rent_doubled = rent_doubled;
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

    public boolean isPropertyMortgaged() {
        return property_is_mortgaged;
    }

    public void isPropertyMortgaged(boolean property_is_mortgaged) {
        this.property_is_mortgaged = property_is_mortgaged;
    }

    public int getOriginalRent() {
        return original_rent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property property = (Property) o;
        return owned == property.owned && cost == property.cost && rent == property.rent && one_house_rent == property.one_house_rent && two_house_rent == property.two_house_rent && three_house_rent == property.three_house_rent && four_house_rent == property.four_house_rent && hotel_rent == property.hotel_rent && house_cost == property.house_cost && mortgage_price == property.mortgage_price && unmortgage_price == property.unmortgage_price && rent_doubled == property.rent_doubled && num_houses == property.num_houses && num_hotels == property.num_hotels && property_is_mortgaged == property.property_is_mortgaged && original_rent == property.original_rent && Objects.equals(name, property.name) && Objects.equals(color_group, property.color_group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, owned, color_group, cost, rent, one_house_rent, two_house_rent, three_house_rent, four_house_rent, hotel_rent, house_cost, mortgage_price, unmortgage_price, rent_doubled, num_houses, num_hotels, property_is_mortgaged, original_rent);
    }




}
