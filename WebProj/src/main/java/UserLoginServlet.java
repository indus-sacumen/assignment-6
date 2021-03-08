import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/EnterDetails")
public class UserLoginServlet extends HttpServlet {

    public UserLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UserModel usermodel = new UserModel();
        usermodel.setUserid(userName);
        usermodel.setPassword(password);

        UserDAO userDao = new UserDAO();
        String userValidate="";
      try {
          userValidate= userDao.authenticateUser(usermodel); //Calling authenticateUser function
          }
       catch(Exception e)
         {
           e.printStackTrace();
          }
        if(userValidate.equals("SUCCESS")) //If function returns success string then user will be rooted to Success page
        {
            request.setAttribute("userName", userName); //with setAttribute() you can define a "key" and value pair so that you can get it in future using getAttribute("key")
            request.getRequestDispatcher("/Success.html").forward(request, response);//RequestDispatcher is used to send the control to the invoked page.
        }
        else
        {
            request.setAttribute("errMessage", userValidate); //If authenticateUser() function returnsother than SUCCESS string it will be sent to Error page again. Here the error message returned from function has been stored in a errMessage key.
            request.getRequestDispatcher("/Error.html").forward(request, response);//forwarding the request
        }
    }
}
