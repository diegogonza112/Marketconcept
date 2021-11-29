package login;

import InputAndOutput.SystemInOut;
import UserFunctions.User;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;


public class PasswordPresenter {

    public String password1;
    public String password2;
    public String password3;

    public void promptPasswordSignUp(User user) throws IOException {
        SystemInOut inOut = new SystemInOut();
        PasswordController passController = new PasswordController();

        inOut.sendOutput("Type your password (At least 5 Characters): ");
        password1 = inOut.getInput();

        if (password1.length() < 5){
            inOut.sendOutput("Password is too short please try again");
            this.promptPasswordSignUp(user);
        }

        inOut.sendOutput("Verify Password is correct: ");
        password2 = inOut.getInput();

        if(passController.verifyPasswordsMatch(password1, password2)){
            passController.setUserPassword(user, password1);
        }
        else{
            inOut.sendOutput("Passwords do not match please try again");
            this.promptPasswordSignUp(user);
        }
    }

    public void promptPasswordSignIn(User user) throws IOException {
        SystemInOut inOut = new SystemInOut();
        PasswordController passController = new PasswordController();
        inOut.sendOutput("Now, input your password");

        password3 = inOut.getInput();

        if(passController.verifyPassword(user, password3)){
            inOut.sendOutput("Successfully logged in");
        }
        else{
            inOut.sendOutput("Password is incorrect please try again");
            this.promptPasswordSignIn(user);
        }
    }
}
