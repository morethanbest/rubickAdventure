package mygame.util;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class YamlReader<T> {

    public T readConfig(String configFile){
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(configFile + ".yaml");
        return (T)yaml.load(inputStream);
    }
}
