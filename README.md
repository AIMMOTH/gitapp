Google Identity Toolkit + Google App Engine (Java)
==================================================

1. Maven skeleton project built
2. Dependencies added
3. GitkitClient example code added (package com.google.identitytoolkit)
4. Properties with client id and service account mail added to WEB-INF (see SignInServlet)
5. p12 file in WEB-INF 
6. Added browser API key and URL:s to widget.htm
7. Ignoring JAVASCRIPT_ESCAPED_POST_BODY

Install
-------

1. Add your p12 key to 'src/main/webapp/WEB-INF/serviceaccountkey.data'
2. Create a properties file at 'src/main/webapp/WEB-INF/gitapp.properties' and add 'googleClientId' and 'serviceAccountEmail' properties
3. Run 'mvn appengine:devserver'  