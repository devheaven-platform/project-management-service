# Project Management Service
[![Build Status](http://drone.devheaven.nl/api/badges/devheaven-platform/project-management-service/status.svg)](http://drone.devheaven.nl/devheaven-platform/project-management-service)

This repository contains the project management service for the DevHeaven platform.

# Development
Build the server by executing the following command ./mvnw install -DskipTests -q in the root directory of the project. Then use java -jar -Dspring.profiles.active=dev target/kwetter-1.0.0.jar to run the server. You can also use an IDE like Intellij to run the server. Don't forget to specifiy -Dspring.profiles.active=dev as an VM argument if you use an IDE to run the server.

# Contribution
There are probably some points of improvement in the application design, structure or code.

If you believe there is a best practice I have not followed, please let me know by opening an issue on the issue tracker. Pull requests are welcome!

# License
DevHeaven is released under the [MIT license](http://opensource.org/licenses/MIT):

```
The MIT License (MIT)

Copyright (c) 2019 DevHeaven

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
