package at.izanamineko.system.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqlconnect {

    mysqlConfigManager mysqlc = new mysqlConfigManager();

    private String host = this.mysqlc.getConfig().getString("MySQL.Host");
    private String port = this.mysqlc.getConfig().getString("MySQL.Port");
    private String database = this.mysqlc.getConfig().getString("MySQL.Database");
    private String username = this.mysqlc.getConfig().getString("MySQL.username");
    private String password = this.mysqlc.getConfig().getString("MySQL.password");

    private Connection connection;

    public boolean isConnected(){
        return(connection == null ? false : true);
    }

    public void connect() throws ClassNotFoundException, SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://" +
                        host + ":" + port + "/" + database + "?useSSL=false",
                username, password);
    }

    public void disconnect(){
        if(isConnected()){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
