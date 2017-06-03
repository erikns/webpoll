package no.hib.megagruppe.webpoll.servlets.meta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Servlet to do a health check of the deployed application. It runs a simple query to verify
 * database connectivity and returns a JSON object with the result. If it is not successful, it
 * also returns the name of the exception and a stacktrace.
 */
@WebServlet("/health")
public class HealthServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "webpollDb")
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // TODO: secure this somehow
        HealthCheckResult result = performChecks();

        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");

        resp.getWriter().print(result.json());
    }

    private HealthCheckResult performChecks() {
        boolean databaseConnectivity = true;
        String databaseException = null;
        String stackTrace = null;
        try {
            Query query = em.createNativeQuery("SELECT 42;");
            Integer result = (Integer)query.getSingleResult();
            if (result != 42)
                throw new AssertionError("Database did something unexpected!");
        } catch (Exception e) {
            databaseConnectivity = false;
            databaseException = e.getClass().getName();

            StringWriter writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            stackTrace = writer.toString();
        }

        return new HealthCheckResult(true, databaseConnectivity,
                databaseException, stackTrace);
    }

    private class HealthCheckResult {
        private final String databaseException;
        private final String stackTrace;
        private final boolean overallResult;
        private final boolean databaseConnectivity;

        HealthCheckResult(boolean overallResult, boolean databaseConnectivity,
                          String databaseException, String stackTrace) {
            this.overallResult = overallResult;
            this.databaseConnectivity = databaseConnectivity;
            this.databaseException = databaseException;
            this.stackTrace = stackTrace;
        }

        String json() {
            return "{" + overallResultJson() + ", " + databaseConnectivityJson() +
                    ", \"databaseError\": {" + databaseErrorJson() + "}}";
        }

        private String overallResultJson() {
            return "\"result\": \"" + (overallResult ? "true" : "false") + "\"";
        }

        private String databaseConnectivityJson() {
            return "\"databaseConnectivity\": \"" + (databaseConnectivity ? "true" : "false") + "\"";
        }

        private String databaseErrorJson() {
            if (!databaseConnectivity) {
                return "\"databaseException\": \"" + databaseException + "\", " +
                        "\"stackTrace\": \"" + stackTrace + "\"}";
            } else {
                return "";
            }
        }
    }
}
