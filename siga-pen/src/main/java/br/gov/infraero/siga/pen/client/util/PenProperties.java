package br.gov.infraero.siga.pen.client.util;

import org.jboss.logging.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PenProperties {

    private static final Logger LOG = Logger.getLogger(PenProperties.class);

    private PenProperties(){
    }

    public static String getValue(String key) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = PenProperties.class.getClassLoader().getResourceAsStream(
                    "siga-pen.properties");
            prop.load(input);
        } catch (IOException e) {
            LOG.error("IOException: ", e);
        }

        return prop.getProperty(key);
    }
}
