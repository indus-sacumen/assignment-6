import java.sql.*;

public class UserDAO {

    public String authenticateUser(UserModel usermodel) throws SQLException, ClassNotFoundException {
        String userName = usermodel.getUserid();
        String password = usermodel.getPassword();
        String userNameDB = "";
        String passwordDB = "";
        String jdbcURL = "jdbc:postgresql://localhost:5432/mydb1";
        String dbUser = "postgres";
        String dbPassword = "clarion";

        Connection connection=null;
        Statement statement=null;
        ResultSet result=null;
        try{
            try
            {
                Class.forName("org.postgresql.Driver");
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
         statement = connection.createStatement();
         result = statement.executeQuery("select userid,password from user");

        while (result.next())
        {
            userNameDB = result.getString("userid"); //fetch the values present in database
            passwordDB = result.getString("password");

            if (userName.equals(userNameDB) && password.equals(passwordDB)) {
                return "SUCCESS"; ////If the user entered values are already present in the database, which means user has already registered so return a SUCCESS message.
            }
        }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return "Invalid user credentials";

    }
}