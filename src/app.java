import com.mc.integration.configuration.PropertiesManager;
import com.mc.integration.service.ReferenceService;
import com.mc.integration.utils.Help;

import java.io.IOException;
// 47095
public class app {
    public static void main(String[] args) {
        if(args[0].contains("-h")) {
            Help.show();
        } else {
            if(args[0].equals("-bp")) {
                bpOptions(args);
            } else {
                System.out.println("Invalid parameters[0]. Please try again");
            }
        }
    }

    private static void bpOptions(String[] args) {
        try {
            PropertiesManager propertiesManager = new PropertiesManager();
            ReferenceService ref = new ReferenceService(propertiesManager);
            if (args[1].equals("-c1")) {
                ref.sendReference(args[2]);
            } else if(args[1].equals("-c2")) {
                ref.sendContact(args[2], args[3]);
            } else {
                System.out.println("Invalid parameters[1]. Please try again");
            }
        } catch (IOException ex) {
            System.out.println("Error read properties file. please try again.");
        } catch (Exception ex) {
            System.out.println("Some parameters are empty. please try again.");
        }
    }
}
