package utils.models;

public record Result<T, U>(U error, T data) {

    public boolean hasError(){
        return error != null;
    }
}
