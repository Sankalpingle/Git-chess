public Board() {}

public Piece get(int r, int c) { return grid[r][c]; }
public Piece get(Position p) { return grid[p.row][p.col]; }

public void set(Position p, Piece piece) { grid[p.row][p.col] = piece; }

public Board copy() {
    Board b = new Board();
    for (int r = 0; r < 8; r++) {
        System.arraycopy(this.grid[r], 0, b.grid[r], 0, 8);
    }
    return b;
}

public static Board initial() {
    Board b = new Board();
    // Pawns
    for (int c = 0; c < 8; c++) {
        b.grid[c] = new Pawn(Piece.Color.WHITE);[6]
        b.grid[c] = new Pawn(Piece.Color.BLACK);[1]
    }
    // Rooks
    b.grid = new Rook(Piece.Color.WHITE);[7]
    b.grid = new Rook(Piece.Color.WHITE);[7]
    b.grid = new Rook(Piece.Color.BLACK);
    b.grid = new Rook(Piece.Color.BLACK);[7]
    // Knights
    b.grid = new Knight(Piece.Color.WHITE);[1][7]
    b.grid = new Knight(Piece.Color.WHITE);[6][7]
    b.grid = new Knight(Piece.Color.BLACK);[1]
    b.grid = new Knight(Piece.Color.BLACK);[6]
    // Bishops
    b.grid = new Bishop(Piece.Color.WHITE);[2][7]
    b.grid = new Bishop(Piece.Color.WHITE);[5][7]
    b.grid = new Bishop(Piece.Color.BLACK);[2]
    b.grid = new Bishop(Piece.Color.BLACK);[5]
    // Queens
    b.grid = new Queen(Piece.Color.WHITE);[3][7]
    b.grid = new Queen(Piece.Color.BLACK);[3]
    // Kings
    b.grid = new King(Piece.Color.WHITE);[4][7]
    b.grid = new King(Piece.Color.BLACK);[4]
    return b;
}

public Position findKing(Piece.Color color) {
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            Piece p = grid[r][c];
            if (p instanceof King && p.color() == color) return new Position(r, c);
        }
    }
    return null;
}

public boolean isOccupied(Position p) { return get(p) != null; }

public boolean isEnemy(Position p, Piece.Color color) {
    Piece q = get(p);
    return q != null && q.color() != color;
}

public boolean isEmpty(Position p) { return get(p) == null; }

public boolean inCheck(Piece.Color color) {
    Position king = findKing(color);
    if (king == null) return false;
    Piece.Color enemy = (color == Piece.Color.WHITE) ? Piece.Color.BLACK : Piece.Color.WHITE;
    // For every enemy piece, see if it attacks king
    for (int r = 0; r < 8; r++) {
        for (int c = 0; c < 8; c++) {
            Piece p = grid[r][c];
            if (p != null && p.color() == enemy) {
                Position from = new Position(r, c);
                for (Position tgt : p.legalTargets(this, from)) {
                    if (tgt.equals(king)) return true;
                }
            }
        }
    }
    return false;
}

public boolean tryMoveLegal(Position from, Position to, Piece.Color sideToMove) {
    Piece moving = get(from);
    if (moving == null || moving.color() != sideToMove) return false;
    List<Position> targets = moving.legalTargets(this, from);
    boolean ok = targets.stream().anyMatch(to::equals);
    if (!ok) return false;
    // simulate and check self-check
    Board copy = this.copy();
    copy.set(to, moving);
    copy.set(from, null);
    if (copy.inCheck(sideToMove)) return false;
    // commit
    set(to, moving);
    set(from, null);
    return true;
}

public void printAscii() {
    for (int r = 0; r < 8; r++) {
        System.out.print(8 - r + " ");
        for (int c = 0; c < 8; c++) {
            Piece p = grid[r][c];
            char ch = '.';
            if (p != null) ch = p.fenChar();
            System.out.print(ch + " ");
        }
        System.out.println();
    }
    System.out.println("  a b c d e f g h");
}
