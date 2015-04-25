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
 * @version 0.5
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
        new Thread()
        {
            @Override
            public void run()
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
                    System.err.println("Creating file failed");
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
                        System.out.println("There was error closing streams");
                    }
                }
            }
        }.start();
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
        new Thread()
        {

            @Override
            public void run()
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
                    System.err.println("File was not loaded");
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
                        System.err.println("There was error closing streams");
                    }
                }
            }
        }.start();
    }

    /**
     *
     * @param camName name of camera ex. cam1 etc...
     * @param mainFolderPath full path where to create our image and config
     * folders ending with slash
     * @return full path with camera folder name
     */
    public String createFullCameraPath(String camName, String mainFolderPath)
    {
        return mainFolderPath + (camName.trim().toLowerCase());
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
        if (new File(path).exists())
        {
            System.out.println("Folders already created...");
            return;
        }
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
                System.err.println("The folder " + path + "could not be deleted!");
            }
        }
        else
        {
            System.out.println("Folder on" + path + "does not exist!");
        }
    }
}
