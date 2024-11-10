package wallylandvacationplanner;

import view.MainPageView;
import view.loginsignup.UserView;
import javax.swing.SwingUtilities;
import controller.foodordering.*;
import view.foodordering.*;

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
        OrderViewForm orderViewForm = new OrderViewForm();
        FoodController controller = new FoodController(orderViewForm); 
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new UserView();
////                new MainPageView();
//            }
//
//        });

    }

}
