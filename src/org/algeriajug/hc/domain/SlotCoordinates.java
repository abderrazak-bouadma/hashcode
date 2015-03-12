package org.algeriajug.hc.domain;

/**
 * Created with IntelliJ IDEA.
 * User: abderrazak
 * Date: 12/03/15
 * Time: 20:54
 */
public class SlotCoordinates {

    private int row;
    private int column;

    public SlotCoordinates(String row, String column) {
        this.row = Integer.decode(row);
        this.column = Integer.decode(column);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "SlotCoordinates{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
