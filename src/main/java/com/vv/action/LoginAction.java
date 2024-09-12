package com.vv.action;

import com.vv.form.UserForm;
import com.vv.util.DatabaseUtil;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginAction extends Action {
    public ActionForward execute( ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response ) {
        // Cast the ActionForm to the specific UserForm
        UserForm userForm = ( UserForm ) form;

        // Get data from the form
        String username = userForm.getUsername();
        String password = userForm.getPassword();

        // Logic to validate or process user data
        Connection connection = null;
        try {
            // Get connection from DatabaseUtil
            connection = DatabaseUtil.getConnection();

            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement( query );
            ps.setString( 1, username );
            ps.setString( 2, password );
            ResultSet rs = ps.executeQuery();

            if ( rs.next() ) {
                // Login success, forward to welcome page
                System.out.println( "vv login success" );
                request.setAttribute( "message", "Login successful!" );
                return mapping.findForward( "success" );
            } else {
                // Login failed, forward to login page
                System.out.println( "vv login failure" );
                return mapping.findForward( "failure" );
            }

        } catch ( Exception e ) {
            System.out.println( "vv error: " + e.getMessage() );
            return mapping.findForward( "failure" );
        } finally {
            // Close the connection
            DatabaseUtil.closeConnection( connection );
        }
    }
}
