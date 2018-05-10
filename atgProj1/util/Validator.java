
/**
 * @author sergiosd
 */

public class Validator {

    /**
     * Validate part of the entry from user.
     * @param commands a string with arguments. E.g. "1 2 3" where 1 is the 'out vertex', is the
     *                 'in vertex' and 3 is the weight
     * @throws Exception if the commands are wrong, with the cause.
     */
    public static void validateCommands(String commands) throws Exception {
        // TODO arrumar as exceptions daqui depois
        String[] line = commands.split(" ");
        if (line.length < 2) {
            throw new IllegalArgumentException("Cannot create a connection. Missing arguments");
        } else if (line.length > 3) {
            throw new IllegalArgumentException("Cannot create a connection. Exceeding arguments");
        }

        try {
            int connectionA = Integer.parseInt(line[0]);
            int connectionB = Integer.parseInt(line[1]);
            if (line.length == 3) {
                double weight = Double.parseDouble(line[2]);
            }
        } catch (Exception e) {
            throw new Exception("Cannot create a connection. " + e.getMessage());
        }


    }


}
