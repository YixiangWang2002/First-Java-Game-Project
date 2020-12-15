public enum UserType {
    ADMIN('A'),
    PLAYER('P');

    private char type;

    UserType(char _t) {
        this.type = _t;
    }

    public char getType() {
        return this.type;
    }

    public static UserType getTypeFromString(String _t) {
        if(_t.equalsIgnoreCase(String.valueOf(ADMIN.getType()))) {
            return ADMIN;
        }
        return PLAYER;
    }
}
