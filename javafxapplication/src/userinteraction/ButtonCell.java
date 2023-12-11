/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userinteraction;

/**
 *
 * @author Administrator
 */
import functions.DeleteInformationDB;
import functions.User;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;


public class ButtonCell extends TableCell<User, Void> {
    private final Button button;
    private final ObservableList<User> userList ;
    
   public ButtonCell(String buttonText,ObservableList<User>userList ) {
    this.button = new Button(buttonText);
       this.userList   = userList ;
    this.button.setOnAction(event -> {
        User user = getTableRow().getItem();
        if (user != null) {
            if (buttonText.equals("Delete")) {
                // Code for deleting user
                System.out.println("Button clicked for user: " + user.getUsername());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this user?");

                ButtonType buttonTypeYes = new ButtonType("Yes");
                ButtonType buttonTypeNo = new ButtonType("No");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                alert.showAndWait().ifPresent(response -> {
                    if (response == buttonTypeYes) {
                        DeleteInformationDB db = new DeleteInformationDB();
                        db.deleteuser(user);
                          userList.remove(user);

                        
                    }
                });
            } else if (buttonText.equals("Edit")) {
                // Code for editing user
                System.out.println("Button clicked for editing user: " + user.getUsername());
                // Open a dialog or a new scene for editing user details
                // You can create a method to handle this, or use a different class for editing
            }
        }
    });
}

    @Override
    protected void updateItem(Void item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setGraphic(null);
        } else {
            setGraphic(button);
        }
    }
}
