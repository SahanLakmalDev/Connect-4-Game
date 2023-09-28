package lk.ijse.dep.services;

public class BoardImpl implements Board{

    private final Piece[][] pieces;
    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
        this.boardUI = boardUI;
        // Initialize the board with EMPTY pieces
        for (int col = 0; col < NUM_OF_COLS; col++) {
            for (int row = 0; row < NUM_OF_ROWS; row++) {
                pieces[col][row] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        // Start searching from the bottom (row 0) and go upwards
        for (int row = 0; row < NUM_OF_ROWS; row++) {
            if(this.pieces[col][row] == Piece.EMPTY){
                return row;
            }
        }
        // If the column is full, return -1
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        int row = findNextAvailableSpot(col);
        if(row != -1){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean existLegalMove() {
        for (int col = 0; col < NUM_OF_COLS; col++) {
           if(isLegalMove(col)){
               return true;
           }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        int row = findNextAvailableSpot(col);
        if(row != -1){
            this.pieces[col][row] = move;
        }

    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;

    }

    @Override
    public Winner findWinner() {
        //Check Vertically
        for (int col = 0; col < NUM_OF_COLS; col++) {
            int count = 1;
            int row = findNextAvailableSpot(col);
            int n = 0;
            if(row == -1){
                n = 5;
            }else{
                n = row;
            }
            for (int i = 1; i < n ; i++) {
                if(pieces[col][i] == pieces[col][i-1]){
                    count++;
                    if(count == 4){
                        return new Winner(pieces[col][i], col, i - 3, col, i);
                    }
                }else{
                    count = 1;
                    if(i > 1){
                        break;
                    }
                }
            }
        }
        //check Horizontally
        for (int row = 0; row < NUM_OF_ROWS; row++) {
            int count = 1;
            for (int i = 1; i < NUM_OF_COLS; i++) {
                if(pieces[i][row] == pieces[i-1][row] && pieces[i][row] != Piece.EMPTY){
                    count++;
                    if(count == 4){
                        return new Winner(pieces[i][row], i-3, row, i, row);
                    }
                }else{
                    count = 1;
                    if(i>2){
                        break;
                    }
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}
