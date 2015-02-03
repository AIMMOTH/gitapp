package se.ce.gitapp;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.identitytoolkit.GitkitClient;
import com.google.identitytoolkit.GitkitClientException;
import com.google.identitytoolkit.GitkitUser;

@SuppressWarnings("serial")
public class SignInServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(SignInServlet.class.getName());
	private GitkitClient gitkitClient;
	private Properties properties = new Properties();

	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext context = config.getServletContext();

		try {
			properties.load(context.getResourceAsStream("WEB-INF/gitapp.properties"));

			gitkitClient = GitkitClient
					.newBuilder()
					.setGoogleClientId(properties.getProperty("googleClientId"))
					.setServiceAccountEmail(properties.getProperty("serviceAccountEmail"))
					.setKeyStream(context.getResourceAsStream("WEB-INF/git-app-819348d8b50f.p12"))
					.setWidgetUrl("http://git-app.appspot.com")
					.setCookieName("gtoken")
					.build();
		} catch (IOException e) {
			e.printStackTrace();
		}

		super.init(config);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

		GitkitUser gitkitUser;

		try {
			gitkitUser = gitkitClient.validateTokenInRequest(request);
			if (gitkitUser != null)
				resp.getWriter().write(
						"Welcome single user back!<br><br> Email: " + gitkitUser.getEmail() + "<br> Id: "
								+ gitkitUser.getLocalId() + "<br> Provider: " + gitkitUser.getCurrentProvider());
			else
				resp.getWriter().write("Ej inloggad!");

			// Download all accounts from Google Identity Toolkit
			// Iterator<GitkitUser> userIterator = gitkitClient.getAllUsers();
			// while (userIterator.hasNext()) {
			// GitkitUser user = userIterator.next();
			// log.info("Welcome back!<br><br> Email: " + user.getEmail() +
			// "<br> Id: " + user.getLocalId() + "<br> Provider: " +
			// user.getCurrentProvider());
			// }
		} catch (GitkitClientException e2) {
			e2.printStackTrace(resp.getWriter());
		} catch (NoSuchMethodError e) {
			e.printStackTrace(resp.getWriter());
		}
	}
}