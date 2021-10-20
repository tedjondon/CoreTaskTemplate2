package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new jm.task.core.jdbc.util.Util().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS user ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(30), "
                    + "lastName VARCHAR(30), "
                    + "age INT);");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {

            }
        }
    }

    public void dropUsersTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new jm.task.core.jdbc.util.Util().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.execute("DROP TABLE IF EXISTS user;");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {

            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new jm.task.core.jdbc.util.Util().getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?);");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {

                }
            }
        }
    }

    public void removeUserById(long id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new jm.task.core.jdbc.util.Util().getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("DELETE FROM user WHERE id = ?;");
            ps.setLong(1, id);

            ps.execute();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignored) {

                }
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = new jm.task.core.jdbc.util.Util().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM user;");
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                result.add(user);
            }
            conn.commit();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {

            }
        }
        return result;
    }

    public void cleanUsersTable() {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = new jm.task.core.jdbc.util.Util().getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            stmt.execute("TRUNCATE TABLE user;");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
            }
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ignored) {

            }
        }
    }
}
