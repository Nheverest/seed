/**
 * Copyright (c) 2013-2015 by The SeedStack authors. All rights reserved.
 *
 * This file is part of SeedStack, An enterprise-oriented full development stack.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.seed.core.fixtures;

import org.seedstack.seed.core.spi.diagnostic.DiagnosticDomain;
import org.seedstack.seed.core.spi.diagnostic.DiagnosticInfoCollector;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@DiagnosticDomain("test")
public class TestDiagnosticCollector implements DiagnosticInfoCollector {
    @Inject
    Service1 service1;

    @Override
    public Map<String, Object> collect() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("service", service1.toString());
        return result;
    }
}
