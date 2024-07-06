package controllers.validators;

import utils.models.Result;
import views.console.MessageRepository;

public class FormatStringValidator extends DataInputValidator {

    public FormatStringValidator(DataInputValidator next) {
        super(next);
    }

    @Override
    protected Result<String, String> specificallyValidate(String validatable) {

        Result<String, String> result =new Result<>(MessageRepository.getInstance().get("sudoku.put-view.put.error"),
                null);

        int vLength = validatable.length();

        String coordinate = validatable.split(":")[0];


        String valueType = validatable.length() > 2 && validatable.contains(":")
            ? validatable.split(":")[1]
            : null;

         char value = valueType != null
                ? valueType.charAt(0)
                : 0;

        char type = valueType != null && valueType.length() == 2
                ? valueType.charAt(1)
                : 0;

        if(vLength < 2 || vLength >5){
            return result;
        }

        if ((vLength == 5 || vLength == 3) && type == 0){
            return result;
        }

        String patter = "[?h\\-+.]";

        if ((vLength == 5 || vLength == 3) && !String.valueOf(type).matches(patter)){
            return result;
        }

        if(vLength > 3 && ! validatable.contains(":")){
            return result;
        }

        if(vLength > 3 && value == 0 ){
            return result;
        }

        if(coordinate.length() != 2){
            return result;
        }

        return  new Result<>(null, null);
    }
}
