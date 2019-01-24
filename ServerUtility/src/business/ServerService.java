package business;

import data.ServerInfo;
import utils.xmlParser.XmlParserImpl;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.synchronize.SynchronizeManager;
import utils.synchronize.SynchronizeManagerImpl;
import utils.xmlParser.XmlParser;

/**
 * This class contain main logic in application, It provide start,restart and
 * stop server, getting information about servers.
 *
 * @author adminuser
 */
public class ServerService {

    private static ServerService serverService;
    private final List<ServerInfo> serversInfo;

    private final File outFile = new File("configuration.xml");
    private final XmlParser xmlParser;
    private final SynchronizeManager synchronizeManager;

    private ServerService() {
        xmlParser = new XmlParserImpl<ServerInfo>();
        serversInfo = xmlParser.parse(outFile);
        synchronizeManager = new SynchronizeManagerImpl(200);
    }

    public static ServerService getInstance() {
        if (serverService == null) {
            serverService = new ServerService();
        }
        return serverService;
    }

    public void remove(ServerInfo serverInfo) {
        serversInfo.remove(serverInfo);
    }

    public void addOrUpdate(ServerInfo serverInfo) {
        if (serverInfo.getId() == null) {
            serverInfo.setId(serversInfo.size());
            serversInfo.add(serverInfo);
        } else {
            for (int i = 0; i < serversInfo.size(); i++) {
                if (Objects.equals(serversInfo.get(i).getId(), serverInfo.getId())) {
                    serversInfo.set(i, serverInfo);
                    break;
                }
            }
        }
    }

    public List<ServerInfo> getServersInfo() {
        return serversInfo;
    }

    public void startServer(ServerInfo serverInfo) {
        try {
            if (isAvailable(serverInfo)) {
                Runtime.getRuntime().exec("cmd /c start " + serverInfo.getStartScript() + " ^& exit");
                File sourceFolder = new File(serverInfo.getSourceFolder());
                File targetFolder = new File(serverInfo.getTargetFolder());
                synchronizeManager.add(sourceFolder, targetFolder);
                serverInfo.setRun(true);
            }
        } catch (Exception e) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void stopServer(ServerInfo serverInfo) {
        try {
            if (isAvailable(serverInfo)) {
                Runtime.getRuntime().exec("cmd /c start " + serverInfo.getStopScript() + " ^& exit");
                File sourceFolder = new File(serverInfo.getSourceFolder());
                File targetFolder = new File(serverInfo.getTargetFolder());
                synchronizeManager.remove(sourceFolder, targetFolder);
                serverInfo.setRun(false);
            }
        } catch (Exception e) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void restartServer(ServerInfo serverInfo) {
        try {
            if (isAvailable(serverInfo)) {
                Runtime.getRuntime().exec("cmd /c start " + serverInfo.getRestartScript() + " ^& exit");
            }
        } catch (Exception e) {
            Logger.getLogger(ServerService.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public boolean isAvailable(ServerInfo info) {
        File startScript = new File(info.getStartScript());
        File restartScript = new File(info.getRestartScript());
        File stopScript = new File(info.getStopScript());
        File sourceFolder = new File(info.getSourceFolder());
        File targetFolder = new File(info.getTargetFolder());

        return (startScript.isFile() && restartScript.isFile() && stopScript.isFile()
                && (info.isAutomaticSynchnize() ? (sourceFolder.isDirectory()) : true));
    }

    public void saveState() {
        xmlParser.save(serversInfo, outFile);
    }
}
