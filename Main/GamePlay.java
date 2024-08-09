package Main;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class GamePlay {

    public static void manage_properties(Player player) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("What would you like to do?\n1) Review\n2) Build\n3) Mortgage\n4) Unmortgage\n");
        String action = scanner.nextLine();

        switch (action) {
            case "1" -> {
                System.out.println("Printing Properties");
                if (player.getProperties().isEmpty()) {
                    System.out.println("You don't own any properties");
                }
                for (Property property : player.getProperties()) {
                    System.out.print(property.getName() + "   ");
                    if (property.isPropertyMortgaged()) {
                        System.out.println("Mortgaged");
                    } else {
                        System.out.println("Unmortgaged");
                    }
                }
            }
            case "2" -> {
                Property curr_property = null;
                boolean prop_found = false;
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Building");
                while (!prop_found) {
                    System.out.println("Which Property?(Press Enter to Go Back)");
                    String which_property = scanner2.nextLine();
                    for (Property property : player.getProperties()) {
                        if (which_property.equalsIgnoreCase(property.getName())) {
                            prop_found = true;
                            curr_property = property;
                            break;
                        } else if (which_property.isEmpty()) {
                            System.out.println("Enter Something");
                        }
                    }
                    System.out.println("1) House\n2) Hotel");
                    String house_hotel = scanner2.nextLine();
                    build_on_prop(house_hotel, scanner2, player, curr_property);
                }
            }
            case "3" -> System.out.println("Mortgaging");
            case "4" -> System.out.println("Unmortgaging");
        }

    }

    private static void build_on_prop(String house_hotel, Scanner scanner2,Player player, Property curr_property) {
        if (house_hotel.equals("1") || house_hotel.equalsIgnoreCase("House")) {
            build_house(scanner2 , player, curr_property);
        }
        else if (house_hotel.equals("2") || house_hotel.equalsIgnoreCase("Hotel")) {
            System.out.println("Building Hotel");
        }
    }

    private static void build_house(Scanner scanner2, Player player, Property curr_property) {
        System.out.println("How Many Houses Do You Want?");
        int how_many_houses = scanner2.nextInt();
        if ( curr_property.getNumHouses() + how_many_houses > 4){
            System.out.println("You can't build that many houses");
        }
        else if (curr_property.getNumHouses() + how_many_houses == 5){
            System.out.println("Build a Hotel instead");
        }
        else {
            curr_property.setNumHouses(curr_property.getNumHouses() + how_many_houses);
            int playerMoney = player.getTotal_money();
            int num_houses_on_prop = curr_property.getNumHouses();
            player.addTotalMoney(playerMoney - (curr_property.getNumHouses() * how_many_houses));
            if (num_houses_on_prop == 1){
                curr_property.setRent(curr_property.getOneHouseRent());
            }
            else if (num_houses_on_prop == 2){
                curr_property.setRent(curr_property.getTwoHouseRent());
            }
            else if (num_houses_on_prop == 3){
                curr_property.setRent(curr_property.getThreeHouseRent());
            }
            else if (num_houses_on_prop == 4){
                curr_property.setRent(curr_property.getFourHouseRent());
            }
        }

        System.out.println("Building " + how_many_houses + "houses");
    }
    public static void jailBeginningActions(int dice1, int dice2, ArrayList<Property> gameSquare, Player player) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("You are in jail, what would you like to do?");
            System.out.println("1) Use Get Out Of Jail Free Card");
            System.out.println("2) Pay $50 To Get Out Of Jail");
            System.out.println("3) Press Enter to Roll");
            String action = scanner.nextLine();
            switch (action){
                case "1":
                    if(player.isGetOutOfJail()){
                        player.setInJail(false);
                        player.setGetOutOfJail(false);
                        player.resetJailCount();
                    }
                    else {
                        System.out.println("You Do Not Have A Get Out Of Jail Free Card");
                    }
                    return;
                case "2":
                    player.addTotalMoney(-50);
                    for (Property property : gameSquare){
                        if (property.getName().equals("Free Parking")){
                            property.setCost(property.getCost()+50);
                        }
                    }
                    player.resetJailCount();
                    return;
                default:
                    System.out.println("You Rolled a " + dice1 + " and a " + dice2);


                    if (dice1 == dice2){
                        player.setInJail(false);
                        player.resetJailCount();
                    }
                    else {
                        player.incrementJailCount();
                    }
                    return;
            }
        }
    }

    public static void handleEndOfJailActions(ArrayList<Property> gameSquare, Player player) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("You are out of turns and did not roll doubles, what would you like to do?");
            System.out.println("1) Pay $50");
            System.out.println("2) Use Get Out Of Jail Free Card");
            String endAction = scanner.nextLine();
            if (endAction.equals("1")) {
                System.out.println("You pay $50");
                player.addTotalMoney(-50);
                player.setInJail(false);
                for (Property property : gameSquare) {
                    if (property.getName().equals("Free Parking")) {
                        property.setCost(property.getCost() + 50);
                    }
                }
                player.resetJailCount();
                return;
            } else {
                if (!player.isGetOutOfJail()) {
                    System.out.println("You Do Not Have A Get Out Of Jail Free Card");
                } else {
                    player.setInJail(false);
                    player.setGetOutOfJail(false);
                    player.resetJailCount();
                    return;
                }
            }
        }
    }

    public static void beginning_actions(int dice_1, int dice_2, ArrayList<Property> game_square, Player player) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1) Manage Properties");
            System.out.println("2) Review Board");
            System.out.println("3) Declare Bankruptcy");
            System.out.println("4) Press Enter to Roll Dice");

            String action = scanner.nextLine();

            if (action.equals("1")) {
                manage_properties(player);
            } else if (action.equals("2")) {
                //print_board()
                System.out.println("Printing Board");
                System.out.println(Property.toString(game_square));
            } else if (action.equals("3")) {
                //declare_bankruptcy()
                System.out.println("Declaring Bankruptcy");
                if (GlobalVariables.isPlayer1Turn()) {
                    System.out.println("Main.Player 2 wins!");
                } else {
                    System.out.println("Main.Player 1 wins!");
                }
                System.out.println("Thank you for playing Monopoly");
                exit(0);

            } else if (action.equals("4") || action.isEmpty()) {
                System.out.println("You rolled a " + dice_1 + " and a " + dice_2);
                return;
            } else {
                System.out.println("Invalid Input, please input a valid one");
            }
        }

    }

    public static int roll_dice(){
        Random random = new Random();
        return random.nextInt(1,6);
    }

    public static void player_turn(String player_1_name, Player player, Player opos_player, ArrayList<Property> game_square, int p1_position){
        int dice_1 = roll_dice();
        int dice_2 = roll_dice();
        int roll = dice_1 + dice_2;
        //roll = 1;


        System.out.println("Bank Account Balance: "+ player.getTotal_money());
        if (player.isInJail()){
            if (player.getJailCount() == 2){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Press Enter to Roll On Your Last Turn");
                scanner.nextLine();
                if (dice_1 == dice_2){
                    System.out.println("You rolled a " + dice_1 + " and a " + dice_2);
                    System.out.println("You rolled doubles and are out of jail");
                    player.setInJail(false);
                    player.resetJailCount();
                }
                else {
                    handleEndOfJailActions(game_square, player);
                }
            }
            else {
                jailBeginningActions(dice_1, dice_2, game_square, player);
            }
        }
        else {
            beginning_actions(dice_1, dice_2, game_square, player);
        }
        int old_position = player.getPosition();
        player.setPosition(old_position += roll);
        if (player.getPosition() > 40){
            player.setPosition((roll -(40-old_position)));
        }

        if (old_position > player.getPosition() && old_position != 40 && player.getPosition() != 0){
            if (!player.isInJail()){
                System.out.println("You Passed Go! Collect $200");
                player.addTotalMoney(200);
                System.out.println();
            }
        }

        if (!player.isInJail()){
            System.out.println(player_1_name + ", you landed on " + game_square.get(player.getPosition()).getName());
            }

        if (player.isInJail()){
            player.setPosition(10);
        }

        player_land_on_property(player.getPosition(),player,opos_player,game_square.get(player.getPosition()).getName(), game_square);
        while(player.getTotal_money() <= 0){
            System.out.println("You are in the negative. You can sell houses and hotels or mortgage properties to get above 0");
            System.out.println("If you cannot get above 0, Declare Bankruptcy");
            System.out.println("Your Balance: " + player.getTotal_money());
            GlobalVariables.setPlayer1Turn(true);
        }
        if (dice_1 == dice_2){

        }
        else if (GlobalVariables.isPlayer1Turn()){
            GlobalVariables.setPlayer1Turn(false);
        }
        else {
            GlobalVariables.setPlayer1Turn(true);
        }
    }

    public static void player_land_on_property(int position, Player curr_player, Player opos_player, String property_name, ArrayList<Property> game_square){
        Property property = game_square.get(position);
        Scanner scanner = new Scanner(System.in);

        switch(property_name) {
            case "Community Chest" -> landOnCommunityChest(curr_player,opos_player,game_square);
            case "Income Tax" -> //land_on_income_tax()
                    System.out.println("You landed on Income tax");
            case "Reading Railroad", "B&O Railroad", "Pennsylvania Railroad","Short Line" -> land_on_railroad(curr_player,opos_player,property);
            case "Chance" -> landOnChance(curr_player,opos_player,game_square);
            case "Jail" -> {
                if (curr_player.isInJail()){

                }
                else{
                curr_player.setPosition(10);
                System.out.println("You are visiting jail");
                }
            }
            case "Electric Company" -> //land_on_utility(specific utility);
                    System.out.println("You landed on Electric Company");
            case "Free Parking" -> {
                System.out.println("You landed on Free Parking");
                System.out.println("You receive $"+property.getCost());
                curr_player.addTotalMoney(property.getCost());
                property.setCost(500);
            }
            case "Water Works" -> //land_on_utility(specific utility);
                    System.out.println("You landed on Water Works");
            case "Go To Jail" -> landOnGoToJail(curr_player);
            case "Luxury Tax" -> //land_on_luxury_tax();
                    System.out.println("You landed on Luxury Tax");
            case "Go" -> //land_on_go();
                    System.out.println("You landed on Go");
            default -> {
                if (property.isOwned()){
                    handleIfOwned(curr_player, opos_player, property);
                }
                else {
                    System.out.println("Would you like to buy it? (Yes or No)");
                    String buy_or_no = scanner.nextLine();
                    if (buy_or_no.equals("Yes") || buy_or_no.isEmpty()) {
                        if (curr_player.getTotal_money() < property.getCost()) {
                            System.out.println("Sorry, You don't have the funds to purchase this property");
                        } else {
                            curr_player.addTotalMoney(-property.getCost());
                            curr_player.getProperties().add(property);
                            property.setOwned(true);
                        }
                    } else if (buy_or_no.equals("No")) {
                        System.out.println("You chose not to buy this property");
                    }
                }
                property.updatePropertiesRent(curr_player,property,game_square);
            }
        }

    }

    private static void landOnGoToJail(Player player) {
        System.out.println("Go To Jail!");
        player.setPosition(10);
        player.setInJail(true);
        GlobalVariables.setPlayer1Turn(!GlobalVariables.isPlayer1Turn());

    }

    private static void landOnChance(Player player, Player opos, ArrayList<Property> gameSquare){
        System.out.println("You Landed On Chance");
        switch (GlobalVariables.getChanceCount()) {
            case 1:
                System.out.println("Advance to Boardwalk");
                player.setPosition(39);
                player_land_on_property(player.getPosition(),player,opos,"Boardwalk",gameSquare);
                break;
            case 2:
                System.out.println("Advance to Go (Collect $200)");
                player.setPosition(40);
                player.addTotalMoney(200);
                break;
            case 3:
                System.out.println("Advance to Illinois Avenue. If you pass Go, collect $200");
                player.setPosition(24);
                player_land_on_property(player.getPosition(),player,opos,"Illinois Ave",gameSquare);
                break;
            case 4:
                System.out.println("Advance to St. Charles Place. If you pass Go, collect $200");
                player.setPosition(11);
                player_land_on_property(player.getPosition(),player,opos,"St. Charles Place",gameSquare);
                break;
            case 5:
                GlobalVariables.setRailRoadRentDoubled(true);
                System.out.println("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay double the rent.");
                if (player.getPosition() < 5 || player.getPosition() >35){
                    player.setPosition(5);
                    player_land_on_property(player.getPosition(),player,opos,"Reading Railraod",gameSquare);
                }
                else if (player.getPosition() > 5 || player.getPosition() < 15){
                    player.setPosition(15);
                    player_land_on_property(player.getPosition(),player,opos,"Pennsylvania Railroad",gameSquare);
                }
                else if (player.getPosition() > 15 || player.getPosition() < 25){
                    player.setPosition(25);
                    player_land_on_property(player.getPosition(),player,opos,"B&O Railroad",gameSquare);
                }
                else if ( player.getPosition() > 25 || player.getPosition() < 35){
                    player.setPosition(35);
                    player_land_on_property(player.getPosition(),player,opos,"Short Line",gameSquare);
                }
                GlobalVariables.setRailRoadRentDoubled(false);
                break;
            case 6:
                System.out.println("Advance to the nearest Railroad. If unowned, you may buy it from the Bank. If owned, pay double the rent.");
                break;
            case 7:
                System.out.println("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times amount thrown");
                break;
            case 8:
                System.out.println("Bank pays you dividend of $50");
                break;
            case 9:
                System.out.println("Get Out of Jail Free");
                break;
            case 10:
                System.out.println("Go Back 3 Spaces");
                break;
            case 11:
                System.out.println("Go to Jail. Go directly to Jail, do not pass Go, do not collect $200");
                break;
            case 12:
                System.out.println("Make general repairs on all your property. For each house pay $25. For each hotel pay $100");
                break;
            case 13:
                System.out.println("Speeding fine $15");
                break;
            case 14:
                System.out.println("Take a trip to Reading Railroad. If you pass Go, collect $200");
                break;
            case 15:
                System.out.println("You have been elected Chairman of the Board. Pay each player $50");
                break;
            case 16:
                System.out.println("Your building loan matures. Collect $150");
                break;
            default:
                System.out.println("Invalid chance card number");
                break;
        }
        GlobalVariables.incrementChanceCount();
    }
    private static void landOnCommunityChest(Player player, Player opos, ArrayList<Property> gameSquare) {
        System.out.println("You Landed on Community Chest");
        switch (GlobalVariables.getCommunityChestCount()) {
            case 1:
                System.out.println("Advance to Go (Collect $200)");
                player.addTotalMoney(200);
                player.setPosition(40);
                break;
            case 2:
                System.out.println("Bank error in your favor. Collect $200");
                player.addTotalMoney(200);
                break;
            case 3:
                System.out.println("Doctor's Fee. Pay $50");
                player.addTotalMoney(-50);
                break;
            case 4:
                System.out.println("From sale of stock you get $50");
                player.addTotalMoney(50);
                break;
            case 5:
                System.out.println("Get out of Jail Free");
                player.setGetOutOfJail(true);
                break;
            case 6:
                System.out.println("Go to Jail. Go directly to jail, do not pass Go, do not collect $200");
                player.setInJail(true);
                player.setPosition(10);
                break;
            case 7:
                System.out.println("Holiday fund matures. Receive $100");
                player.addTotalMoney(100);
                break;
            case 8:
                System.out.println("Income Tax refund. Receive $100");
                player.addTotalMoney(100);
                break;
            case 9:
                System.out.println("It is your birthday. Collect $10 from every player");
                player.addTotalMoney(10);
                opos.addTotalMoney(-10);
                break;
            case 10:
                System.out.println("Life insurance matures. Collect $100");
                player.addTotalMoney(100);
                break;
            case 11:
                System.out.println("Pay hospital fees of $100");
                player.addTotalMoney(-100);
                break;
            case 12:
                System.out.println("Pay school fees of $50");
                player.addTotalMoney(-50);
                break;
            case 13:
                System.out.println("Receive $25 consultancy fee");
                player.addTotalMoney(25);
                break;
            case 14:
                System.out.println("You are assessed for street repair. $40 per house. $115 per hotel");
                player.addTotalMoney(-40*player.getNumHouses());
                player.addTotalMoney(-115*player.getNumHotels());
                break;
            case 15:
                System.out.println("You have won second prize in a beauty contest ($10)");
                player.addTotalMoney(10);
                break;
            case 16:
                System.out.println("You inherit $100");
                player.addTotalMoney(100);
                GlobalVariables.resentCommunityChestCount();
                break;
            default:
                System.out.println("Invalid community chest card number");
                break;
        }
        GlobalVariables.incrementCommunityChestCount();
    }

    private static void handleIfOwned(Player curr_player, Player opos_player, Property property) {
        if(curr_player.getProperties().contains(property)){
            System.out.println("You already own it");
        }
        else if (opos_player.getProperties().contains(property)){
            if (property.isPropertyMortgaged()){
                System.out.println("Property is Mortgaged");
            }
            else{
                if (GlobalVariables.isRailRoadRentDoubled()){
                    System.out.println("You owe " + (2 * property.getRent()));
                    curr_player.addTotalMoney(-2* property.getRent());
                    opos_player.addTotalMoney(2* property.getRent());
                }
                else {
                    System.out.println("You owe " + property.getRent());
                    curr_player.addTotalMoney(-property.getRent());
                    opos_player.addTotalMoney(property.getRent());
                }
            }
        }
    }

    private static void land_on_railroad(Player player,Player opos, Property property) {

        if (property.isOwned()){
            handleIfOwned(player,opos,property);
            return;
        }
        player.addTotalMoney(-200);
        player.getProperties().add(property);
        property.updateRailroads(player);
        property.setOwned(true);
            }



}
