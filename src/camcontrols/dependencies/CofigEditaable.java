package camcontrols.dependencies;

import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 */
public interface CofigEditaable
{
    /**
     * 
     * @return 
     */
    public String getConfigPath();
    
    /**
     * 
     * @return 
     */
    public ArrayList<String> getParsedConfig();
}
