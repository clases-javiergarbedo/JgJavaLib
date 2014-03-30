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

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier García Escobedo <javiergarbedo.es>
 */
public class JgConversionUtil {

    public static byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    public static String toMd5(char[] input, boolean clearInput) {
        String md5 = null;
        if (input != null) {
            try {
                //Convertir la entrada a array de bytes, ya que así se requiere
                //  para poder codificarla a MD5. No se debe conviertir a String
                //  por cuestiones de seguridad
                byte[] arrayBytesInput = JgConversionUtil.toBytes(input);

                //Realizar la codificación
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] arrayBytesMd5 = md.digest(arrayBytesInput);

                //it is recommended that the returned character array be cleared
                //  after use by setting each character to zero
                Arrays.fill(arrayBytesInput, (byte) 0);
                if(clearInput) {
                    Arrays.fill(input, '\u0000');
                }

                //Convertir a hexadecimal pasándolo a BigInteger
                BigInteger bigIntMd5 = new BigInteger(1, arrayBytesMd5);
                md5 = bigIntMd5.toString(16);

                // Now we need to zero pad it if you actually want the full 32 chars.
                while (md5.length() < 32) {
                    md5 = "0" + md5;
                }
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(JgConversionUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return md5;
    }

}
