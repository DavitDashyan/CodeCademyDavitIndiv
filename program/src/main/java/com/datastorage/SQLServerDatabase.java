package com.datastorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerDatabase {
        private String url = "jdbc:sqlserver://aei-sql2.avans.nl:1443;databaseName=CodeAcademyOffice;";
        private String user = "boomkip";
        private String password = "KipBoom123";
        private Connection connection;
        private static SQLServerDatabase instance;

        public SQLServerDatabase(String url, String user, String password) {
            this.url = url;
            this.user = user;
            this.password = password;
        }


        public SQLServerDatabase(){
        }

        public Connection getConnection() {
            return connection;
        }

        public void connect() throws SQLException {
            String connectionUrl = url + ";user=" + this.user + ";password=" + this.password;
        DriverManager.setLoginTimeout(500);
        try {
            this.connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            this.connection = null;
            throw e;
        }
        } 

        public static SQLServerDatabase getDatabase(){
            if (instance == null){
                instance = new SQLServerDatabase();
            }
            return instance;
        }
    
}
