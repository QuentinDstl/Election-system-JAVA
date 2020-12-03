package dao_package;

import java.sql.SQLException;

public interface DAO {
    
    static final int FIRST_ID_CANDIDATE = 1;
    static final int FIRST_ID_ELECTOR = 101;
    static final int FIRST_ID_OFFICIAL = 51;
    static final int NUMBER_OF_STATES = 51;
    
    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;
    public void addToTable(String... args) throws SQLException, IllegalArgumentException;
}