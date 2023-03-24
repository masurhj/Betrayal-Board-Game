package betrayal;

import betrayal.player.PlayerLoader;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class JSONLoader {

    private JSONLoader() {

    }

    /**
     * @param path
     * @return A JSONArray objected created by a specified file.
     */
    public static JSONArray loadFromJSON(final String path) {
        JSONArray jsonList = null;
        JSONParser jsonParser = new JSONParser();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(PlayerLoader.class.getResource(path).getPath());
            try (Reader reader = new InputStreamReader(inputStream, "UTF-8")) {
                Object obj = jsonParser.parse(reader);
                jsonList = (JSONArray) obj;

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return jsonList;
    }
}
