package wallylandvacationplanner;

import controller.ticketsandpasses.PassesController;
import javax.swing.SwingUtilities;
import view.MainPageView;
import view.loginsignup.login.LoginView;

/**
 * This is the main class for the WLVP Application
 *
 * @author theme
 */
public class WallyLandVacationPlannerApp {

    //FoodController controller;
    /**
     * Application Main Method
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(LoginView::new);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            PassesController controller = new PassesController(); // Ensure this is your singleton or relevant instance
            controller.clearCartFiles();
        }));
    }

}
