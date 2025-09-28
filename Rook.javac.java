@Override
public List<Position> legalTargets(Board board, Position from) {
    List<Position> res = new ArrayList<>();
    Queen.addRays(board, from, res, new int[][]{
        {-1,0},{1,0},{0,-1},{0,1}
    });
    return res;
}
