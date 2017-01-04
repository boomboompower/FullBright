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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

    public FileUtils() {
    }

    public static void getVars() throws Throwable {
        try {
            File e = new File(FullBright.USER_DIR);
            if (!e.exists()) e.mkdirs();

            boolean executeWriter = false;
            BufferedReader f;
            List lines;

            if (exists(FullBright.USER_DIR + FullBright.FILENAME)) {
                f = new BufferedReader(new FileReader(FullBright.USER_DIR + FullBright.FILENAME));

                try {
                    lines = f.lines().collect(Collectors.toList());
                    if (lines.size() >= 0) {
                        FullBright.level = Float.valueOf((String) lines.get(0));
                    } else {
                        executeWriter = true;
                    }
                } catch (Exception var31) {
                    var31.printStackTrace();
                }
            }
            if (executeWriter) Writer.execute();
        } catch (IOException var32) {
            var32.printStackTrace();
        }
    }

    private static boolean exists(String path) {
        return Files.exists(Paths.get(path));
    }
}
