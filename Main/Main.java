package Main;

import Main.GlobalVariables;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        final ArrayList<Property> game_square = Property.game_square();



        System.out.println("What is player 1s name? ");
        String player_1 = scanner.nextLine();
        System.out.println("What is player 2s name? ");
        String player_2 = scanner.nextLine();
        System.out.println("Who is going first?");
        String going_first = scanner.nextLine();


        Player p1 = new Player( player_1,1500,0,
                new ArrayList<>(),false,false,
                0,0);

        Player p2 = new Player( player_2,1500,0,
                new ArrayList<>(),false,false,
                0,0);

        if (going_first.equals(player_1)){
            GlobalVariables.setPlayer1Turn(true);
        }
        else if( going_first.equals(player_2)){
            GlobalVariables.setPlayer1Turn(false);
        }


        System.out.println("Welcome to Monopoly " + player_1 + " and " + player_2);

        while (!GlobalVariables.isIsGameOver()){
            if (GlobalVariables.isPlayer1Turn()){
                System.out.println(player_1 + " it's your turn");
                GamePlay.player_turn(player_1, p1, p2, game_square, p1.getPosition());
            }
            else {
                System.out.println(player_2 + " it's your turn");
                GamePlay.player_turn(player_2, p2, p1, game_square, p2.getPosition());
            }
        }
    }


}
