@Override
public ListPosition legalTargets(Board board, Position from) {
    ListPosition res = new ArrayList();
    Queen.addRays(board, from, res, new int[][]{
        {-1,-1},{-1,1},{1,-1},{1,1}
    });
    return res;
}
