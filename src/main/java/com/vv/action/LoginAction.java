package com.vv.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.vv.form.UserForm;

public class LoginAction extends Action {
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Cast the ActionForm to the specific UserForm
        UserForm userForm = (UserForm) form;

        // Get data from the form
        String username = userForm.getUsername();
        String password = userForm.getPassword();

        // Logic to validate or process user data
        if ("admin".equals(username) && "password".equals(password)) {
            request.setAttribute("message", "Login successful!");
            return mapping.findForward("success");
        } else {
            request.setAttribute("message", "Login failed. Try again.");
            return mapping.findForward("failure");
        }
    }
}
