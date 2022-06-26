package flash.autostop;

import de.maxhenkel.configbuilder.ConfigBuilder;
import flash.autostop.config.ServerConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.server.command.ServerCommandSource;

import java.time.LocalTime;

public class Main implements ModInitializer {

    public static ServerConfig SERVER_CONFIG;

    @Override
    public void onInitialize() {
        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            SERVER_CONFIG = ConfigBuilder.build(server.getRunDirectory().toPath().resolve("config").resolve("autostop-server.properties"), ServerConfig::new);
        });

        ServerTickCallback.EVENT.register(listener -> {
            LocalTime now = LocalTime.now();
            int hour = now.getHour();
            int hourToRestart = SERVER_CONFIG.hourToRestart.get();
            if (hour == hourToRestart) {
                ServerCommandSource source = listener.getCommandSource().withSilent();
                listener.getCommandManager().execute(source, "stop");
            }
        });
    }
}
