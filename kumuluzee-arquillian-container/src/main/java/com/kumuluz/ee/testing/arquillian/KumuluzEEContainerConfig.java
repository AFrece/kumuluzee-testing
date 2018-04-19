/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.kumuluz.ee.testing.arquillian;

import org.jboss.arquillian.container.spi.ConfigurationException;
import org.jboss.arquillian.container.spi.client.container.ContainerConfiguration;

import java.util.Arrays;

/**
 * KumuluzEE Container Adapter configuration class.
 *
 * @author Urban Malc
 * @since 1.0.0
 */
public class KumuluzEEContainerConfig implements ContainerConfiguration {

    private static KumuluzEEContainerConfig instance = null;

    public static void init(KumuluzEEContainerConfig config) {
        if (instance != null) {
            throw new RuntimeException("KumuluzEEContainerConfig already initialised!");
        }
        instance = config;
    }

    public static KumuluzEEContainerConfig getInstance() {
        if (instance == null) {
            throw new RuntimeException("KumuluzEEContainerConfig not initialised!");
        }

        return instance;
    }

    public static final String PACKAGING_UBER_JAR = "uber_jar";
    public static final String PACKAGING_EXPLODED = "exploded";

    private static final String[] ALLOWED_PACKAGINGS = {
            PACKAGING_EXPLODED,
    };

    public static final String INCLUDE_LIBS_FALSE = "false";
    public static final String INCLUDE_LIBS_DEFAULT = "default";
    public static final String INCLUDE_LIBS_MP1_0 = "MicroProfile-1.0";
    public static final String INCLUDE_LIBS_MP1_1 = "MicroProfile-1.1";
    public static final String INCLUDE_LIBS_MP1_2 = "MicroProfile-1.2";

    private static final String[] ALLOWED_INCLUDE_LIBS = {
            INCLUDE_LIBS_FALSE,
            INCLUDE_LIBS_DEFAULT,
            INCLUDE_LIBS_MP1_0,
            INCLUDE_LIBS_MP1_1,
            INCLUDE_LIBS_MP1_2,
    };

    private boolean deleteTemporaryFiles = true;
    private long containerStartTimeoutMs = 60 * 1000;
    private String packaging = PACKAGING_EXPLODED;
    private String kumuluzVersion = "2.6.0-SNAPSHOT";
    private String includeRequiredLibraries = INCLUDE_LIBS_DEFAULT;

    public void validate() throws ConfigurationException {
        if (containerStartTimeoutMs <= 0) {
            throw new ConfigurationException("containerStartTimeoutMs should be greater than 0");
        }

        if (!Arrays.asList(ALLOWED_PACKAGINGS).contains(packaging)) {
            throw new ConfigurationException("Packaging " + packaging + " not allowed. Use one of the following: " +
                    Arrays.toString(ALLOWED_PACKAGINGS));
        }

        if (!Arrays.asList(ALLOWED_INCLUDE_LIBS).contains(includeRequiredLibraries)) {
            throw new ConfigurationException("includeRequiredLibraries parameter " + includeRequiredLibraries +
                    " not recognised. Use one of the following: " + Arrays.toString(ALLOWED_INCLUDE_LIBS));
        }
    }

    public boolean shouldDeleteTemporaryFiles() {
        return deleteTemporaryFiles;
    }

    public void setDeleteTemporaryFiles(boolean deleteTemporaryFiles) {
        this.deleteTemporaryFiles = deleteTemporaryFiles;
    }

    public long getContainerStartTimeoutMs() {
        return containerStartTimeoutMs;
    }

    public void setContainerStartTimeoutMs(long containerStartTimeoutMs) {
        this.containerStartTimeoutMs = containerStartTimeoutMs;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getKumuluzVersion() {
        return kumuluzVersion;
    }

    public void setKumuluzVersion(String kumuluzVersion) {
        this.kumuluzVersion = kumuluzVersion;
    }

    public String getIncludeRequiredLibraries() {
        return includeRequiredLibraries;
    }

    public void setIncludeRequiredLibraries(String includeRequiredLibraries) {
        this.includeRequiredLibraries = includeRequiredLibraries;
    }
}