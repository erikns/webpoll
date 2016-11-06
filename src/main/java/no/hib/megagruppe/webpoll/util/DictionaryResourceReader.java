package no.hib.megagruppe.webpoll.util;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class DictionaryResourceReader implements ResourceReader {
    private ServletContext context;

    @Inject
    public DictionaryResourceReader(ServletContext context) {
        this.context = context;
    }

    @Override
    public List<String> readAllLines() {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = getResourceReader()) {
            String line;
            while ((line = reader.readLine()) != null && line.length() != 0) {
                lines.add(line);
            }

            return lines;
        } catch (IOException e) {
            return null;
        }
    }

    private BufferedReader getResourceReader() {
        InputStreamReader isr = new InputStreamReader(context.getResourceAsStream("WEB-INF/dictionary.txt"));
        return new BufferedReader(isr);
    }
}
