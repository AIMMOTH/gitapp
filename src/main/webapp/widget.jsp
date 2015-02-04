<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.net.URLEncoder" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<%
  StringBuilder builder = new StringBuilder();
  String line;
  try {
    while ((line = request.getReader().readLine()) != null) {
      builder.append(line);
    }
  } catch (IOException e) {
    throw new RuntimeException(e);
  }
  String postBody = URLEncoder.encode(builder.toString(), "UTF-8");
%>

<!-- Copy and paste here the "Widget javascript" you downloaded from Developer Console as gitkit-widget.html -->

<script type="text/javascript" src="//www.gstatic.com/authtoolkit/js/gitkit.js"></script>
<link type="text/css" rel="stylesheet" href="//www.gstatic.com/authtoolkit/css/gitkit.css" />
<script type="text/javascript">
  var config = {
      apiKey: 'AIzaSyCYba-RkSceXP-QStgNfc--GMiBd8RMYy0',
      signInSuccessUrl: 'http://git-app.appspot.com/signin/',
      idps: ["google"],
      oobActionUrl: 'http://git-app.appspot.com/email/',
      siteName: 'this site'
  };
  // The HTTP POST body should be escaped by the server to prevent XSS
  window.google.identitytoolkit.start(
      '#gitkitWidgetDiv', // accepts any CSS selector
      config,
      '<%=postBody%>'
      );
</script>

<!-- End modification -->

</head>
<body>

<!-- Include the sign in page widget with the matching 'gitkitWidgetDiv' id -->
<div id="gitkitWidgetDiv"></div>
<!-- End identity toolkit widget -->

</body></html>