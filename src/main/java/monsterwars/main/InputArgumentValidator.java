package monsterwars.main;

class InputArgumentValidator {

    private static final String NOT_A_NUMBER_ERROR_MESSAGE = "Not a valid number as a parameter: ";
    private static final String NOT_ENOGH_MONSTERS_ERROR_MESSAGE = "Too few monsters are specified: ";

    static Long validate(String[] args) {
        Long numberOfMonsters = null;
        try {
            numberOfMonsters = Long.valueOf(args[0]);
        } catch (NumberFormatException exception) {
            System.out.println(NOT_A_NUMBER_ERROR_MESSAGE + args[0]);
        }
        if (numberOfMonsters != null && numberOfMonsters < 1)
            System.out.println(NOT_ENOGH_MONSTERS_ERROR_MESSAGE + numberOfMonsters);
        return numberOfMonsters;
    }
}
