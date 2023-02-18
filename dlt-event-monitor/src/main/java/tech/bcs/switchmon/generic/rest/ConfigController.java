package tech.bcs.switchmon.generic.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.bcs.switchmon.generic.config.ConfigReader;
import tech.bcs.switchmon.generic.config.Contracts;

@RestController
public class ConfigController {

    private final ConfigReader configReader;

    public ConfigController(ConfigReader configReader) {
        this.configReader = configReader;
    }

    @GetMapping("/api/config")
    public ResponseEntity<Contracts> getConfig() throws Exception {
        return ResponseEntity.ok(configReader.readYamlConfig());
    }
}
