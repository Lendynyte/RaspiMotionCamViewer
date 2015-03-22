package camcontrols.configEditing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Dominik Pauli
 * @version 0.3
 */
public class Parser
{

    /**
     * *
     * This method Creates File on designated path with name in the path and
     * writes contents of Arraylist inside each line ending with \r\n
     *
     * @param list Arraylist containing data to write
     * @param path Path where the file will be created
     */
    public void createConfFile(ArrayList<String> list, String path)
    {
        File file;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try
        {
            file = new File(path + "/motion.conf");
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);

            for (String line : list)
            {
                bw.write(line + '\r' + '\n');
            }
            bw.flush();
            fw.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Creating file failed");
        }
        finally
        {
            try
            {
                if (bw != null)
                {
                    bw.close();
                }

                if (fw != null)
                {
                    fw.close();
                }

            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("There was error closing streams");
            }
        }
    }

    /**
     * This method read file specified in path input and parses it to an
     * provided Arraylist.
     *
     * @param list This is output arraylist
     * @param path This is a path of file we are parsing
     *
     */
    public void loadConfLines(ArrayList<String> list, String path)
    {
        FileReader fr = null;
        BufferedReader br = null;
        String currentLine = null;

        try
        {
            fr = new FileReader(path);
            br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null)
            {
                list.add(currentLine); // add data to provided list
            }
        }
        catch (IOException e)
        {// file was not found 
            e.printStackTrace();
            System.out.println("File was not loaded");
        }
        finally
        {// if we cannot read we still close our streams
            try
            {
                if (br != null)
                { // close buffered reader
                    br.close();
                }
                if (fr != null)
                { // close filereader
                    fr.close();
                }
            }
            catch (IOException e)
            {// Unable to close the file
                e.printStackTrace();
                System.out.println("There was error closing streams");
            }
        }
    }

    /**
     * This method takes parsed configuration file and replaces String on
     * lineNumber for provided replacementString
     *
     * @param list parsen configuration file for editing
     * @param replacementString String to replace existing String as
     * @param lineNumber number of line to replace
     */
    public void rewriteLine(ArrayList<String> list, String replacementString, int lineNumber)
    {
        list.remove(lineNumber - 1);
        list.add(lineNumber - 1, replacementString);
    }

    //TODO: move these methods to camera constructor after created
    /**
     *
     * @param camName name of camera ex. cam1 etc...
     * @param mainFolderPath full path where to create our image and config
     * folders ending with slash
     * @return full path with camera folder name
     */
    public String createFullCameraPath(String camName, String mainFolderPath)
    {
        String fullPath = mainFolderPath + (camName.trim().toLowerCase());
        return fullPath;
    }

    /**
     * This method creates folders for each camera where the images and
     * generated config file end up If the folders already exist it does not do
     * anything
     *
     * @param path Path where to created folders
     */
    public void createConfigFolders(String path)
    {

        boolean success = (new File(path).mkdirs());
        if (!success)
        {
            System.err.println("There was an error creating camera folders");
        }
        else
        {
            System.out.println("Camera folders succesfully created!");
        }
    }

    //TODO(Dominik): IMPORTANT: THIS METHOD IS NOT TESETED TEST BEFORE USING ON HARMLESS FOLDER IS IT DELETES ONLY ONE OR FULL PATH
    /**
     * This method destroys folders at given location used for deleting unused
     * camera folders
     *
     * @param path path to folder to be destroyed
     */
    public void destroyConfigFolders(String path)
    {
        File file = new File(path);
        if (file.exists())
        {
            try
            {
                file.delete();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.err.println("The folder " + path + "could not be deleted!");
            }
        }
        else
        {
            System.out.println("Folder on" + path + "does not exist!");
        }
    }
}
