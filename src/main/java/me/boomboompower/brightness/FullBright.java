/*
 *     Copyright (C) 2017 boomboompower
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

import me.boomboompower.brightness.utils.FileUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

import java.io.File;

@Mod(modid = FullBright.MODID, version = FullBright.VERSION_STRING, clientSideOnly = true, acceptedMinecraftVersions = "*")
public class FullBright {

    public static final String MODID = "boomsfullbright";
    public static final String VERSION_STRING = "1.0.0";
    public static final String FILENAME = "options.config";

    public static String USER_DIR;

    public static float level;

    public FullBright() {
        level = Minecraft.getMinecraft().gameSettings.gammaSetting;
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        try {
            registerCommands(new FullBrightCommand());

            USER_DIR = "mods" + File.separator + "fullbright" + File.separator + getPlayerUUID() + File.separator;

            FileUtils.getVars();
        } catch (Throwable var21) {
            var21.printStackTrace();
        }
    }

    private String getPlayerUUID() {
        return Minecraft.getMinecraft().getSession().getProfile().getId().toString();
    }

    public static void setLevel(float newLevel) {
        Minecraft.getMinecraft().gameSettings.gammaSetting = newLevel;
        level = newLevel;
    }

    private void registerCommands(ICommand... toRegister) {
        for (ICommand o : toRegister) {
            try {
                ClientCommandHandler.instance.registerCommand(o);
            } catch (Throwable var31) {
                var31.printStackTrace();
            }
        }
    }
}
