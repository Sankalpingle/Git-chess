public boolean makeMoveAlgebraic(String fromAlg, String toAlg) {
    Position from = Position.fromAlgebraic(fromAlg);
    Position to = Position.fromAlgebraic(toAlg);
    boolean ok = board.tryMoveLegal(from, to, toMove);
    if (ok) toMove = (toMove == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    return ok;
}

public boolean inCheck(Piece.Color color) { return board.inCheck(color); }

public boolean hasAnyLegalMove(Piece.Color side) {
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            Position from = new Position(r, c);
            Piece p = board.get(from);
            if (p == null || p.color() != side) continue;
            for (Position to : p.legalTargets(board, from)) {
                Board copy = board.copy();
                Piece mv = copy.get(from);
                copy.set(to, mv);
                copy.set(from, null);
                if (!copy.inCheck(side)) return true;
            }
        }
    }
    return false;
}

public String status() {
    Piece.Color enemy = (toMove == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    boolean check = board.inCheck(toMove);
    boolean legal = hasAnyLegalMove(toMove);
    if (!legal && check) return (toMove == Piece.Color.WHITE ? "White" : "Black") + " to move is checkmated.";
    if (!legal) return "Stalemate.";
    if (check) return (toMove == Piece.Color.WHITE ? "White" : "Black") + " to move is in check.";
    return (toMove == Piece.Color.WHITE ? "White" : "Black") + " to move.";
}

public void print() { board.printAscii(); }
