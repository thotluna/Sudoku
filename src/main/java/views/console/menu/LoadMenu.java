package views.console.menu;

import controllers.LoadController;
import utils.menu.Menu;


public class LoadMenu extends Menu {

    LoadController controller;


    public LoadMenu(String title, LoadController controller) {
        super(title);
        this.controller = controller;


        for (String nameFile : controller.getListFileName()) {
            this.addCommand(new LoadCommand(nameFile.toUpperCase(), controller));
        }
    }




}
