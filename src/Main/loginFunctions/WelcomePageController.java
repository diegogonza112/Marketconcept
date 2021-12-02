package loginFunctions;
import inputOutputFunctions.InOut;
import options.EnglishOptionsPresenter;
import options.UserOptionsController;
import userFunctions.User;
import java.io.IOException;

/**
 * Take user input for what they want to do upon seeing the login page.
 *
 */

public class WelcomePageController {

    /**
     * Use case class that decides which classes to call, given the input from the user. Then, calls the methods
     * needed to perform the respective functions.
     */
    public void userLoginDecision(InOut input) throws IOException {
        WelcomePagePresenter presenter = new WelcomePagePresenter();
        presenter.decision(input);
        String userDecision = input.getInput();

            try {
                if (userDecision.equals("1")) {
                    // sign in
                    SignInController signInCont = new SignInController();
                    String username = signInCont.getUsername(input);
                    // the user is directed to option page
                    GetUserGateway signIn = new GetUserGateway();
                    User signedInUser = signIn.getUser(username);
                    UserOptionsController options = new UserOptionsController(signedInUser);
                    EnglishOptionsPresenter engPresenter = new EnglishOptionsPresenter();
                    options.getOption(input, engPresenter);
                    this.userLoginDecision(input);


                } else if (userDecision.equals("2")) {
                    SignUpController signUpController = new SignUpController();
                    signUpController.getNewUsername(input);
                    // recurse back to login page after sign up to then sign in
                    this.userLoginDecision(input);

                } else if (userDecision.equals("3")) {
                    // need to keep this as an empty return statement, otherwise most tests can't run
                    return;
                }
                else {
                    input.sendOutput("Try again");
                    this.userLoginDecision(input);
                }
            } catch (ClassNotFoundException exception) {
                exception.printStackTrace();
            }
        }

    }





