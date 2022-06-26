package flash.autostop.config;

import de.maxhenkel.configbuilder.ConfigBuilder;
import de.maxhenkel.configbuilder.ConfigEntry;

public class ServerConfig {

    public final ConfigEntry<Integer> hourToRestart;

    public ServerConfig(ConfigBuilder builder) {
        hourToRestart = builder.integerEntry("stop_at_hour", 9, 0, 23);
    }

}
