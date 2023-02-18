package tech.bcs.switchmon.config;

import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import tech.bcs.switchmon.generic.config.Contracts;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class YamlConfigReaderTest {

    @Test
    public void whenLoadYAMLDocumentWithTopLevelClass_thenLoadCorrectJavaObjectWithNestedObjects() {

        Yaml yaml = new Yaml(new Constructor(Contracts.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("simple_contract.yaml");
        Contracts contracts = yaml.load(inputStream);

        assertThat(contracts.getContracts()).isNotNull();

        assertThat(contracts.getContracts().get(0).getAddress()).isEqualTo("0x1c1d5AABe949AFD8744Eb06E16F76b0dc0AdD33f");
        assertThat(contracts.getContracts().get(0).getName()).isEqualTo("Bexchainge");
        assertThat(contracts.getContracts().get(0).getEvents()).hasSize(3);
    }
    @Test
    public void whenLoadYAMLDocumentWithTopLevelClass_thenLoadCorrectJavaObjectWithCorrectNestedObjects() {

        Yaml yaml = new Yaml(new Constructor(Contracts.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("simple_contract.yaml");
        Contracts contracts = yaml.load(inputStream);

        assertThat(contracts.getContracts()).isNotNull();

        assertThat(contracts.getContracts().get(0).getAddress()).isEqualTo("0x1c1d5AABe949AFD8744Eb06E16F76b0dc0AdD33f");
        assertThat(contracts.getContracts().get(0).getName()).isEqualTo("Bexchainge");
        assertThat(contracts.getContracts().get(0).getEvents()).hasSize(3);
        assertThat(contracts.getContracts().get(0).getEvents().get(0).getName()).isEqualTo("Created(address,address,uint256)");
        assertThat(contracts.getContracts().get(0).getEvents().get(0).getStart()).isEqualTo(1);
        assertThat(contracts.getContracts().get(0).getEvents().get(1).getName()).isEqualTo("Financed(address,address,uint256)");
        assertThat(contracts.getContracts().get(0).getEvents().get(1).getStart()).isEqualTo(2);
        assertThat(contracts.getContracts().get(0).getEvents().get(2).getName()).isEqualTo("Settled(address,address,uint256)");
        assertThat(contracts.getContracts().get(0).getEvents().get(2).getStart()).isEqualTo(3);
    }
}
