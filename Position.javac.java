public Position(int row, int col) {
    this.row = row;
    this.col = col;
}

public static Position fromAlgebraic(String s) {
    if (s == null || s.length() != 2) throw new IllegalArgumentException("Bad coord: " + s);
    char file = Character.toLowerCase(s.charAt(0));
    char rank = s.charAt(1);
    int col = file - 'a';
    int boardRank = rank - '1';
    if (col < 0 || col > 7 || boardRank < 0 || boardRank > 7) throw new IllegalArgumentException("Bad coord: " + s);
    // row 0 is rank 8, row 7 is rank 1
    int row = 7 - boardRank;
    return new Position(row, col);
}

public String toAlgebraic() {
    char file = (char) ('a' + col);
    char rank = (char) ('1' + (7 - row));
    return "" + file + rank;
}

public boolean onBoard() {
    return row >= 0 && row < 8 && col >= 0 && col < 8;
}

@Override
public boolean equals(Object o) {
    if (!(o instanceof Position)) return false;
    Position p = (Position) o;
    return row == p.row && col == p.col;
}

@Override
public int hashCode() {
    return (row << 3) ^ col;
}
