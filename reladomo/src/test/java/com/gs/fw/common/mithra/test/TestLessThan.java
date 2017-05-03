/*
 Copyright 2016 Goldman Sachs.
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

package com.gs.fw.common.mithra.test;

import com.gs.fw.common.mithra.test.domain.InfinityTimestamp;
import com.gs.fw.common.mithra.test.domain.ParaDeskFinder;
import com.gs.fw.common.mithra.test.domain.ParaDeskList;

import java.sql.Timestamp;
import java.util.List;



public class TestLessThan extends TestSqlDatatypes
{
    public void testBasicLessThanRetrieval()
    throws Exception
    {
        String sql;
        List desks;

        //Boolean : intentionally blank

        //Byte
        byte bite = -127;
        sql = "select * from PARA_DESK where LOCATION_BYTE < -127";
        desks = new ParaDeskList(ParaDeskFinder.locationByte().lessThan(bite));
        this.genericRetrievalTest(sql, desks);

        //Char
        sql = "select * from PARA_DESK where STATUS_CHAR < 'O'";
        desks = new ParaDeskList(ParaDeskFinder.statusChar().lessThan('O'));
        this.genericRetrievalTest(sql, desks);

        //Date
        sql = "select * from PARA_DESK where CLOSED_DATE < '1981-06-08'";
        desks = new ParaDeskList(ParaDeskFinder.closedDate().lessThan(getTestDate()));
        this.genericRetrievalTest(sql, desks);

        //Double
        sql = "select * from PARA_DESK where SIZE_DOUBLE < 4000000000.0";
        desks = new ParaDeskList(ParaDeskFinder.sizeDouble().lessThan(4000000000.0));
        this.genericRetrievalTest(sql, desks);

        //Float
        sql = "select * from PARA_DESK where MAX_FLOAT < 40000.0";
        desks = new ParaDeskList(ParaDeskFinder.maxFloat().lessThan((float) 40000.0));
        this.genericRetrievalTest(sql, desks);

        //Integer
        sql = "select * from PARA_DESK where TAG_INT < 10";
        desks = new ParaDeskList(ParaDeskFinder.tagInt().lessThan(10));
        this.genericRetrievalTest(sql, desks);

        //Long
        sql = "select * from PARA_DESK where CONNECTION_LONG < 2000000";
        desks = new ParaDeskList(ParaDeskFinder.connectionLong().lessThan(2000000));
        this.genericRetrievalTest(sql, desks);

        //Short
        sql = "select * from PARA_DESK where MIN_SHORT < 2000";
        desks = new ParaDeskList(ParaDeskFinder.minShort().lessThan((short) 2000));
        this.genericRetrievalTest(sql, desks);

        //String
        sql = "select * from PARA_DESK where DESK_ID_STRING < 'rnd'";
        desks = new ParaDeskList(ParaDeskFinder.deskIdString().lessThan("rnd"));
        this.genericRetrievalTest(sql, desks);

        //Timestamp
        sql = "select * from PARA_DESK where CREATE_TIMESTAMP < '1981-06-08 02:01:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp()));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where CREATE_TIMESTAMP < '1981-06-08 02:01:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp()));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where CREATE_TIMESTAMP < '9999-12-01 23:59:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.createTimestamp().lessThan(InfinityTimestamp.getParaInfinity()));
        this.genericRetrievalTest(sql, desks, 0);
    }

    public void testLessThanAndEqualOnSameAttribute()
    throws Exception
    {
        String sql;
        List desks;

        //Not implemented for Boolean

        //Byte
        sql = "select * from PARA_DESK where LOCATION_BYTE < -127 and LOCATION_BYTE = 127";
        desks = new ParaDeskList(ParaDeskFinder.locationByte().lessThan((byte) -127).and(ParaDeskFinder.locationByte().eq((byte) 127)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where LOCATION_BYTE < -127 and LOCATION_BYTE = -127";
        desks = new ParaDeskList(ParaDeskFinder.locationByte().lessThan((byte) -127).and(ParaDeskFinder.locationByte().eq((byte) -127)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where LOCATION_BYTE < -127 and LOCATION_BYTE = -128";
        desks = new ParaDeskList(ParaDeskFinder.locationByte().lessThan((byte) -127).and(ParaDeskFinder.locationByte().eq((byte) -128)));
        this.genericRetrievalTest(sql, desks);

        //Char
        sql = "select * from PARA_DESK where STATUS_CHAR < 'O' and STATUS_CHAR = 'P'";
        desks = new ParaDeskList(ParaDeskFinder.statusChar().lessThan('O').and(ParaDeskFinder.statusChar().eq('P')));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where STATUS_CHAR < 'O' and STATUS_CHAR = 'O'";
        desks = new ParaDeskList(ParaDeskFinder.statusChar().lessThan('O').and(ParaDeskFinder.statusChar().eq('O')));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where STATUS_CHAR < 'O' and STATUS_CHAR = 'M'";
        desks = new ParaDeskList(ParaDeskFinder.statusChar().lessThan('O').and(ParaDeskFinder.statusChar().eq('M')));
        this.genericRetrievalTest(sql, desks);

        //Date
        sql = "select * from PARA_DESK where CLOSED_DATE < '1981-06-08' and CLOSED_DATE = '9999-12-01'";
        desks = new ParaDeskList(ParaDeskFinder.closedDate().lessThan(getTestDate()).and(ParaDeskFinder.closedDate().eq(InfinityTimestamp.getParaInfinity())));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where CLOSED_DATE < '1981-06-08' and CLOSED_DATE = '1981-06-08'";
        desks = new ParaDeskList(ParaDeskFinder.closedDate().lessThan(getTestDate()).and(ParaDeskFinder.closedDate().eq(getTestDate())));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where CLOSED_DATE < '1981-06-08' and CLOSED_DATE = '1900-01-01'";
        desks = new ParaDeskList(ParaDeskFinder.closedDate().lessThan(getTestDate()).and(ParaDeskFinder.closedDate().eq(getDawnOfTime())));
        this.genericRetrievalTest(sql, desks);

        //Double
        sql = "select * from PARA_DESK where SIZE_DOUBLE < 4000000000.0 and SIZE_DOUBLE = 5464565234435.9";
        desks = new ParaDeskList(ParaDeskFinder.sizeDouble().lessThan(4000000000.0).and(ParaDeskFinder.sizeDouble().eq(5464565234435.9)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where SIZE_DOUBLE < 4000000000.0 and SIZE_DOUBLE = 4000000000.0";
        desks = new ParaDeskList(ParaDeskFinder.sizeDouble().lessThan(4000000000.0).and(ParaDeskFinder.sizeDouble().eq(4000000000.0)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where SIZE_DOUBLE < 4000000000.0 and SIZE_DOUBLE = 1564654.34";
        desks = new ParaDeskList(ParaDeskFinder.sizeDouble().lessThan(4000000000.0).and(ParaDeskFinder.sizeDouble().eq(1564654.34)));
        this.genericRetrievalTest(sql, desks);

        //Float
//        Connection connection = this.getConnection();
//        sql = "select * from PARA_DESK where MAX_FLOAT < 36546.43 and MAX_FLOAT = ?";
//        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setFloat(1, (float)999999.9);
//        desks = new ParaDeskList(ParaDeskFinder.maxFloat().lessThan((float) 36546.43).and(ParaDeskFinder.maxFloat().eq((float) 999999.9)) );
//        this.genericRetrievalTest(ps, desks, connection);

        sql = "select * from PARA_DESK where MAX_FLOAT < 36546.43 and MAX_FLOAT = 36546.43";
        desks = new ParaDeskList(ParaDeskFinder.maxFloat().lessThan((float) 36546.43).and(ParaDeskFinder.maxFloat().eq((float) 36546.43)));
        this.exactRetrievalTest(sql, desks, 0);

//        sql = "select * from PARA_DESK where MAX_FLOAT < 36546.43 and MAX_FLOAT = 23423.234234234";
//        desks = new ParaDeskList(ParaDeskFinder.maxFloat().lessThan((float) 36546.43).and(ParaDeskFinder.maxFloat().eq((float) 23423.234234234)));
//        this.genericRetrievalTest(sql, desks);

        //Integer
        sql = "select * from PARA_DESK where TAG_INT < 10 and TAG_INT = 100";
        desks = new ParaDeskList(ParaDeskFinder.tagInt().lessThan(10).and(ParaDeskFinder.tagInt().eq(100)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where TAG_INT < 10 and TAG_INT = 10";
        desks = new ParaDeskList(ParaDeskFinder.tagInt().lessThan(10).and(ParaDeskFinder.tagInt().eq(10)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where TAG_INT < 10 and TAG_INT = 5";
        desks = new ParaDeskList(ParaDeskFinder.tagInt().lessThan(10).and(ParaDeskFinder.tagInt().eq(5)));
        this.genericRetrievalTest(sql, desks);

        //Long
        sql = "select * from PARA_DESK where CONNECTION_LONG < 2000000 and CONNECTION_LONG = 3000000 ";
        desks = new ParaDeskList(ParaDeskFinder.connectionLong().lessThan(2000000).and(ParaDeskFinder.connectionLong().eq(3000000)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where CONNECTION_LONG < 2000000 and CONNECTION_LONG = 2000000 ";
        desks = new ParaDeskList(ParaDeskFinder.connectionLong().lessThan(2000000).and(ParaDeskFinder.connectionLong().eq(2000000)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where CONNECTION_LONG < 2000000 and CONNECTION_LONG = 1000000 ";
        desks = new ParaDeskList(ParaDeskFinder.connectionLong().lessThan(2000000).and(ParaDeskFinder.connectionLong().eq(1000000)));
        this.genericRetrievalTest(sql, desks);

        //Short
        sql = "select * from PARA_DESK where MIN_SHORT < 2000 and MIN_SHORT = 3000 ";
        desks = new ParaDeskList(ParaDeskFinder.minShort().lessThan((short) 2000).and(ParaDeskFinder.minShort().eq((short) 3000)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where MIN_SHORT < 2000 and MIN_SHORT = 2000 ";
        desks = new ParaDeskList(ParaDeskFinder.minShort().lessThan((short) 2000).and(ParaDeskFinder.minShort().eq((short) 2000)));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where MIN_SHORT < 2000 and MIN_SHORT = 100 ";
        desks = new ParaDeskList(ParaDeskFinder.minShort().lessThan((short) 2000).and(ParaDeskFinder.minShort().eq((short) 100)));
        this.genericRetrievalTest(sql, desks);

        //String
        sql = "select * from PARA_DESK where DESK_ID_STRING < 'rnd' and DESK_ID_STRING = 'usd'";
        desks = new ParaDeskList(ParaDeskFinder.deskIdString().lessThan("rnd").and(ParaDeskFinder.deskIdString().eq("usd")));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where DESK_ID_STRING < 'rnd' and DESK_ID_STRING = 'rnd'";
        desks = new ParaDeskList(ParaDeskFinder.deskIdString().lessThan("rnd").and(ParaDeskFinder.deskIdString().eq("rnd")));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where DESK_ID_STRING < 'rnd' and DESK_ID_STRING = 'abc'";
        desks = new ParaDeskList(ParaDeskFinder.deskIdString().lessThan("rnd").and(ParaDeskFinder.deskIdString().eq("abc")));
        this.genericRetrievalTest(sql, desks);

        //Timestamp
        sql = "select * from PARA_DESK where CREATE_TIMESTAMP < '1981-06-08 02:01:00.0' and CREATE_TIMESTAMP = '9999-12-01 23:59:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp()).and(ParaDeskFinder.createTimestamp().eq(InfinityTimestamp.getParaInfinity())));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where CREATE_TIMESTAMP < '1981-06-08 02:01:00.0' and CREATE_TIMESTAMP = '1981-06-08 02:01:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp()).and(ParaDeskFinder.createTimestamp().eq(getTestTimestamp())));
        this.exactRetrievalTest(sql, desks, 0);

        sql = "select * from PARA_DESK where CREATE_TIMESTAMP < '1981-06-08 02:01:00.0' and CREATE_TIMESTAMP = '1900-01-01 00:00:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp()).and(ParaDeskFinder.createTimestamp().eq(new Timestamp(getDawnOfTime().getTime()))));
        this.genericRetrievalTest(sql, desks);
    }

    public void testMultipleLessThanOnSameAttribute()
    throws Exception
    {
        String sql;
        List desks;

        //Byte
        sql = "select * from PARA_DESK where LOCATION_BYTE < -127 and LOCATION_BYTE < 0";
        desks = new ParaDeskList(ParaDeskFinder.locationByte().lessThan((byte) -127).and(ParaDeskFinder.locationByte().lessThan((byte) 0)));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where LOCATION_BYTE < 0 and LOCATION_BYTE < -127";
        desks = new ParaDeskList(ParaDeskFinder.locationByte().lessThan((byte) 0).and(ParaDeskFinder.locationByte().lessThan((byte) -127)));
        this.genericRetrievalTest(sql, desks);

        //Char
        sql = "select * from PARA_DESK where STATUS_CHAR < 'O' and STATUS_CHAR < 'P'";
        desks = new ParaDeskList(ParaDeskFinder.statusChar().lessThan('O').and(ParaDeskFinder.statusChar().lessThan('P')));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where STATUS_CHAR < 'O' and STATUS_CHAR < 'P'";
        desks = new ParaDeskList(ParaDeskFinder.statusChar().lessThan('P').and(ParaDeskFinder.statusChar().lessThan('O')));
        this.genericRetrievalTest(sql, desks);

        //Date
        sql = "select * from PARA_DESK where CLOSED_DATE < '1981-06-08' and CLOSED_DATE < '9999-12-01'";
        desks = new ParaDeskList(ParaDeskFinder.closedDate().lessThan(getTestDate()).and(ParaDeskFinder.closedDate().lessThan(InfinityTimestamp.getParaInfinity())));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where CLOSED_DATE < '1981-06-08' and CLOSED_DATE < '9999-12-01'";
        desks = new ParaDeskList(ParaDeskFinder.closedDate().lessThan(InfinityTimestamp.getParaInfinity()).and(ParaDeskFinder.closedDate().lessThan(getTestDate())));
        this.genericRetrievalTest(sql, desks);

        //Double
        sql = "select * from PARA_DESK where SIZE_DOUBLE < 4000000000.0 and SIZE_DOUBLE < 5464565234435.9";
        desks = new ParaDeskList(ParaDeskFinder.sizeDouble().lessThan(5464565234435.9).and(ParaDeskFinder.sizeDouble().lessThan(4000000000.0)));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where SIZE_DOUBLE < 4000000000.0 and SIZE_DOUBLE < 5464565234435.9";
        desks = new ParaDeskList(ParaDeskFinder.sizeDouble().lessThan(4000000000.0).and(ParaDeskFinder.sizeDouble().lessThan(5464565234435.9)));
        this.genericRetrievalTest(sql, desks);

        //Float
//        sql = "select * from PARA_DESK where MAX_FLOAT < 36546.43 and MAX_FLOAT < 23423.234234234";
//        desks = new ParaDeskList(ParaDeskFinder.maxFloat().lessThan((float) 36546.43).and(ParaDeskFinder.maxFloat().lessThan((float) 23423.234234234)));
//        this.genericRetrievalTest(sql, desks);

//        sql = "select * from PARA_DESK where MAX_FLOAT < 36546.43 and MAX_FLOAT < 23423.234234234";
//        desks = new ParaDeskList(ParaDeskFinder.maxFloat().lessThan((float) 23423.234234234).and(ParaDeskFinder.maxFloat().lessThan((float) 36546.43)));
//        this.genericRetrievalTest(sql, desks);

        //Int
        sql = "select * from PARA_DESK where TAG_INT < 10 and TAG_INT < 50";
        desks = new ParaDeskList(ParaDeskFinder.tagInt().lessThan(10).and(ParaDeskFinder.tagInt().lessThan(50)));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where TAG_INT < 10 and TAG_INT < 50";
        desks = new ParaDeskList(ParaDeskFinder.tagInt().lessThan(50).and(ParaDeskFinder.tagInt().lessThan(10)));
        this.genericRetrievalTest(sql, desks);

        //Long
        sql = "select * from PARA_DESK where CONNECTION_LONG < 2000000 and CONNECTION_LONG < 3000000 ";
        desks = new ParaDeskList(ParaDeskFinder.connectionLong().lessThan(2000000).and(ParaDeskFinder.connectionLong().lessThan(3000000)));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where CONNECTION_LONG < 2000000 and CONNECTION_LONG < 3000000 ";
        desks = new ParaDeskList(ParaDeskFinder.connectionLong().lessThan(3000000).and(ParaDeskFinder.connectionLong().lessThan(2000000)));
        this.genericRetrievalTest(sql, desks);

        //Short
        sql = "select * from PARA_DESK where MIN_SHORT < 2000 and MIN_SHORT < 1000 ";
        desks = new ParaDeskList(ParaDeskFinder.minShort().lessThan((short) 2000).and(ParaDeskFinder.minShort().lessThan((short) 1000)));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where MIN_SHORT < 2000 and MIN_SHORT < 1000 ";
        desks = new ParaDeskList(ParaDeskFinder.minShort().lessThan((short) 1000).and(ParaDeskFinder.minShort().lessThan((short) 2000)));
        this.genericRetrievalTest(sql, desks);

        //String
        sql = "select * from PARA_DESK where DESK_ID_STRING < 'rnd' and DESK_ID_STRING < 'gma'";
        desks = new ParaDeskList(ParaDeskFinder.deskIdString().lessThan("rnd").and(ParaDeskFinder.deskIdString().lessThan("gma")));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where DESK_ID_STRING < 'rnd' and DESK_ID_STRING < 'gma'";
        desks = new ParaDeskList(ParaDeskFinder.deskIdString().lessThan("gma").and(ParaDeskFinder.deskIdString().lessThan("rnd")));
        this.genericRetrievalTest(sql, desks);

        //Timestamp
        sql = "select * from PARA_DESK where CREATE_TIMESTAMP < '1981-06-08 02:01:00.0' and CREATE_TIMESTAMP < '9999-12-01 23:59:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp()).and(ParaDeskFinder.createTimestamp().lessThan(InfinityTimestamp.getParaInfinity())));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where CREATE_TIMESTAMP < '1981-06-08 02:01:00.0' and CREATE_TIMESTAMP < '9999-12-01 23:59:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.createTimestamp().lessThan(InfinityTimestamp.getParaInfinity()).and(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp())));
        this.genericRetrievalTest(sql, desks);
    }

    public void testLessThanOnDifferent()
            throws Exception
    {
        String sql = "select * from PARA_DESK where DESK_ID_STRING < 'rnd' and CREATE_TIMESTAMP < '1981-06-08 02:01:00.0'";
        List desks = new ParaDeskList(ParaDeskFinder.deskIdString().lessThan("rnd").and(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp())));
        this.genericRetrievalTest(sql, desks);

        sql = "select * from PARA_DESK where TAG_INT < 1000 and CONNECTION_LONG < 2000000 and CREATE_TIMESTAMP < '1981-06-08 02:01:00.0'";
        desks = new ParaDeskList(ParaDeskFinder.tagInt().lessThan(1000).and(ParaDeskFinder.connectionLong().lessThan(2000000)).and(ParaDeskFinder.createTimestamp().lessThan(getTestTimestamp())));
        this.genericRetrievalTest(sql, desks);
    }
}