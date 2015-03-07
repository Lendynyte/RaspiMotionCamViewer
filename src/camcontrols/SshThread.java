package camcontrols;

/**
 *
 */
public class SshThread extends Thread
{

    public SshThread()
    {
    }

    public SshThread(String name)
    {
        super(name);
    }

    public SshThread(ThreadGroup group, String name)
    {
        super(group, name);
    }

    @Override
    public void run()
    {
        int ip = 100 + Integer.parseInt(getName()) + 1;   // aby ip na konci bylo postupne 101, 102 atd. pro cluster, kde je 32 RPi
        //Ssh ssh = new Ssh("sem_login", "sem_heslo", "sem_ip_adresu", sem_port);
        SshHandler ssh = new SshHandler("sem_login", "sem_heslo", "sem_prvni_cast_IP" + ip, 22);
        ssh.runCommand("uptime", getName());

        // Cluster.stavVlaken[Integer.parseInt(getName())] = true;
    }

    @Override
    public synchronized void start()
    {
        super.start(); 
    }

}
