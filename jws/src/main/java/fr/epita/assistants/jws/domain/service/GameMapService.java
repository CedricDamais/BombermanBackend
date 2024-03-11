package fr.epita.assistants.jws.domain.service;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ApplicationScoped
public class GameMapService {
    @ConfigProperty(name = "JWS_MAP_PATH", defaultValue = "src/test/resources/map1.rle")
    String mapPath;

    public List<String> getMapPaths()
    {
        List<String> fileContent = null;
        try {
            fileContent = Files.readAllLines(Path.of(mapPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileContent;
    }
}
