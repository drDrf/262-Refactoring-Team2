package model;

/* model.BowlerFile.java
 *
 *  Version:
 *  		$Id$
 *
 *  Revisions:
 * 		$Log: model.BowlerFile.java,v $
 * 		Revision 1.5  2003/02/02 17:36:45  ???
 * 		Updated comments to match javadoc format.
 *
 * 		Revision 1.4  2003/02/02 16:29:52  ???
 * 		Added model.ControlDeskEvent and model.ControlDeskObserver. Updated model.Queue to allow access to Vector so that contents could be viewed without destroying. Implemented observer model for most of model.ControlDesk.
 *
 *
 */

/**
 * Class for interfacing with model.Bowler database
 */

import java.io.*;
import java.util.Vector;

public class BowlerFile {

    /** The location of the bowelr database */
    private static String BOWLER_DAT = "BOWLERS.DAT";

    /**
     * Retrieves bowler information from the database and returns a model.Bowler objects with populated fields.
     *
     * @param nickName    the nickName of the bolwer to retrieve
     *
     * @return a model.Bowler object
     *
     */

    public static Bowler getBowlerInfo(String nickName) throws IOException, FileNotFoundException {
        BufferedReader in = new BufferedReader(new FileReader(BOWLER_DAT));
        String data;

        while ((data = in.readLine()) != null) {
            // File format is nick\tfname\te-mail
            String[] bowler = data.split("\t");
			String currentNick = bowler[0];
			String currentFull = bowler[1];
			String currentEmail = bowler[2];

			if (nickName.equals(currentNick)) {
				System.out.println(String.format("Nick: %s Full: %s email: %s", currentNick, currentFull, currentEmail));

                return (new Bowler(currentNick, currentFull, currentEmail));
            }
        }

        System.out.println("Nick not found...");
        return null;
    }

    /**
     * Stores a model.Bowler in the database
     *
     * @param nickName    the NickName of the model.Bowler
     * @param fullName    the FullName of the model.Bowler
     * @param email    the E-mail Address of the model.Bowler
     *
     */
    public static void putBowlerInfo(String nickName, String fullName, String email) throws IOException, FileNotFoundException {
        String data = nickName + "\t" + fullName + "\t" + email + "\n";

        RandomAccessFile out = new RandomAccessFile(BOWLER_DAT, "rw");
        out.skipBytes((int) out.length());
        out.writeBytes(data);
        out.close();
    }

    /**
     * Retrieves a list of nicknames in the bowler database
     *
     * @return a Vector of Strings
     *
     */
    public static Vector getBowlers() throws IOException, FileNotFoundException {
        Vector allBowlers = new Vector();
        BufferedReader in = new BufferedReader(new FileReader(BOWLER_DAT));
        String data;

        while ((data = in.readLine()) != null) {
            // File format is nick\tfname\te-mail
            String[] bowler = data.split("\t");
            //"Nick: bowler[0] Full: bowler[1] email: bowler[2]
            allBowlers.add(bowler[0]);
        }

        return allBowlers;
    }

}