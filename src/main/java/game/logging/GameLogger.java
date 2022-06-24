package game.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GameLogger {
    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger("GameLog");
        FileHandler fileHandler;

        String path = "target/logging";

        LocalDateTime now = LocalDateTime.now();
        String logDate = now.toString();
        logDate = logDate.replace(":","");

        try {
            Path file = Paths.get(path);
            Files.createDirectories(file);
            fileHandler = new FileHandler(path + "/log-" + logDate + ".log");
            LOGGER.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private GameLogger () {}

    public static Logger getLogger () {
        return LOGGER;
    }
}
