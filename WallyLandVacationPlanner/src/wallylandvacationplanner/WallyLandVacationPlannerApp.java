package wallylandvacationplanner;

import controller.ticketsandpasses.PassesController;
import view.loginsignup.login.LoginView;
import javax.swing.SwingUtilities;
import view.MainPageView;
import view.passes.tiketsandpasses.PassesView;
import view.passes.tiketsandpasses.TicketsView;

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

        SwingUtilities.invokeLater(MainPageView::new);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            PassesController controller = new PassesController(); // Ensure this is your singleton or relevant instance
            controller.clearCartFiles();
        }));
    }

}
