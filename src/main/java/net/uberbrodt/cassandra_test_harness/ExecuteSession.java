package net.uberbrodt.cassandra_test_harness;

import com.datastax.driver.core.Session;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;

import java.util.ArrayList;

/**
 * Created by cbrodt on 5/30/14.
 */
public class ExecuteSession {

    public static void main(String[] args) {
        ArrayList<String> clusterContacts = new ArrayList<String>();

        CassandraConnection conn = new CassandraConnection(clusterContacts);

        Session session = conn.getSession("write_test");

        while (DateTimeComparator.getInstance().compare(null, DateTime.now().plusMinutes(30)) < 1) {
            GenericData obj = new GenericData();
            obj.persist(session);
        }

        session.shutdown();
    }
}
