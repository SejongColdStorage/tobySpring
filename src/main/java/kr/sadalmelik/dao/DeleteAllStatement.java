package kr.sadalmelik.dao;

class DeleteAllStatement implements  StatementStrategy{

    @Override
    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
        PreparedStatement ps = c.prepareStatement("delete from users");
        return ps;
    }
}