package Tests;
import Main.Property;
import Main.Player;
import Main.GamePlay;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static Main.Property.game_square;

class ColorGroupTests {

    ArrayList<Property> game_square = new ArrayList<>();
    Player p1 = new Player( "player1",1500,0,
            new ArrayList<>(),false,false,
            0,0);
    Player p2 = new Player( "player2",1500,0,
            new ArrayList<>(),false,false,
            0,0);

    @BeforeEach
    public void setUp(){
        game_square = game_square();
    }

    @Test
    void updatePropertyRentTest(){
        // one not owned, no update
        p1.addProperty(game_square.get(1));
        game_square.get(1).updatePropertiesRent(p1,game_square.get(1),game_square);
        boolean expected = false;
        boolean actual = game_square.get(1).isRentDoubled();
        Assertions.assertEquals(expected,actual);

        // opponent ownes one, no update
        p2.addProperty(game_square.get(3));
        game_square.get(1).updatePropertiesRent(p1,game_square.get(1),game_square);
        actual = game_square.get(1).isRentDoubled();
        Assertions.assertEquals(expected,actual);

        // you own all, update happens
        p2.removeProperty(game_square.get(3));
        p1.addProperty(game_square.get(3));
        game_square.get(1).updatePropertiesRent(p1,game_square.get(1),game_square);
        expected = true;
        actual = game_square.get(1).isRentDoubled() && game_square.get(3).isRentDoubled();
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void purplesTest(){
        twoPropertyColorGroupTest(1,3);

    }

    @Test
    public void lightBlueTest(){
        colorGroupTest(6,8,9);
    }

    @Test
    public void magentaTest(){
        colorGroupTest(11,13,14);
    }

    @Test
    public void orangesTest(){
        colorGroupTest(16,18,19);
    }

    @Test
    public void redsTest(){
        colorGroupTest(21,23,24);
    }

    @Test
    public void yellowsTest(){
        colorGroupTest(26,27,29);
    }

    @Test
    public void greensTest(){
        colorGroupTest(31,32,34);

    }

    @Test
    public void darkBlueTest(){
        twoPropertyColorGroupTest(37,39);
    }

    @Test void railroadsTest(){
        // player owns 1 railroad, rent = 25
        p1.addProperty(game_square.get(5));
        game_square.get(5).updateRailroads(p1);
        int playerExpected = 25;
        int playerActual = game_square.get(5).getRent();
        Assertions.assertEquals(playerExpected, playerActual);

        // player owns 1, opponent owns 1, rent = 25 for both
        p2.addProperty(game_square.get(15));
        game_square.get(15).updateRailroads(p2);
        int opponentExpected = 25;
        int opponentActual = game_square.get(15).getRent();
        Assertions.assertEquals(playerExpected,playerActual);
        Assertions.assertEquals(opponentExpected,opponentActual);

        //reset
        p2.removeProperty(game_square.get(15));

        // player owns 2 railroads, rent = 50
        p1.addProperty(game_square.get(15));
        game_square.get(15).updateRailroads(p1);
        playerExpected = 50;
        playerActual = game_square.get(5).getRent();
        Assertions.assertEquals(playerExpected, playerActual);

        // player owns 2 opponent owns 1, rent = 50, 25 respectively
        p2.addProperty(game_square.get(25));
        game_square.get(25).updateRailroads(p2);
        opponentActual = game_square.get(25).getRent();
        Assertions.assertEquals(playerExpected,playerActual);
        Assertions.assertEquals(opponentExpected,opponentActual);

        // player owns 3 railroads, rent = 100
        p1.addProperty(game_square.get(25));
        game_square.get(15).updateRailroads(p1);
        playerExpected = 100;
        playerActual = game_square.get(5).getRent();
        Assertions.assertEquals(playerExpected, playerActual);

        // player owns all railroads, rent = 200
        p1.addProperty(game_square.get(35));
        game_square.get(15).updateRailroads(p1);
        playerExpected = 200;
        playerActual = game_square.get(5).getRent();
        Assertions.assertEquals(playerExpected, playerActual);

    }

    private void colorGroupTest(int firstProp, int secProp, int thirdProp) {
        // not all properties are owned
        p1.addProperty(game_square.get(firstProp));
        p1.addProperty(game_square.get(secProp));
        game_square.get(firstProp).updatePropertiesRent(p1,game_square.get(firstProp),game_square);
        boolean expected = false;
        boolean actual = (game_square.get(firstProp).isRentDoubled() && game_square.get(secProp).isRentDoubled() && game_square.get(thirdProp).isRentDoubled());
        Assertions.assertEquals(expected, actual);

        // mixed players own properties
        p2.addProperty(game_square.get(thirdProp));
        game_square.get(firstProp).updatePropertiesRent(p1,game_square.get(firstProp),game_square);
        actual = (game_square.get(firstProp).isRentDoubled() && game_square.get(secProp).isRentDoubled() && game_square.get(thirdProp).isRentDoubled());
        Assertions.assertEquals(expected,actual);

        // p1 owns all properties
        p2.removeProperty(game_square.get(thirdProp));
        p1.addProperty(game_square.get(thirdProp));
        game_square.get(firstProp).updatePropertiesRent(p1,game_square.get(firstProp),game_square);
        expected = true;
        actual = (game_square.get(firstProp).isRentDoubled() && game_square.get(secProp).isRentDoubled() && game_square.get(thirdProp).isRentDoubled());
        Assertions.assertEquals(expected,actual);
    }
    private void twoPropertyColorGroupTest(int firstProp, int secProp) {
        //one property owned one not owned
        p1.addProperty(game_square.get(firstProp));
        game_square.get(1).updatePropertiesRent(p1, game_square.get(firstProp), game_square);
        boolean expected = false;
        boolean actual = game_square.get(firstProp).isRentDoubled();
        Assertions.assertEquals(expected, actual);

        //one property owned by each player
        p2.addProperty(game_square.get(secProp));
        game_square.get(3).updatePropertiesRent(p2, game_square.get(secProp), game_square);
        actual = game_square.get(firstProp).isRentDoubled();
        Assertions.assertEquals(expected, actual);


        // both properties owned
        p2.removeProperty(game_square.get(secProp));
        p1.addProperty(game_square.get(secProp));
        game_square.get(secProp).updatePropertiesRent(p1, game_square.get(secProp), game_square);

        expected = true;
        actual = game_square.get(firstProp).isRentDoubled();
        Assertions.assertEquals(expected, actual);
    }
}