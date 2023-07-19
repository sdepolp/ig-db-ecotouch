package cl.ecotouch.msauth.utils;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;


@Slf4j
public class JwtUtils {
    public static boolean decodeJwtToken(String token, String secretKey) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secretKey);

            if (signedJWT.verify(verifier)) {
                JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
                // Realizar las validaciones necesarias en base a los claims del token
                return true;
            }
        } catch (ParseException e) {
            // El token no pudo ser parseado correctamente
            log.info(e.getMessage());
        } catch (Exception e) {
            // El token no es válido o ocurrió algún error en la validación
            log.info(e.getMessage());
        }
        return false;
    }
}
