/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.util;

import org.apache.ignite.IgniteCheckedException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HostAndPortRangeTest {

    /**
     * tests correct input address with IPv4 host and port range.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test
    public void testParseIPv4WithPortRange() throws IgniteCheckedException {
        String addrStr = "127.0.0.1:8080..8090";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);
        HostAndPortRange expected = new HostAndPortRange("127.0.0.1", 8080, 8090);
        assertEquals(expected, actual);
    }

    /**
     * tests correct input address with IPv4 host and single port.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test
    public void testParseIPv4WithSinglePort() throws IgniteCheckedException {
        String addrStr = "127.0.0.1:8080";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);
        HostAndPortRange expected = new HostAndPortRange("127.0.0.1", 8080, 8080);
        assertEquals(expected, actual);
    }

    /**
     * ests correct input address with IPv4 host and no port.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test
    public void testParseIPv4NoPort() throws IgniteCheckedException {
        String addrStr = "127.0.0.1";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);
        HostAndPortRange expected = new HostAndPortRange("127.0.0.1", 18360, 18362);
        assertEquals(expected, actual);
    }

    /**
     * tests correct input address with IPv6 host and port range.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test
    public void testParseIPv6WithPortRange() throws IgniteCheckedException {
        String addrStr = "[::1]:8080..8090";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);
        HostAndPortRange expected = new HostAndPortRange("::1", 8080, 8090);
        assertEquals(expected, actual);
    }

    /**
     * tests correct input address with IPv6 host and single port.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test
    public void testParseIPv6WithSinglePort() throws IgniteCheckedException {
        String addrStr = "[3ffe:2a00:100:7031::]:8080";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);
        HostAndPortRange expected = new HostAndPortRange("3ffe:2a00:100:7031::", 8080, 8080);
        assertEquals(expected, actual);
    }

    /**
     * tests correct input address with IPv6 host and no port.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test
    public void testParseIPv6NoPort() throws IgniteCheckedException {
        String addrStr = "[::FFFF:129.144.52.38]";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);
        HostAndPortRange expected = new HostAndPortRange("::FFFF:129.144.52.38", 18360, 18362);
        assertEquals(expected, actual);
    }

    /**
     * tests incorrect input address with IPv6 host (no brackets) and port.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test(expected = IgniteCheckedException.class)
    public void testParseIPv6IncorrectHost() throws IgniteCheckedException {
        String addrStr = "3ffe:2a00:100:7031:::8080";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);

    }

    /**
     * tests empty host and port.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test(expected = IgniteCheckedException.class)
    public void testParseNoHost() throws IgniteCheckedException {
        String addrStr = ":8080";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);
    }

    /**
     * tests empty address string.
     * @throws IgniteCheckedException on incorrect host/port
     */
    @Test(expected = IgniteCheckedException.class)
    public void testParseNoAddress() throws IgniteCheckedException {
        String addrStr = "";
        String errMsgPrefix = "";
        int dfltPortFrom = 18360;
        int dfltPortTo = 18362;
        HostAndPortRange actual = HostAndPortRange.parse(addrStr, dfltPortFrom, dfltPortTo, errMsgPrefix);
    }
}
