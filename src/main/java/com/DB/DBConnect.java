// package com.DB;

// import java.sql.Connection;
// import java.sql.DriverManager;

// public class DBConnect {
//     private static Connection conn;

//     public static Connection getConn() {
//         try {
//             if (conn == null) {
//                 Class.forName("com.mysql.cj.jdbc.Driver");
//                 conn = DriverManager.getConnection(
//                         "jdbc:mysql://localhost:3307/job_portal?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
//                         "root",
//                         "root");
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return conn;
//     }
// }

package com.DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    private static final String URL = "jdbc:mysql://localhost:3307/job_portal?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConn() {
        Connection conn = null;
        try {
            // Driver mới cho MySQL Connector/J 8.x
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
