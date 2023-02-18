package tech.bcs.switchmon.generic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class ConfigReader {

    private final String configFilePath;

    public ConfigReader(@Value("${config.file.path}") String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public Contracts readYamlConfig() throws FileNotFoundException {
        Yaml yaml = new Yaml(new Constructor(Contracts.class));
        File configFile = new File(configFilePath);
        return yaml.load(new FileReader(configFile));
    }
}
