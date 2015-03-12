package org.algeriajug.hc.domain;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Created with IntelliJ IDEA.
 * User: abderrazak
 * Date: 12/03/15
 * Time: 20:46
 */
public class DataCenter {

    public static final String LINE_ELEM_SEPARATOR = " ";
    private static DataCenter dcInstance;
    private int nbRows;
    private int nbSlotsPerRow;
    private int nbUnavailable;
    private int nbGroups;
    private int nbServers;
    private List<SlotCoordinates> unavailableSlotsCoordinates;
    private List<Server> servers;
    private Scanner stream;

    private DataCenter() {
        unavailableSlotsCoordinates = new ArrayList<>();
        servers = new ArrayList<>();
    }

    public static DataCenter newInstance() {
        if (dcInstance == null) {
            synchronized (DataCenter.class) {
                if (dcInstance == null) {
                    dcInstance = new DataCenter();
                }
            }
        }
        return dcInstance;
    }

    public DataCenter load(Scanner stream) {
        this.stream = stream;
        return dcInstance;
    }

    public DataCenter build() {
        int lineCounter = 0;
        while (stream.hasNext()) {
            lineCounter++;
            String line = stream.nextLine();
            String[] lineElements = line.split(LINE_ELEM_SEPARATOR);
            if (lineCounter == 1) {
                nbRows = Integer.decode(lineElements[0]);
                nbSlotsPerRow = Integer.decode(lineElements[1]);
                nbUnavailable = Integer.decode(lineElements[2]);
                nbGroups = Integer.decode(lineElements[3]);
                nbServers = Integer.decode(lineElements[4]);
            } else if (lineCounter > 1 && lineCounter <= nbUnavailable) {
                unavailableSlotsCoordinates.add(new SlotCoordinates(lineElements[0], lineElements[1]));
            } else {
                servers.add(new Server(lineElements[0], lineElements[1]));
            }
        }
        return dcInstance;
    }

    @Override
    public String toString() {
        return "DataCenter{" +
                "nbRows=" + nbRows +
                ", nbSlotsPerRow=" + nbSlotsPerRow +
                ", nbUnavailable=" + nbUnavailable +
                ", nbGroups=" + nbGroups +
                ", nbServers=" + nbServers +
                ", unavailableSlotsCoordinates=" + unavailableSlotsCoordinates +
                ", servers=" + servers +
                '}';
    }

    public DataCenter optimize() {
        servers.parallelStream()
                .sorted((o1, o2) -> Integer.valueOf(o1.getServerSlotSize()).compareTo(o2.getServerSlotSize()))
                .sorted((o1, o2) -> Integer.valueOf(o1.getServerCapacity()).compareTo(o2.getServerCapacity()));
        servers.forEach(server -> {
            server.applyToServer(1,2,3);
        });
        return dcInstance;
    }

    public void writeTo(BufferedWriter outfile) {
        servers.forEach(server -> {
            try {
                outfile.write(server.getFormattedOutput());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
