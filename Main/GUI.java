package Main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JTextArea textArea;

    public GUI(ArrayList<Property> properties) {
        setTitle("Main.Property Cards");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create JTextArea to display property details
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Display property details
        displayProperties(properties);
    }

    private void displayProperties(ArrayList<Property> properties) {
        StringBuilder sb = new StringBuilder();
        for (Property property : properties) {
            sb.append("Name: ").append(property.getName()).append("\n");
            sb.append("Owned: ").append(property.isOwned()).append("\n");
            sb.append("Color Group: ").append(property.getColorGroup()).append("\n");
            sb.append("Cost: ").append(property.getCost()).append("\n");
            sb.append("Rent: ").append(property.getRent()).append("\n");
            sb.append("One House Rent: ").append(property.getOneHouseRent()).append("\n");
            sb.append("Two House Rent: ").append(property.getTwoHouseRent()).append("\n");
            sb.append("Three House Rent: ").append(property.getThreeHouseRent()).append("\n");
            sb.append("Four House Rent: ").append(property.getFourHouseRent()).append("\n");
            sb.append("Hotel Rent: ").append(property.getHotelRent()).append("\n");
            sb.append("House Cost: ").append(property.getHouseCost()).append("\n");
            sb.append("Mortgage Price: ").append(property.getMortgagePrice()).append("\n");
            sb.append("Unmortgage Price: ").append(property.getUnmortgagePrice()).append("\n");
            sb.append("Rent Doubled: ").append(property.isRentDoubled()).append("\n");
            sb.append("Number of Houses: ").append(property.getNumHouses()).append("\n");
            sb.append("Number of Hotels: ").append(property.getNumHotels()).append("\n");
            sb.append("Property is Mortgaged: ").append(property.isPropertyMortgaged()).append("\n");
            sb.append("Original Rent: ").append(property.getOriginalRent()).append("\n\n");
        }
        textArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        ArrayList<Property> properties = Property.game_square();
        SwingUtilities.invokeLater(() -> new GUI(properties).setVisible(true));
    }
}
