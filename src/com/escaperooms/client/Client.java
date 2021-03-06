package com.escaperooms.client;

import com.escaperooms.application.*;
import org.fusesource.jansi.AnsiConsole;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

class Client extends JPanel{

    public static void main(String[] args)  {
        try {
                    AnsiConsole.systemInstall();
        System.out.println(" _____                   _           \n" +
                "|_   _|                 | |          \n" +
                "  | |_ __ __ ___   _____| | ___ _ __ \n" +
                "  | | '__/ _` \\ \\ / / _ | |/ _ | '__|\n" +
                "  | | | | (_| |\\ V |  __| |  __| |   \n" +
                "  \\_|_|  \\__,_| \\_/ \\___|_|\\___|_|   \n");
            User user = new User();
            EscapeRoomGame escapeRoomGame = new EscapeRoomGame();
            String name = EscapeRoomGame.prompt("Please enter your name: ", "[ a-zA-z]*",
                    "\nThat is not a valid name!\n");
            user.newName(name);
            Traveler traveler = new Traveler(user, escapeRoomGame);

            Frame frame = new Frame("Escape Room", traveler);

            frame.showGame();

        } catch (Exception e) {
            System.out.println("The main is not working");
            System.out.println(e.getMessage());
        }
    }

}
