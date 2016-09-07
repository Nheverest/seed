/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.seed.core;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import io.nuun.kernel.api.Kernel;
import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.nuun.kernel.core.NuunCore.newKernelConfiguration;

/**
 * @author Pierre THIROUIN (pierre.thirouin@ext.inetpsa.com)
 */
public class IgnoreIT {

    private static Kernel kernel;
    private static Injector injector;

    @org.seedstack.seed.Ignore
    @Scan
    static class IgnoredClass {
    }

    @Scan
    static class ScannedClass {
    }

    @interface Scan {
    }

    @BeforeClass
    public static void setUp() throws Exception {
        kernel = Seed.createKernel(null, newKernelConfiguration().withoutSpiPluginsLoader().addPlugin(IgnorePlugin.class), true);
        injector = kernel.objectGraph().as(Injector.class);
    }

    @Test
    public void testScanWorks() throws Exception {
        ScannedClass instance = injector.getInstance(ScannedClass.class);
        Assertions.assertThat(instance).isNotNull();
    }

    @Test(expected = ConfigurationException.class)
    public void testIgnoreFeature() throws Exception {
        injector.getInstance(IgnoredClass.class);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Seed.disposeKernel(kernel);
    }
}