package Tests;

import Main.*;
import org.junit.jupiter.api.*;

import java.io.InputStream;
import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndToEndTest {
    static String player1;
    static String player2;
    static Player p1;
    static Player p2;
    static ArrayList<Property> game_square;
    static InputStream originalSystemIn;


    @BeforeAll
    static void setUp(){
        game_square = Property.game_square();

        player1 = "player1";
        player2 = "player2";


        p1 = new Player(player1,1500,0,new ArrayList<>(),false,false,0,0);
        p2 = new Player(player2,1500,0,new ArrayList<>(),false,false,0,0);

        String input = "Yes";
        originalSystemIn = System.in;
        System.setIn(new MockInputStream(input));
    }

    @Test
    @Order(1)
    public void test1PlayerLandsOnBalticAvenue() {
        p1.setPosition(3);
        GamePlay.player_land_on_property(3, p1, p2, "Baltic", game_square);
        int p1TotalMoneyActual = p1.getTotal_money();
        boolean p1PropertiesContainsActual = p1.getProperties().contains(game_square.get(3));
        boolean propertyIsOwned = game_square.get(3).isOwned();
        Assertions.assertEquals(1440, p1TotalMoneyActual);
        Assertions.assertTrue(p1PropertiesContainsActual);
        Assertions.assertTrue(propertyIsOwned);
        game_square.get(3).updatePropertiesRent(p1,game_square.get(3),game_square);
    }

    @Test
    @Order(2)
    public void testPlayer2LandsOnReadingRailroad() {
        p2.setPosition(5);
        GamePlay.player_land_on_property(5, p2, p1, "Reading Railroad", game_square);
        int p2TotalMoneyActual = p2.getTotal_money();
        boolean p2PropertiesContainsActual = p2.getProperties().contains(game_square.get(5));
        int readingRailroadRentActual = game_square.get(5).getRent();
        Assertions.assertEquals(1300, p2TotalMoneyActual);
        Assertions.assertEquals(25, readingRailroadRentActual);
        Assertions.assertTrue(p2PropertiesContainsActual);
        game_square.get(5).updatePropertiesRent(p2,game_square.get(5),game_square);
    }

    @Test
    @Order(3)
    public void testPlayer1LandsOnReadRail() {
        p1.setPosition(5);
        GamePlay.player_land_on_property(5, p1, p2, "Reading Railroad", game_square);
        int p1TotalMoneyActual = p1.getTotal_money();
        int p2TotalMoneyActual = p2.getTotal_money();
        Assertions.assertEquals(1415, p1TotalMoneyActual);
        Assertions.assertEquals(1325, p2TotalMoneyActual);
        game_square.get(5).updatePropertiesRent(p1,game_square.get(5),game_square);
    }

    @Test
    @Order(4)
    public void testPlayer1LandsOnStatesAvenue() {
        System.setIn(new MockInputStream("Yes"));
        p1.setPosition(13);
        GamePlay.player_land_on_property(13, p1, p2, "States Ave", game_square);
        int p1TotalMoneyActual = p1.getTotal_money();
        boolean propertiesContainsActual = p1.getProperties().contains(game_square.get(13));
        boolean propertyOwned = game_square.get(13).isOwned();
        Assertions.assertEquals(1275, p1TotalMoneyActual);
        Assertions.assertTrue(propertiesContainsActual);
        Assertions.assertTrue(propertyOwned);
        game_square.get(13).updatePropertiesRent(p1,game_square.get(13),game_square);
    }

    @Test
    @Order(5)
    public void testPlayer2LandsOnJail() {
        p2.setPosition(10);
        GamePlay.player_land_on_property(10, p2, p1, "Jail", game_square);
        boolean p2IsInJailActual = p2.isInJail();
        Assertions.assertFalse(p2IsInJailActual);
        game_square.get(10).updatePropertiesRent(p2,game_square.get(10),game_square);
    }

    @Test
    @Order(6)
    public void TestPlayer1LandOnFreeParking(){
        // p1 land on free parking, p1.money=1775
        p1.setPosition(20);
        GamePlay.player_land_on_property(20,p1,p2,"Free Parking",game_square);
        int p1TotalMoneyActual = p1.getTotal_money();
        Assertions.assertEquals(1775,p1TotalMoneyActual);
    }

    @Test
    @Order(7)
    public void TestPlayer2LandOnStates(){
        GamePlay.player_land_on_property(13, p2, p1, "States Ave", game_square);
        int p1TotalMoneyActual = p1.getTotal_money();
        int p2TotalMoneyActual = p2.getTotal_money();
        Assertions.assertEquals(1785,p1TotalMoneyActual);
        Assertions.assertEquals(1315,p2TotalMoneyActual);
        game_square.get(13).updatePropertiesRent(p1,game_square.get(13),game_square);
    }

    @Test
    @Order(8)
    public void TestPlayer1LandOnIllinois(){
        System.setIn(new MockInputStream("Yes"));
        GamePlay.player_land_on_property(24,p1,p2,"Illinois Ave",game_square);
        int p1TotalMoneyActual = p1.getTotal_money();
        boolean propertyContainsActual = p1.getProperties().contains(game_square.get(24));
        boolean propertyIsOwned = game_square.get(24).isOwned();
        Assertions.assertEquals(1545,p1TotalMoneyActual);
        Assertions.assertTrue(propertyContainsActual);
        Assertions.assertTrue(propertyIsOwned);

    }

    @Test
    @Order(9)
    public void TestPlayer2LandOnCommunityChest(){
        GamePlay.player_land_on_property(17,p2,p1,"Community Chest",game_square);
        int p2TotalMoneyActual = p2.getTotal_money();
        int p2PositionActual = p2.getPosition();
        Assertions.assertEquals(1515,p2TotalMoneyActual);
        Assertions.assertEquals(40,p2PositionActual);
    }

    @Test
    @Order(10)
    public void TestPlayer1LandsOnGoToJail(){

        p1.setPosition(30);
        GamePlay.player_land_on_property(30,p1,p2,"Go To Jail",game_square);
        int p1PositionActual = p1.getPosition();
        boolean p1InJailActual = p1.isInJail();
        boolean p1TurnActual = GlobalVariables.isPlayer1Turn();
        Assertions.assertEquals(10,p1PositionActual);
        Assertions.assertTrue(p1InJailActual);
        Assertions.assertFalse(p1TurnActual);


    }
    @Test
    @Order(11)
    public void TestPlayer2LandsOnKentucky(){
        //p2Money=1295
        System.setIn(new MockInputStream("Yes"));
        p2.setPosition(21);
        GamePlay.player_land_on_property(21,p2,p1,"Kentucky Ave",game_square);
        int p2MoneyActual = p2.getTotal_money();
        boolean propContainsActual = p2.getProperties().contains(game_square.get(21));
        boolean propOwnedActual = game_square.get(21).isOwned();
        Assertions.assertEquals(1295,p2MoneyActual);
        Assertions.assertTrue(propContainsActual);
        Assertions.assertTrue(propOwnedActual);
    }
    @Test
    @Order(12)
    public void TestPlayer1FirstRoundInJail(){
        // player 1 first round chooses to roll dice, no doubles, jailCount=1,position=10,
        System.setIn(new MockInputStream("3"));
        GamePlay.jailBeginningActions(2,3,game_square,p1);
        GamePlay.player_land_on_property(10,p1,p2,"Jail",game_square);
        Assertions.assertTrue(p1.isInJail());
        Assertions.assertEquals(1,p1.getJailCount());
    }
    @Test
    @Order(13)
    public void TestPlayer2LandsOnBAndORailroad(){
        // p2 owns two railroads, rent or both = 50
        p2.setPosition(25);
        GamePlay.player_land_on_property(25,p2,p1,"B&O Railroad",game_square);
        int p2TotalMoneyActual = p2.getTotal_money();
        boolean p2PropertiesContainsActual = p2.getProperties().contains(game_square.get(25));
        int readingRailroadRentActual = game_square.get(5).getRent();
        int BandORailRoadRentActual = game_square.get(25).getRent();
        Assertions.assertEquals(1095, p2TotalMoneyActual);
        Assertions.assertEquals(50, readingRailroadRentActual);
        Assertions.assertEquals(50,BandORailRoadRentActual);
        Assertions.assertTrue(p2PropertiesContainsActual);
        game_square.get(5).updatePropertiesRent(p2,game_square.get(5),game_square);
    }

    @Test
    @Order(14)
    public void TestPlayer1SecondRoundInJail(){
        // player 1 second round chooses to roll dice, no doubles, jailCount=1,position=10,
        System.setIn(new MockInputStream("3"));
        GamePlay.jailBeginningActions(2,3,game_square,p1);
        GamePlay.player_land_on_property(10,p1,p2,"Jail",game_square);
        Assertions.assertTrue(p1.isInJail());
        Assertions.assertEquals(2,p1.getJailCount());
    }
    @Test
    @Order(15)
    public void Player2LandsOnMarvinGardens(){
        //p2Money=815
        System.setIn(new MockInputStream("Yes"));
        p2.setPosition(29);
        GamePlay.player_land_on_property(29,p2,p1,"Marvin Gardens",game_square);
        int p2MoneyActual = p2.getTotal_money();
        boolean propContainsActual = p2.getProperties().contains(game_square.get(29));
        boolean propOwnedActual = game_square.get(29).isOwned();
        Assertions.assertEquals(815,p2MoneyActual);
        Assertions.assertTrue(propContainsActual);
        Assertions.assertTrue(propOwnedActual);
    }
    @Test
    @Order(16)
    public void TestPlayer1ThirdRoundInJailPay50(){
        System.setIn(new MockInputStream("1"));
        GamePlay.handleEndOfJailActions(game_square,p1);
        Assertions.assertFalse(p1.isInJail());
        Assertions.assertEquals(1495,p1.getTotal_money());
        System.setIn(new MockInputStream("Yes"));
        GamePlay.player_land_on_property(15,p1,p2,"Pennsylvania Railroad",game_square);
        Assertions.assertEquals(1295,p1.getTotal_money());
        Assertions.assertTrue(game_square.get(15).isOwned());
        Assertions.assertTrue(p1.getProperties().contains(game_square.get(15)));
    }
    @Test
    @Order(17)
    public void TestPlayer2Something(){}



    @AfterAll
    static void breakDown(){
        System.setIn(originalSystemIn);
    }


}
