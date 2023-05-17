package com.billystash.renaultautoradiodecode.process;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class RenaultAutoradioDecode {

    /**
     * function to capitalize the precode then decode it
     * @param precode
     * @return code or message bad precode
     */
    public String decodeProcess(String precode) {
        precode = precode.toUpperCase();
        return validPrecode(precode) ? decode(precode) : "Precode invalide";
    }

    /**
     * Function for test precode with regex and don't start with "A0"
     * @param precode
     * @return True if regex match and not start with "A0", else False
     */
    public boolean validPrecode(String precode) {
        return Pattern.compile("^[A-Z]\\d{3}$").matcher(precode).find() && !precode.startsWith("A0");
    }

    /**
     * Function to calculate code thank to precode.
     * @param precode
     * @return code
     */
    private String decode(String precode) {
        int a = precode.charAt(1) + precode.charAt(0) * 10 - 698;
        int b = precode.charAt(3) + precode.charAt(2) * 10 + a - 528;
        double c = (b * 7) % 100;

        // var code comme entier
        double code = Math.floor(c / 10) + (c % 10) * 10 + ((259 % a) % 100) * 100;

        // var code comme chaîne avec 4 chiffres
        return StringUtils.leftPad(String.valueOf((int) code), 4, "0");
    }
}
