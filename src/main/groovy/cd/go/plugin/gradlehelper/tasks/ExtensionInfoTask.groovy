/*
 * Copyright 2018 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cd.go.plugin.gradlehelper.tasks

import groovy.text.GStringTemplateEngine

class ExtensionInfoTask extends AbstractTask {

    @Override
    void doTaskAction() {
        final URL[] fileURLs = [
                getClass().getResource('/plugin.xml'),
                getClass().getResource('/plugin.properties')
        ]

        def engine = new GStringTemplateEngine()

        fileURLs.each { fileUrl ->
            def template = engine.createTemplate(fileUrl).make(pluginInfo.toHash().withDefault { '' })
            new File(resourceOutDir, new File(fileUrl.file).getName()).write(template.toString())
        }
    }
}
