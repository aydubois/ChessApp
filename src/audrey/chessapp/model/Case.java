package audrey.chessapp.model;

public class Case {
    private boolean empty = true;

    public boolean isEmpty() {
        return empty;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }


private Piece piece = null;

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    private boolean selected = false;
    private final String name;
    private  final int column;
    private  final int row;

    public Case(String name){
        this.name = name;

        String[] nameSplit = name.split("");
        this.row = Integer.parseInt(nameSplit[nameSplit.length-1]);
        this.column = Integer.parseInt(nameSplit[nameSplit.length-2]);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
