package wallylandvacationplanner;

import controller.MainPage;
import controller.loginsignup.UserController;
import javax.swing.SwingUtilities;

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
                new UserController();
//                new MainPage();
            }

        });

    }

}
