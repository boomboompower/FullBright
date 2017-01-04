/*
 *     Copyright (C) 2016 boomboompower
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.boomboompower.brightness;

import me.boomboompower.brightness.utils.Writer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class FullBrightCommand implements ICommand {

    private final List<String> aliases;

    private final List<String> subcommands;

    public FullBrightCommand() {
        aliases = new ArrayList<String>();
        aliases.add("fullbright");
        aliases.add("gamma");
        aliases.add("fb");

        subcommands = new ArrayList<String>();
        subcommands.add("setmax");
    }

    @Override
    public String getCommandName() {
        return "fullbright";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return String.format("Usage: /%s <brightness, setmax>", getCommandName());
    }

    @Override
    public List<String> getCommandAliases() {
        return aliases;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sendMessage(getCommandUsage(sender), MessageType.NORMAL);
        } else if (args[0] != null) {
			int val = 0;
            try {
                if (args[0].equalsIgnoreCase("setmax")) {
					val = 1;
                    GameSettings.Options.GAMMA.setValueMax(Float.valueOf(args[1] != null ? args[1] : "1"));
                } else {
					val = 0;
                    FullBright.setLevel(Float.valueOf(args[0]));
                    execute();
                }
            } catch (Exception var12) {
                sendMessage("Your number was not valid! Try again.", MessageType.ERROR);
                sendMessage(String.format("Your input was \"%s\". Make sure it\'s a float", args[val]), MessageType.ERROR);
            }

        }
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return args.length == 1 ? CommandBase.getListOfStringsMatchingLastWord(args, subcommands) : new ArrayList<String>();
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    private void sendMessage(String message, MessageType type) {
        Minecraft.getMinecraft().thePlayer.addChatComponentMessage(new ChatComponentText(type.getFormat() + message));
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }

    private void execute() {
        Writer.execute();
    }

    enum MessageType {
        NOTE(EnumChatFormatting.GRAY + "F" + EnumChatFormatting.DARK_GRAY + "B"),
        NORMAL(EnumChatFormatting.AQUA + "F" + EnumChatFormatting.BLUE + "B"),
        WARNING(EnumChatFormatting.YELLOW + "F" + EnumChatFormatting.GOLD + "B"),
        ERROR(EnumChatFormatting.RED + "F" + EnumChatFormatting.DARK_RED + "B");

        private String prefix;

        MessageType(String message) {
            prefix = message + EnumChatFormatting.DARK_GRAY + " > " + EnumChatFormatting.GRAY;
        }

        public String getFormat() {
            return prefix;
        }
    }

}
