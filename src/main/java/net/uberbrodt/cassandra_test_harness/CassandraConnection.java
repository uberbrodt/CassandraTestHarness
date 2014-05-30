package net.uberbrodt.cassandra_test_harness;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;

import java.util.List;

/**
 * Created by cbrodt on 5/30/14.
 */
public class CassandraConnection {

    private Cluster cluster = null;

    private static final String DC_NAME = "foo";
    private static final int  RECONNECTION_WAIT_TIME = 30000;

    public CassandraConnection(List<String> nodeList) {
        Builder builder = Cluster.builder();
        builder.withPort(9042);
        for (String node: nodeList) {
            builder.addContactPoint(node);
        }

        builder.withLoadBalancingPolicy(new DCAwareRoundRobinPolicy(DC_NAME));
        builder.withReconnectionPolicy(new ConstantReconnectionPolicy(RECONNECTION_WAIT_TIME));
        builder.withRetryPolicy(DefaultRetryPolicy.INSTANCE);

        cluster = builder.build();
    }

    public Session getSession(String keyspace) {
        return cluster.connect(keyspace);
    }


}
