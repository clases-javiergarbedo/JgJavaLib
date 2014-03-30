/*
 * Copyright (C) 2014 Javier García Escobedo <javiergarbedo.es>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package es.javiergarbedo.aula.util;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier García Escobedo <javiergarbedo.es>
 */
public class JgSystemUtil {

    public static void restartJarApplication(Class appClass) {
        try {
            final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
            final File currentJar = new File(appClass.getProtectionDomain().getCodeSource().getLocation().toURI());

            /* Build command: java -jar application.jar */
            final ArrayList<String> command = new ArrayList<String>();
            command.add(javaBin);
            command.add("-jar");
            command.add(currentJar.getPath());

            final ProcessBuilder builder = new ProcessBuilder(command);
            builder.start();
            System.exit(0);
        } catch (URISyntaxException ex) {
            Logger.getLogger(JgSystemUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JgSystemUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
