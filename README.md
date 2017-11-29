---
maintainer: mkristiansen
---

 <b>~*~ mkristiansen/gradle-plugin-mk-plantuml ~*~</b>

Plugin to render [PlantUML](http://en.wikipedia.org/wiki/PlantUML) diagrams as part of a Gradle build.

You may also want to install the plugin for your favorite IDE / Editor:
- Eclipse
- [IntelliJ](https://github.com/esteinberg/plantuml4idea)
- [Visual Studo Code](https://marketplace.visualstudio.com/items?itemName=jebbs.plantuml)
- Atom

<b>about</b>

macod

<b>installation</b>

- Add the plugin to `gradle.build` with the 'new' syntax
  ```gradle
  plugins {
    id "io.kristiansen.gradle.PlantUMLPlugin" version "0.0.1"
  }
  ```
- Add the plugin to `gradle.build` with the 'old' syntax. See the [plugin page](https://plugins.gradle.org/plugin/io.kristiansen.gradle.PlantUMLPlugin).


<b>usage</b>

- Place *.puml files in `${projectDir}/assets/` and run

  ```bash
  gradle renderPlantUml
  ```

  Rendered PNGs and SVGs are placed alongside the puml files.

- Remove all rendered images by running

  ```bash
  gradle cleanPlantUml
  ```

<b>license</b>

```license
The MIT License (MIT)

Copyright (c) 2015 Jason Dunkelberger (a.k.a dirkraft)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```
