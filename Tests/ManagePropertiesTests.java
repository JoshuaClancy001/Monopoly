package Tests;

import Main.GamePlay;
import Main.MockInputStream;
import Main.Player;
import Main.Property;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.io.PrintStream;
import java.util.ArrayList;

import static Main.Property.game_square;

public class ManagePropertiesTests {

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

        originalSystemIn = System.in;
    }

    @Test
    void updatePropertyRentTest(){
        System.setIn(new MockInputStream("1"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));
        GamePlay.manage_properties(p1);
        System.setOut(originalOut);
        String actualOutput = baos.toString().trim().replaceAll("\\s+", " ");
        String expectedOutput = "What would you like to do? 1) Review 2) Build 3) Mortgage 4) Unmortgage Printing Properties You don't own any properties".trim().replaceAll("\\s+", " ");
        Assertions.assertEquals(expectedOutput, actualOutput);


    }
}



