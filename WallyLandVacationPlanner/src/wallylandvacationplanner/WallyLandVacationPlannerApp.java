package wallylandvacationplanner;

import view.MainPageView;
import view.loginsignup.UserView;
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
                new UserView();
//                new MainPageView();
            }

        });

    }

}
