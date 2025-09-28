@Override
public List<Position> legalTargets(Board board, Position from) {
    List<Position> res = new ArrayList<>();
    int dir = (color == Color.WHITE) ? -1 : 1; // white moves up (toward row 0)
    int startRow = (color == Color.WHITE) ? 6 : 1;

    // one step
    int r1 = from.row + dir;
    if (r1 >= 0 && r1 < 8) {
        Position fwd = new Position(r1, from.col);
        if (board.isEmpty(fwd)) {
            res.add(fwd);
            // two steps from start if clear
            if (from.row == startRow) {
                Position fwd2 = new Position(from.row + 2*dir, from.col);
                if (board.isEmpty(fwd2)) res.add(fwd2);
            }
        }
    }
    // captures
    int[] dc = {-1, 1};
    for (int d : dc) {
        int c = from.col + d;
        int r = from.row + dir;
        if (r >= 0 && r < 8 && c >= 0 && c < 8) {
            Position cap = new Position(r, c);
            if (board.isEnemy(cap, color)) res.add(cap);
        }
    }
    // en passant and promotion can be added later
    return res;
}
