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

package me.boomboompower.brightness.utils;

import me.boomboompower.brightness.FullBright;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Writer implements Runnable {

    private static String lastDate = (new SimpleDateFormat("dd-MM-yyyy '-' HH:mm:ss a")).format(new Date());
    private static String ls = System.lineSeparator();

    public Writer() {
    }

    public static void execute() {
        new Thread(new Writer()).start();
    }

    public void run() {
        try {
            FileWriter e = new FileWriter(FullBright.USER_DIR + FullBright.FILENAME);

            this.write(e, FullBright.level + ls);
            this.write(e, "Last modified: " + lastDate);

            e.close();
        } catch (Exception var56) {
            var56.printStackTrace();
        }
    }

    private void write(FileWriter writer, String text) {
        try {
            writer.write(text);
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }
}
