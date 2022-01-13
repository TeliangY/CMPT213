package ca.sfu.cmpt213.a2.model;

/**
 * The Cell class is used to store the information of
 * each cell. The data includes the category of this celldddads
 * (such as Hunter, Wall, Guardian, etc.), coordinates and
 * whether it is visible.
 */
public class Cell {
    private int symbol;//-1:initialization    0:Wall      1:hunter
                       // 2:guardians    3:relics   4:blank
    private boolean visible;
    private int indexRow;
    private int indexCol;
    public Cell(){
        symbol=-1;
        indexCol=-1;
        indexRow=-1;
        visible=false;
    }
    public Cell(int symbol, int indexRow, int indexCol) {
        this.symbol = symbol;
        this.indexRow = indexRow;
        this.indexCol = indexCol;
    }
    public boolean getVisible(){
        return this.visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public void setIndexRow(int indexRow) {
        this.indexRow = indexRow;
    }

    public void setIndexCol(int indexCol) {
        this.indexCol = indexCol;
    }

    public int getSymbol() {
        return symbol;
    }

    public int getIndexRow() {
        return indexRow;
    }

    public int getIndexCol() {
        return indexCol;
    }
}
