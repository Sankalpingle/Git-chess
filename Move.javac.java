@Override
public List<Position> legalTargets(Board board, Position from) {
    List<Position> res = new ArrayList<>();
    int[] d = {-1, 0, 1};
    for (int dr : d) for (int dc : d) {
        if (dr == 0 && dc == 0) continue;
        int nr = from.row + dr, nc = from.col + dc;
        if (nr < 0 || nr > 7 || nc < 0 || nc > 7) continue;
        Position to = new Position(nr, nc);
        Piece q = board.get(to);
        if (q == null || q.color() != this.color) res.add(to);
    }
    return res;
}
