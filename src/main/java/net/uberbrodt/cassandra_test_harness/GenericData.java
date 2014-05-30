package net.uberbrodt.cassandra_test_harness;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import org.joda.time.DateTime;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by cbrodt on 5/30/14.
 */
public class GenericData {

    private static SecureRandom random = new SecureRandom();

    private String stringVal1;
    private String stringVal2;
    private int intVal1;
    private DateTime dateVal1;

    public String getStringVal1() {
        return stringVal1;
    }

    public void setStringVal1(String stringVal1) {
        this.stringVal1 = stringVal1;
    }

    public String getStringVal2() {
        return stringVal2;
    }

    public void setStringVal2(String stringVal2) {
        this.stringVal2 = stringVal2;
    }

    public int getIntVal1() {
        return intVal1;
    }

    public void setIntVal1(int intVal1) {
        this.intVal1 = intVal1;
    }

    public DateTime getDateVal1() {
        return dateVal1;
    }

    public void setDateVal1(DateTime dateVal1) {
        this.dateVal1 = dateVal1;
    }

    public GenericData() {
       this.dateVal1 = generateRandomDate();
       this.stringVal1 = generateRandomString();
       this.stringVal2 = generateRandomString();
       this.intVal1 = generateRandomInt();
    }

    private static String generateRandomString() {
        return new BigInteger(130, random).toString(32);

    }

    private static int generateRandomInt() {
        return random.nextInt();

    }
    private static DateTime generateRandomDate() {
        return new DateTime().now();
    }

    public ResultSet persist(Session session) {
        Insert insert = QueryBuilder.insertInto("generic_data").
                value("string_val_1", this.stringVal1).value("string_val_2", this.stringVal2).
                value("int_val_1", intVal1).value("date_val_1", dateVal1);

        return session.execute(insert);
    }
}
