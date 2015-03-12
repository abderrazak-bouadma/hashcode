package org.algeriajug.hc.domain;

/**
 * Created with IntelliJ IDEA.
 * User: abderrazak
 * Date: 12/03/15
 * Time: 20:53
 */
public class Server {
    public static final String WS = " ";
    public static final String LF = "\n";
    private int serverSlotSize;
    private int serverCapacity;
    private int serverRow;
    private int serverSlot;
    private int serverGroup;

    public String getFormattedOutput() {
        return serverRow + WS + serverSlot + WS + serverGroup + LF;
    }
    public void applyToServer(int serverRow, int serverSlot, int serverGroup) {
        this.serverRow = serverRow;
        this.serverSlot = serverSlot;
        this.serverGroup = serverGroup;
    }

    public Server(String serverSlotSize, String serverCapacity) {
        this.serverSlotSize = Integer.decode(serverSlotSize);
        this.serverCapacity = Integer.decode(serverCapacity);
    }

    public int getServerSlotSize() {
        return serverSlotSize;
    }

    public void setServerSlotSize(int serverSlotSize) {
        this.serverSlotSize = serverSlotSize;
    }

    public int getServerCapacity() {
        return serverCapacity;
    }

    public void setServerCapacity(int serverCapacity) {
        this.serverCapacity = serverCapacity;
    }

    @Override
    public String toString() {
        return "Server{" +
                "serverSlotSize=" + serverSlotSize +
                ", serverCapacity=" + serverCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Server)) return false;

        Server server = (Server) o;

        if (serverCapacity != server.serverCapacity) return false;
        if (serverSlotSize != server.serverSlotSize) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serverSlotSize;
        result = 31 * result + serverCapacity;
        return result;
    }

}
