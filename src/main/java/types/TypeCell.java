package types;

public enum TypeCell {
    FIXED('f'),
    CANDIDATE('c'),
    HELP('h'),
    HIGHLIGHT('i'),
    ERROR('e');

    private final char min;

    TypeCell(char min) {
        this.min = min;
    }

    public static TypeCell get(char type) {
        for (TypeCell typeCell : TypeCell.values()) {
            if(typeCell.min == type){
                return typeCell;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(min);
    }
}
