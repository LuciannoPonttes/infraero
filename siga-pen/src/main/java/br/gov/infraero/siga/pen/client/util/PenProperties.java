package br.gov.infraero.siga.pen.client.util;

import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

public class PenProperties {

    private static final Logger LOG = Logger.getLogger(PenProperties.class);
    private static Properties prop;

    private PenProperties(){
    }

    public static String getValue(String key) {
        InputStream input = null;
        String value = null;
        try {
            value = System.getProperty(key);
            if(prop == null){
                prop = new Properties();
                input = PenProperties.class.getClassLoader().getResourceAsStream(
                        "siga-pen.properties");
                prop.load((new InputStreamReader(input, Charset.forName("UTF-8"))));
            }
            if(value == null || value.isEmpty()){
                value = prop.getProperty(key);
            }
        } catch (IOException e) {
            LOG.error("IOException: ", e);
        }

        return value;
    }
}
