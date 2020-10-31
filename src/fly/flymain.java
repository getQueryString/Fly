package fly;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class flymain extends JavaPlugin implements CommandExecutor {

    String noperm = "§7[§cServer§7] §4§l Keine Rechte!";
    String pre = "§7[§cServer§7]";

    public void onEnable() {
        getCommand("fly").setExecutor(new flymain());
        getServer().getConsoleSender().sendMessage("§aDas §3Fly-Plugin §awurde erfolgreich aktiviert!");
    }

    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§4Das §3Fly-Plugin §4wurde erfolgreich deaktiviert!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        Player p = (Player) sender;

        if (!p.hasPermission("fly.fly")) {
            p.sendMessage(noperm);
            return true;
        }

        if (args.length == 0) {

            if (p.getAllowFlight()) {
                p.setAllowFlight(false);
                p.sendMessage(pre + " §cDu kannst nun §4nicht §cmehr fliegen!");
                return true;
            } else {
                p.setAllowFlight(true);
                p.sendMessage(pre + " §aDu kannst nun wieder fliegen!");
            }
            return true;
        }
        if (args.length == 1) {

            Player t = Bukkit.getPlayer(args[0]);

            if (t == null) {
                p.sendMessage(pre + " §cDieser Spieler ist nicht online!");
                return true;
            }

            if (t.getAllowFlight()) {
                t.setAllowFlight(false);
                p.sendMessage(pre + " §cDu hast §2" + args[0] + " §cden Flugmodus entzogen!");
            } else {
                t.setAllowFlight(true);
                t.sendMessage(pre + " §aDu hast §2" + args[0] + " §aden Flugmodus gegeben!");
            }
            return true;
        }
        return false;
    }
}