import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author sergiosd
 */

public class FReader {

    /**
     * Read the path passed by user
     * @param path The path of file
     * @return a list made of strings representing the lines of file
     */
    public static ArrayList<String> readFile(String path) throws IOException {

        ArrayList<String> output = new ArrayList<>();

        BufferedReader bReader = null;

        // TODO arrumar as excecoes daqui depois...
        try {
            bReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        while (bReader.ready()) {
            String line = bReader.readLine();
            output.add(line);
        }

        bReader.close();
        return output;

    }
}
