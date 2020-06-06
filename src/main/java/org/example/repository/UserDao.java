package org.example.repository;

import org.example.model.User;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class UserDao {
    //STEP 1 Database info
    static final String DBURL ="jdbc:postgresql://localhost:5431/yummy";
    static final String USER = "admin";
    static final String PASS = "password";
    private Logger logger = LoggerFactory.getLogger(getClass());
    public List<User> getUsers(){
        List<User> users = new ArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //STEP 2: Open a connection
            logger.debug("open connection...");
            conn = DriverManager.getConnection(DBURL, USER, PASS);
            //STEP 3: Execute a query
            logger.debug("create statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM users";
            rs = stmt.executeQuery(sql);
            //STEP 4: Extract data from result set
            while(rs.next()) {
                //Retrieve by column name
                Long userId  = rs.getLong("user_id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String username = rs.getString("username");
                Timestamp createdOn = rs.getTimestamp("created_on");
                //Fill the object
                User user = new User();
                user.setUserId(userId);
                user.setEmail(email);
                user.setPassword(password);
                user.setUserName(username);
                user.setCreatedOn(createdOn);
                users.add(user);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        finally {
            //STEP 6: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return users;
    }

    public static void main(String[] args) {
        UserDao userJBDCDao = new UserDao();
        System.out.println(userJBDCDao.getUsers());
    }
}
