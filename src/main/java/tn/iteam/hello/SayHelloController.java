package tn.iteam.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    // Say-Hello
    // http://localhost:8084/say-hello
    @RequestMapping("/say-hello")
    @ResponseBody
    public String sayHello(){
        return "Hello! What are you leaning today?";
    }
    @RequestMapping("/say-hello-html")
    @ResponseBody
    public String sayHelloHtml(){
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My First HTML PAGE </title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1> My first html page with body</h1>");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();

    }

    @RequestMapping("/say-hello-view")
    public String sayHelloView(){
        return "sayHello";
    }

}
