package MineSweeper;

public class Cell {
    int originalVal;
    char displayValue;
    boolean state;

    public Cell() {
        this.originalVal = 0;
        this.displayValue = '-';
        this.state = false;
    }

}
