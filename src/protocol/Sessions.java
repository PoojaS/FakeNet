package protocol;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Sessions {

    private Map<String, Session> allSessions;

    public Sessions() {
        allSessions = new Hashtable<String, Session>();
    }

    public synchronized void create(String buddy) {
        allSessions.put(buddy, new Session(buddy));
    }

    public Packets read(int bytes) {
        int bytesToReadNow = bytes;
        Packets result = new Packets();
        for (Session session : allSessions.values()) {
            result.addAll(session.read(bytesToReadNow));
            bytesToReadNow = bytes - result.size();
        }
        return result;
    }

    public synchronized Session accept(String buddy) {
        Session existingSession = allSessions.get(buddy);
        if (null == existingSession) {
            Session session = new Session(buddy);
            allSessions.put(buddy, session);
            existingSession = session;
        }
        return existingSession;
    }

    public List<String> allBuddies() {
        return new ArrayList<String>(allSessions.keySet());
    }
}
