package de.sesosas.simpletablist.commands;

import de.sesosas.simpletablist.SimpleTabList;
import de.sesosas.simpletablist.classes.CustomConfig;
import de.sesosas.simpletablist.message.MessageHandler;
import de.sesosas.simpletablist.permissions.PermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand {

    public static void Do(Player player, String[] args){
        if(SimpleTabList.getPlugin().config.getBoolean("Homes.Use")){
            if(args.length >= 2){
                CustomConfig.setup(player);
                FileConfiguration con = CustomConfig.get();
                if(args[1].equalsIgnoreCase("add")){
                    if(PermissionsHandler.hasPermission(player, "stl.home.add")){
                        if(args.length >= 3){
                            for(int i = 1; i <= SimpleTabList.getPlugin().config.getInt("Homes.Amount"); i++){
                                if(con.get("Homes."+i) == null){
                                    con.set("Homes."+i+".Name", args[2]);
                                    con.set("Homes."+i+".World", player.getWorld().getName());
                                    con.set("Homes."+i+".X", player.getLocation().getX());
                                    con.set("Homes."+i+".Y", player.getLocation().getY());
                                    con.set("Homes."+i+".Z", player.getLocation().getZ());
                                    con.set("Homes."+i+".Yaw", player.getLocation().getYaw());
                                    con.set("Homes."+i+".Pitch", player.getLocation().getPitch());
                                    CustomConfig.save();
                                    MessageHandler.Send(player, ChatColor.AQUA + "New Home called "+con.getString("Homes."+i+".Name")+" saved!");
                                    break;
                                }
                                else if(con.getString("Homes."+i+".Name").equalsIgnoreCase("Deleted")){
                                    con.set("Homes."+i+".Name", args[2]);
                                    con.set("Homes."+i+".World", player.getWorld().getName());
                                    con.set("Homes."+i+".X", player.getLocation().getX());
                                    con.set("Homes."+i+".Y", player.getLocation().getY());
                                    con.set("Homes."+i+".Z", player.getLocation().getZ());
                                    con.set("Homes."+i+".Yaw", player.getLocation().getYaw());
                                    con.set("Homes."+i+".Pitch", player.getLocation().getPitch());
                                    CustomConfig.save();
                                    MessageHandler.Send(player, ChatColor.AQUA + "New Home called "+con.getString("Homes."+i+".Name")+" saved!");
                                    break;
                                }
                                if(i == SimpleTabList.getPlugin().config.getInt("Homes.Amount")){
                                    MessageHandler.Send(player, ChatColor.RED + "You cannot add more Homes!");
                                }
                            }
                        }
                        else{
                            MessageHandler.Send(player, ChatColor.YELLOW + "You need to provide a name!");
                        }
                    }
                    else{
                        MessageHandler.Send(player, ChatColor.RED + "You are not allowed to use this command!");
                    }
                }
                else if(args[1].equalsIgnoreCase("set")){
                    if(PermissionsHandler.hasPermission(player, "stl.home.add")){
                        if(args.length >= 3){
                            for(int i = 1; i <= SimpleTabList.getPlugin().config.getInt("Homes.Amount"); i++){
                                if(con.getString("Homes."+i+".Name").equalsIgnoreCase(args[2])){
                                    con.set("Homes."+i+".Name", args[2]);
                                    con.set("Homes."+i+".World", player.getWorld().getName());
                                    con.set("Homes."+i+".X", player.getLocation().getX());
                                    con.set("Homes."+i+".Y", player.getLocation().getY());
                                    con.set("Homes."+i+".Z", player.getLocation().getZ());
                                    con.set("Homes."+i+".Yaw", player.getLocation().getYaw());
                                    con.set("Homes."+i+".Pitch", player.getLocation().getPitch());
                                    CustomConfig.save();
                                    MessageHandler.Send(player, ChatColor.AQUA + "Home called "+con.getString("Homes."+i+".Name")+" changed and saved!");
                                    break;
                                }
                            }
                        }
                        else{
                            MessageHandler.Send(player, ChatColor.RED + "You need to provide a name!");
                        }
                    }
                    else{
                        MessageHandler.Send(player, ChatColor.RED + "You are not allowed to use this command!");
                    }
                }
                else if(args[1].equalsIgnoreCase("remove")){
                    if(PermissionsHandler.hasPermission(player, "stl.home.add")){
                        if(args.length >= 3){
                            for(int i = 1; i <= SimpleTabList.getPlugin().config.getInt("Homes.Amount"); i++){
                                if(con.getString("Homes."+i+".Name").equalsIgnoreCase(args[2])){
                                    con.set("Homes."+i+".Name", "Deleted");
                                    con.set("Homes."+i+".World", "Deleted");
                                    con.set("Homes."+i+".X", "0");
                                    con.set("Homes."+i+".Y", "0");
                                    con.set("Homes."+i+".Z", "0");
                                    con.set("Homes."+i+".Yaw", "0");
                                    con.set("Homes."+i+".Pitch", "0");
                                    CustomConfig.save();
                                    MessageHandler.Send(player, ChatColor.AQUA + "Home called "+args[2]+" got deleted!");
                                    break;
                                }
                            }
                        }
                        else{

                        }
                    }
                }
                else{
                    for(int i = 1; i <= SimpleTabList.getPlugin().config.getInt("Homes.Amount"); i++){
                        if(args[1].equalsIgnoreCase(con.getString("Homes."+i+".Name"))){
                            if(PermissionsHandler.hasPermission(player, "stl.home")){
                                World w = Bukkit.getWorld(con.getString("Homes."+i+".World"));
                                float x = Float.parseFloat(con.getString("Homes."+i+".X"));
                                float y = Float.parseFloat(con.getString("Homes."+i+".Y"));
                                float z = Float.parseFloat(con.getString("Homes."+i+".Z"));
                                float yaw = Float.parseFloat(con.getString("Homes."+i+".Yaw"));
                                float pitch = Float.parseFloat(con.getString("Homes."+i+".Pitch"));
                                Location l = new Location(w, x, y, z, yaw, pitch);
                                player.teleport(l);
                                MessageHandler.Send(player, ChatColor.AQUA + "You have been teleported to home \""+con.getString("Homes."+i+".Name")+"\"");
                            }
                        }
                    }
                }
            }
        }
    }

}
