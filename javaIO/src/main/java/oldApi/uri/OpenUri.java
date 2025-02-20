package oldApi.uri;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class OpenUri {

    public static void main(String[] args) {
        URI uri = URI.create("https://www.youtube.com/watch?v=W8XArVpt-lM&list=LL&index=40");
        try {
            Desktop.getDesktop().browse(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
