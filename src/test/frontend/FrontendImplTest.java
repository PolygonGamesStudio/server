package frontend;

import base.MessageSystem;
import base.UserData;
import dbService.UserDataSet;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.SysInfo;
import utils.TemplateHelper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;

import static org.mockito.Mockito.*;

/**
 * Created by max on 05.04.14.
 */
public class FrontendImplTest {
    MessageSystem messageSystem;
    FrontendImpl frontend;
    Server server;
    ResourceHandler resource_handler;
    HandlerList handlers;

    Request mockedBaseRequest;
    HttpServletRequest mockedRequest;
    HttpServletResponse mockedResponse;
    String target;


    @BeforeMethod
    public void setUp() throws Exception {
        messageSystem = new MessageSystemImpl();
        frontend = new FrontendImpl(messageSystem);

        SysInfo sysInfo = new SysInfo();
        server = new Server(8000);
        resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("static");

        handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {frontend});
        server.setHandler(handlers);

        server.start();
        TemplateHelper.init();
        (new Thread(sysInfo)).start();

        mockedBaseRequest = mock(Request.class);
        mockedRequest = mock(HttpServletRequest.class);
        mockedResponse = mock(HttpServletResponse.class);
    }

    @AfterMethod
    public void tearDown() throws Exception {
        server.stop();

    }
/*@Test
    public void testHandle() throws Exception {
        mockedBaseRequest = mock(Request.class);
        mockedRequest = mock(HttpServletRequest.class);
        mockedResponse = mock(HttpServletResponse.class);




        //====== newUser()
        when(mockedRequest.getCookies()).thenReturn(null);  //returns true
        //------
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{   //returns false
                new Cookie("sessionId", "hz chto"),
                new Cookie("startServerTime", UserDataImpl.getStartServerTime())
        });
        UserDataSet mockedUserSession = mock(UserDataSet.class);
            //
            when(mockedUserSession.getId()).thenReturn(42);//getStatus() #3
            //
        UserDataImpl.putSessionIdAndUserSession("hz chto", mockedUserSession);
        //====== inWeb()
        target = "/admin";  //returns true
        //------
        target = "/a/";  //returns false
        //====== isStatic()
        target = "/a/";     //returns false
        //------
        target = "/js/";    //returns true
        //------
        target = "/img/";   //returns true
        //====== getStatus()
        when(mockedRequest.getMethod()).thenReturn("POST");
        //------
        when(mockedRequest.getMethod()).thenReturn(null);
        //------
        target = "/wait"; //#3
        //------
        target = "";//hz что написать
        //------
        target = "not/wait";




        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
    }*/

    @Test
    public void testHandle1() throws Exception{
    //newUser: false, !inWeb.!isStatic: 11, stat.target: 1random
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{  //newUser: returns false
                new Cookie("sessionId", "hz chto"),
                new Cookie("startServerTime", UserDataImpl.getStartServerTime())
        });
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        UserDataImpl.putSessionIdAndUserSession("hz chto", mockedUserSession);
        target = "/a/"; //!inWeb.!isStatic: 11
        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
        verify(mockedBaseRequest).setHandled(true);
    }

    @Test
    public void testHandle2() throws Exception{
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{  //newUser: returns false
                new Cookie("JSESSIONID", "nomer")
        });
        target = "/js/"; //!inWeb.!isStatic: 10
        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
        verify(mockedBaseRequest).setHandled(true);
    }

    @Test
    public void testHandle3() throws Exception{
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{   //newUser false
                new Cookie("sessionId", "hz chto"),
                new Cookie("startServerTime", UserDataImpl.getStartServerTime())
        });
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        //
        when(mockedUserSession.getId()).thenReturn(42);//getStatus() #3
        //
        UserDataImpl.putSessionIdAndUserSession("hz chto", mockedUserSession);
        when(mockedRequest.getMethod()).thenReturn("POST");
        target = "/wait";
        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
        verify(mockedBaseRequest).setHandled(true);
    }

    @Test
    public void testHandle4() throws Exception{
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{   //newUser false
                new Cookie("sessionId", "hz chto"),
                new Cookie("startServerTime", UserDataImpl.getStartServerTime())
        });
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        //
        when(mockedUserSession.getId()).thenReturn(42);//getStatus() #3
        //
        UserDataImpl.putSessionIdAndUserSession("hz chto", mockedUserSession);
        when(mockedRequest.getMethod()).thenReturn("POST");

        target = "/admin";

        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
        verify(mockedBaseRequest).setHandled(true);
    }

    @Test
    public void testHandle5() throws Exception{
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{   //newUser false
                new Cookie("sessionId", "hz chto"),
                new Cookie("startServerTime", UserDataImpl.getStartServerTime())
        });
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        //
        when(mockedUserSession.getId()).thenReturn(42);
        when(mockedUserSession.getNick()).thenReturn("Nagibator9000");
        when(mockedUserSession.getRating()).thenReturn(666);
        //
        UserDataImpl.putSessionIdAndUserSession("hz chto", mockedUserSession);
        when(mockedRequest.getMethod()).thenReturn("GET");

        target = "/admin";

        //HttpServletResponse response = new Response()
        PrintWriter mockedPrintWriter = mock(PrintWriter.class);
        when(mockedResponse.getWriter()).thenReturn(mockedPrintWriter);

        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
        verify(mockedBaseRequest).setHandled(true);
    }

    @Test
    public void testHandle6() throws Exception{
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{   //newUser false
                new Cookie("sessionId", "hz chto"),
                new Cookie("startServerTime", UserDataImpl.getStartServerTime())
        });
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        //
        when(mockedUserSession.getId()).thenReturn(42);
        when(mockedUserSession.getNick()).thenReturn("Nagibator9000");
        when(mockedUserSession.getRating()).thenReturn(666);
        //
        UserDataImpl.putSessionIdAndUserSession("hz chto", mockedUserSession);
        when(mockedRequest.getMethod()).thenReturn("GET");

        target = "/rules";

        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
        verify(mockedBaseRequest).setHandled(true);
    }
    @Test
    public void testHandle7() throws Exception{
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{   //newUser false
                new Cookie("sessionId", "hz chto"),
                new Cookie("startServerTime", UserDataImpl.getStartServerTime())
        });
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        //
        when(mockedUserSession.getId()).thenReturn(42);//getStatus() #3
        //
        UserDataImpl.putSessionIdAndUserSession("hz chto", mockedUserSession);
        when(mockedRequest.getMethod()).thenReturn("POST");
        target = "/";
        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
        verify(mockedBaseRequest).setHandled(true);
    }

    @Test
    public void testHandle8() throws Exception{
        when(mockedRequest.getCookies()).thenReturn(new Cookie[]{   //newUser false
                new Cookie("sessionId", "hz chto"),
                new Cookie("startServerTime", UserDataImpl.getStartServerTime())
        });
        UserDataSet mockedUserSession = mock(UserDataSet.class);
        //
        when(mockedUserSession.getId()).thenReturn(42);//getStatus() #3
        //
        UserDataImpl.putSessionIdAndUserSession("hz chto", mockedUserSession);
        when(mockedRequest.getMethod()).thenReturn("POST");
        when(mockedRequest.getParameter("regNick")).thenReturn("asasasasasasasasasasasas");
        when(mockedRequest.getParameter("regPassword")).thenReturn("asda");

        target = "/admin";

        frontend.handle(target, mockedBaseRequest, mockedRequest, mockedResponse);
        verify(mockedBaseRequest).setHandled(true);
    }

}