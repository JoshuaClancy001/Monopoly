package Main;

public class GlobalVariables {
    public static int communityChestCount = 1;
    public static int chanceCount = 1;
    public static boolean isGameOver = false;
    public static boolean player1Turn = true;
    public static boolean railRoadRentDoubled = false;

    public static void setCommunityChestCount(int communityChestCount) {
        GlobalVariables.communityChestCount = communityChestCount;
    }

    public static boolean isRailRoadRentDoubled() {
        return railRoadRentDoubled;
    }

    public static void setRailRoadRentDoubled(boolean railRoadRentDoubled) {
        GlobalVariables.railRoadRentDoubled = railRoadRentDoubled;
    }

    public static boolean isPlayer1Turn() {
        return player1Turn;
    }

    public static void setPlayer1Turn(boolean player1Turn) {
        GlobalVariables.player1Turn = player1Turn;
    }

    public static int getCommunityChestCount() {
        return communityChestCount;
    }

    public static void incrementCommunityChestCount() {
        GlobalVariables.communityChestCount +=1;
    }
    public static void resentCommunityChestCount(){
        GlobalVariables.communityChestCount = 1;
    }

    public static int getChanceCount() {
        return chanceCount;
    }

    public static void setChanceCount(int chanceCount) {
        GlobalVariables.chanceCount = chanceCount;
    }

    public static void incrementChanceCount() {
        GlobalVariables.chanceCount +=1;
    }
    public static void resentChanceCount(){
        GlobalVariables.chanceCount = 1;
    }

    public static boolean isIsGameOver() {
        return isGameOver;
    }

    public static void setIsGameOver(boolean isGameOver) {
        GlobalVariables.isGameOver = isGameOver;
    }
}
