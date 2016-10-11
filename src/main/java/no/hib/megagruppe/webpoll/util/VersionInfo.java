package no.hib.megagruppe.webpoll.util;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Utility class to get the deployed build number. Expects to find the build number
 * in a text file 'buildNumber' under WEB-INF/
 */
public class VersionInfo {
    private final String version;
    private final String buildNumber;

    public VersionInfo(ServletContext servletContext) {
        String tmpVersion = getClass().getPackage().getImplementationVersion();
        if (tmpVersion == null) {
            Properties prop = new Properties();
            try {
                prop.load(servletContext.getResourceAsStream("/META-INF/MANIFEST.MF"));
                tmpVersion = prop.getProperty("Implementation-Version");
            } catch (IOException e) {
                tmpVersion = "DEV";
            }
        }
        version = tmpVersion;

        String tmpBuildNumber = "X";
        InputStream is = servletContext.getResourceAsStream("WEB-INF/buildNumber");
        if (is != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                tmpBuildNumber = br.readLine();
            } catch (IOException e) {
                tmpBuildNumber = "DEV";
            }
        }
        buildNumber = tmpBuildNumber;
    }

    private String getVersion() {
        return version;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public String getVersionString() {
        return getVersion() + "-" + getBuildNumber();
    }
}
