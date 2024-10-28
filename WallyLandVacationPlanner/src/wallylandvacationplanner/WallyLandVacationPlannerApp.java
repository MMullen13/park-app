package wallylandvacationplanner;

import view.MainPageView;
import view.loginsignup.login.LoginView;
import javax.swing.SwingUtilities;
import view.loginsignup.signup.SignUpView;

/**
 * This is the main class for the WLVP Application
 *
 * @author theme
 */
public class WallyLandVacationPlannerApp {

    /**
     * Application Main Method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginView();
//                new MainPageView();
//                  new SignUpView();

            }

        });

    }

}
