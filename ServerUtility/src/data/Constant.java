package data;

public class Constant {

    private Constant() {
    }

    //general
    public static final String APP_VERSION = "1.0";
    public static final String APP_NAME = "Server utility";
    public static final String OK_BUTTON_LABEL = "O.K.";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    //main window
    public static final String BUTTON_START_LABEL = "Start";
    public static final String BUTTON_RESTART_LABEL = "Restart";
    public static final String BUTTON_STOP_LABEL = "Stop";
    public static final String BUTTON_CONFIGURATE_LABEL = "Configurate";
    public static final String BUTTON_DELETE_LABEL = "Del";
    public static final String ADD_NEW_SERVER_LABEL = "Add new server";

    //add new server dialog
    public static final String SETUP_SERVER_LABEL = "Setup server";
    public static final String SERVER_NAME_LABEL = "Server name:";
    public static final String START_SCRIPT_LABEL = "Start script:";
    public static final String RESTART_SCRIPT_LABEL = "Restart script:";
    public static final String STOP_SCRIPT_LABEL = "Stop script:";
    public static final String AUTOMATIC_SYNCHRONIZE_LABEL = "Automatic synchronize web folder";
    public static final String SOURCE_FOLDER_LABEL = "Source folder:";
    public static final String TARGET_FOLDER_LABEL = "Target folder:";

    //help dialog
    public static final String ABOUT_PROGRAM_LABEL = "About program";
    public static final String HELP_CONTENT =
            "Main goal this application is automatization copy java program to web server. " +
            "This is best way for developers codeing in java EE and use external web server. " +
            "Easy you can start,restart and restart server in gui with use bath scripts." +
            "You not need manually unzip war and copy file to server yet. " +
            "After you change XHTML pages, it will automatic reloaded on server." +
            "This is way to fast develop without stereotype work.\n\n " + 
            "----> Develop by: Jiri Caga \n ----> Contact: jiricaga30@gmail.com";
    
}
