package resources;

import java.util.ListResourceBundle;

public class Resource_us_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {

        return resources;
    }

    private final Object[][] resources = {

            {"/startBUTTONTEXT", "Hello"},
            {"startCOMMANDRESULTTEXT", "Change to EN"},

    };
}
