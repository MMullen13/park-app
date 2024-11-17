# IST 412  
## GROUP 9 - WallyLandVacationPlanner   
## Members: Ana Diru, Mark Mullen, Rebecca Reyes

Use Case #1 - Description submitted by video

Use Case #2 - Food Ordering

The food ordering use case can be started by running the main method found in
WallyLandVacationPlannerApp.java. At this time I have it set to launch straight
to the main landing page so you can run through the new use case without logging 
in each time. If you would like to go through the first use case and go through 
the login, you can comment out new MainPageView(); and uncomment new LoginView();

Once on the main landing page, you can select Dining. When first laucnhed, view
order confirmation will be blank as no order has been created. Clicking on order
History will display past orders. I left some in there to show that the 
data is being persisted between runs.

When selecting new order, you will be greeted with a create order window. You can 
select your eatery and then the type of items you want. Once an item is added to the
order, you can not change the eatery. You can cycle through drinks, sides, mains,
apps and desserts and add them to the order as you wish. You can also
select the quantity you would like. You will be prompted with a popup if you try
to add less than one item or try to complete the order with no items added.
Remove Last Item Added, clear all items, and undo work with the command pattern 
to work with undo functionality.

Once you are happy with your order you can select your pickup time and complete the
order. At this time you will be greeted with the confirmation page. You can exit
out of this page to return to the main page or mark the order as complete. Marking
the order complete simulates the barcode being scanned at pickup. This will now 
clear the order pickup flag and you can create a new new order. Until an order is 
marked as picked up, you will be unable to start a new order. If you "X" out of this 
window you will return to the main menu. To get the barcode again to complete the order,
you can select view order confirmation to bring it back up. This will be blank if you
marked the order complete.
