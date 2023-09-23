package lk.ijse.dep.services;

public class AiPlayer extends Player{
    public AiPlayer(Board board) {
        super(board);
    }
    public void movePiece(int col){
        int randomCol;
        do{
            randomCol = (int) (Math.random() * Board.NUM_OF_COLS);
        }while(!this.board.isLegalMove(randomCol));
        this.board.updateMove(randomCol, Piece.GREEN);
        this.board.getBoardUI().update(randomCol, false);
        Winner winner = this.board.findWinner();
        if(winner.getWinningPiece() != Piece.EMPTY){
            this.board.getBoardUI().notifyWinner(winner);
        }else{
            if(!this.board.existLegalMove()){
                this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }
    }
}
