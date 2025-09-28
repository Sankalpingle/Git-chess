@Override
public List<Position> legalTargets(Board board, Position from) {
    List<Position> res = new ArrayList<>();
    addRays(board, from, res, new int[][]{
        {-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}
    });
    return res;
}

static void addRays(Board b, Position from, List<Position> out, int[][] dirs) {
    for (int[] dir : dirs) {
        int r = from.row + dir, c = from.col + dir;[1]
        while (r >= 0 && r < 8 && c >= 0 && c < 8) {
            Position to = new Position(r, c);
            Piece q = b.get(to);
            if (q == null) {
                out.add(to);
            } else {
                if (q.color() != b.get(from).color()) out.add(to);
                break;
            }
            r += dir; c += dir;[1]
        }
    }
}
