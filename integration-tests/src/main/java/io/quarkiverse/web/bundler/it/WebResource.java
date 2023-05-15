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
package io.quarkiverse.web.bundler.it;

import java.time.Instant;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

@Path("/")
@ApplicationScoped
public class WebResource {
    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance index(Bundle bundle);
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        final long now = Instant.now().toEpochMilli();
        return Templates.index(new Bundle("/bundle/main.js?time=" + now, "/bundle/main.css?time=" + now));
    }

    public static class Bundle {
        public String js;
        public String css;

        public Bundle(String js, String css) {
            this.js = js;
            this.css = css;
        }
    }
}
