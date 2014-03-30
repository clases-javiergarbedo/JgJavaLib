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

import java.util.logging.Logger;

/**
 *
 * @author Javier García Escobedo <javiergarbedo.es>
 */
public class JgValidatorUtil {
    
    public static boolean isPasswordValid(char[] password, int minLength, 
            boolean digitRequired, boolean lowerCaseRequired, 
            boolean upperCaseRequired, boolean specialCharRequired) {
        
        boolean hasDigit = false;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasSpecialChar = false;
        
        if(password.length >= minLength) {
            for(int i=0; i<password.length; i++) {
                char c = password[i];
                if(Character.isDigit(c)) {
                    hasDigit = true;
                }
                if(Character.isLetter(c) && Character.isLowerCase(c)) {
                    hasLower = true;
                }
                if(Character.isLetter(c) && !Character.isLowerCase(c)) {
                    hasUpper = true;
                }
                if(!Character.isDigit(c) && !Character.isAlphabetic(c)) {
                    hasSpecialChar = true;
                }
            }
            //Comprobar si se cumplen todas las condiciones impuestas
            if(digitRequired == hasDigit && lowerCaseRequired == hasLower &&
                    upperCaseRequired == hasUpper && 
                    specialCharRequired == hasSpecialChar) {
                return true;
            } 
        }
        return false;
    }
    
}
