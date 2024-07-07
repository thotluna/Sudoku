package controllers;

public class Interpreter {

    private final GameController controller;

    public Interpreter(GameController controller) {
        this.controller = controller;
    }

    public void interprets(String data) {

        if(data.contains("/")) {
            if(data.length() == 4){
                controller.addCell(data.replace('/', ':'));
                return;
            }
            String[] dataArr = data.split("/");
            if(dataArr.length != 2) return;
            if(dataArr[1].length() == 2 && dataArr[1].charAt(1) == '+'){
                controller.addCellHighlight(data);
                return;
            }

        }

        if(data.contains(".")){
            controller.helpCell(data);
            return;
        }

        if(data.contains("+") || data.matches("[A-Ia-i][1-9]")){
            controller.addCellHighlight(data);
        }

        if(data.contains("-")){
            controller.deleteCell(data);
        }


    }
}
