package AppServer;

import java.sql.*;
import com.softtech.jdbc.*;

/**
 *
 * @author Fülöp Levente
 */
public class Database{
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/TEST";

    static final String USER = "root";
    static final String PASS = "";
    
    Connection conn = null;

    public String testQuery(){
        ConnectionPool conPool = new ConnectionPool(1, JDBC_DRIVER, DB_URL, USER, PASS);
        SQLExecutor sqlExec = new SQLExecutor(conPool);
        SQLResults res = sqlExec.runQueryCloseCon("SELECT * FROM TEST");
        return res.toString();
    }

}
