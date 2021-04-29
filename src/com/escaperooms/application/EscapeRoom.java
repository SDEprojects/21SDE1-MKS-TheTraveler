package com.escaperooms.application;

import com.escaperooms.crazystans.CrazyStans;
import com.escaperooms.joninexams.JoninExams;
import com.escaperooms.spaceodyssey.SpaceOdyssey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.*;
import java.util.stream.Collectors;

public class EscapeRoom implements EscapeRoomInterface {
    private static final EscapeRoomPrompter escapeRoomPrompter = new EscapeRoomPrompter();
    Map<String, Playable> escapeRooms = new HashMap<>();
    public Map<String, Room> innerRooms;
    private static EscapeRoom me;

    public EscapeRoom() throws IOException {
        this.innerRooms = this.load();

    }

    public Map<String, Room> load() throws IOException {

        Map<String, Room> allRooms = new HashMap<>();
        try {
            InputStream in = getClass().getResourceAsStream("/resources/data/RoomData.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            reader.lines().forEach(roomData -> {
                String[] roomDataCells = roomData.split(" : ");
                String gameName = roomDataCells[0];
                String roomName = roomDataCells[1];
                List<String> roomItems = Arrays.stream(roomDataCells[2].split(" ~ ")).collect(Collectors.toList());
                List<String> roomUsefulItems = Arrays.stream(roomDataCells[3].split(" ~ ")).collect(Collectors.toList());
                List<String> actorNames = Arrays.stream(roomDataCells[4].split(" ~ ")).collect(Collectors.toList());
                List <String> doors = Arrays.stream(roomDataCells[5].split(" ~ ")).collect(Collectors.toList());

                Room currentRoom = new Room(roomName, roomItems, roomUsefulItems, actorNames,doors);
                allRooms.put(gameName + " : " + roomName, currentRoom);
            });
        } catch(Exception e) {
            System.out.println(e);
        }
        return allRooms;


    }

    public void generateEscapeRooms(EscapeRoom room) throws IOException {
        me = room;
        SpaceOdyssey spaceOdyssey = new SpaceOdyssey();
        CrazyStans crazyStans = new CrazyStans();
        JoninExams joninExams = new JoninExams();
        escapeRooms.put(spaceOdyssey.getName(), spaceOdyssey.playable());
        escapeRooms.put(crazyStans.getName(), crazyStans.playable());
        escapeRooms.put(joninExams.getName(), joninExams.playable());
    }

    public Map<String, Playable> getEscapeRooms() {
        return escapeRooms;
    }


    public Playable getEscapeRoomPlayable(String room) {
        Map<String, Playable> playables = getEscapeRooms();
        return playables.get(room);
    }

    public void removeEscapeRoom(String room) {
        escapeRooms.remove(room);
    }

    public static String prompt(String message, String regex, String errorMessage) {
        String customRegex = "|quit|" + regex;
        String input = escapeRoomPrompter.getPrompt(message, customRegex, errorMessage);
        // check for global commands
        switch (input) {
            case "quit":
                me.quitGame();
        }
        return input;
    }

    private void quitGame() {
        System.out.println("Quitting game...");
        System.exit(0);
    }

    @Override
    public String getName() {
        return null;
    }
}
