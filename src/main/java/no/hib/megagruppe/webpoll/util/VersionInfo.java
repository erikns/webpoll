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
    private final String revision;

    public VersionInfo(ServletContext servletContext) {
        version = gatherVersionFromPackage(servletContext);
        buildNumber = gatherBuildNumberFromResource(servletContext);
        revision = gatherRevisionFromResource(servletContext);
    }

    private String gatherVersionFromPackage(ServletContext servletContext) {
        String version;
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
        return version;
    }

    private String gatherBuildNumberFromResource(ServletContext servletContext) {
        return readFromResource(servletContext, "WEB-INF/buildNumber");
    }

    private String gatherRevisionFromResource(ServletContext servletContext) {
        return readFromResource(servletContext, "WEB-INF/revision");
    }

    private static String readFromResource(ServletContext servletContext, String resourcePath) {
        String tmp = "X";
        InputStream is = servletContext.getResourceAsStream(resourcePath);
        if (is != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            try {
                tmp = br.readLine();
            } catch (IOException e) {
                tmp = "DEV";
            }
        }
        return tmp;
    }

    private String getVersion() {
        return version;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    public String getRevision() { return revision; }

    public String getVersionString() {
        return getVersion() + "-" + getBuildNumber();
    }
}
