package dao_package;

import java.sql.SQLException;

public interface DAO {
    
    static final int FIRST_ID_CANDIDATE = 0;
    static final int FIRST_ID_ELECTOR = 100;
    static final int FIRST_ID_OFFICIAL = 50;
    static final int NUMBER_OF_STATES = 51;
    
    public void createTable() throws SQLException;
    public void dropTable() throws SQLException;
    public void addToTable(String... args) throws SQLException, IllegalArgumentException;
}