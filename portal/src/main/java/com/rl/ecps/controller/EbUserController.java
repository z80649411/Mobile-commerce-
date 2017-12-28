package com.rl.ecps.controller;

import com.rl.ecps.model.EbShipAddr;
import com.rl.ecps.model.TsPtlUser;
import com.rl.ecps.service.EbShipAddrService;
import com.rl.ecps.service.TsPtlUserService;
import com.rl.ecps.service.impl.EbShipAddrServiceImpl;
import com.rl.ecps.service.impl.TsptlUserServiceImpl;
import com.rl.ecps.utils.MD5;
import com.rl.ecps.utils.ResourcesUtils;
import net.sf.json.JSONObject;
import org.apache.bcel.generic.IFNE;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by ozc on 2017/9/29.
 */
@Controller
@RequestMapping("/user")
public class EbUserController {




    @Autowired
    private TsPtlUserService userService;

    @Autowired
    private EbShipAddrService addrService;

    /**
     * 跳转到登陆页面
     *
     * @return
     */
    @RequestMapping("/toLogin.do")
    public String toLogin() {

        return "passport/login";
    }

    /**
     * 将地址设置为默认地址
     *
     * @return
     */
    @RequestMapping("/updateDefault.do")
    public String updateDefault(Long shipAddrId,HttpSession session) {

        if (shipAddrId != null) {

            //将当前用户数据库中为默认地址的数据设置为0
            TsPtlUser user = (TsPtlUser) session.getAttribute("user");
            addrService.deleteDefault(user.getPtlUserId());

            //将其地址设置为默认地址
            addrService.updateDefault(shipAddrId);

        }



        return "redirect:login/toDeliverAddress.do";
    }


    /**
     * 根据id获取收货地址详细信息，返回给浏览器
     *
     * @return
     */
    @RequestMapping("/getAddr.do")
    public void getAddr(Long shipAddrId,HttpServletResponse response) {
        EbShipAddr ebShipAddr = addrService.selectByPrimaryKey(shipAddrId);

        //返回JSON数据出去
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("addr", ebShipAddr);
        String result = jsonObject.toString();

        //JSON数据带有中文，编码后输出
        ResourcesUtils.printJSON(result, response);

    }


    /**
     * 添加或修改地址
     *
     * @return
     */
    @RequestMapping("/saveOrUpdateAddress.do")
    public String saveOrUpdateAddress(EbShipAddr addr, HttpSession session) {

        TsPtlUser user = (TsPtlUser) session.getAttribute("user");
        addr.setPtlUserId(user.getPtlUserId());

        if (addr.getDefaultAddr() == 1) {
            addrService.deleteDefault(user.getPtlUserId());
        }

        if (addr.getShipAddrId() == null) {
            addrService.insert(addr);

        } else {
            //如果是有id的，那么就是更新操作！
            addrService.updateByPrimaryKeySelective(addr);

        }

        return "redirect:login/toDeliverAddress.do";
    }

    @RequestMapping("/loginAjax.do")
    public void loginAjax(String username, String password,
                          String captcha, HttpSession session, PrintWriter out){
        //获得正确的验证码的值
        String picCode = (String) session.getAttribute("piccode");
        if(!StringUtils.equalsIgnoreCase(captcha, picCode)){
            out.write("caperror");
            return;
        }
        password = new MD5().GetMD5Code(password);
        Map<String, String> map = new HashMap<String,String>();
        map.put("username", username);
        map.put("password", password);
        //根据用户名和密码来查询用户
        TsPtlUser user = userService.findUserByUsernamePass(username, password);
        if(user == null){
            out.write("userpasserror");
            return;
        }
        session.setAttribute("user", user);
        //如果是:后面没有/在同一个Controller中重定向
        //如果是：后面有/是在不同的Controller中重定向
        out.write("success");
    }


    /**
     * 跳转到收货地址页面
     *
     * @return
     */
    @RequestMapping("/login/toDeliverAddress.do")
    public String toDeliverAddress(HttpSession session,Model model) {

        TsPtlUser user = (TsPtlUser) session.getAttribute("user");
        Long ptlUserId = user.getPtlUserId();
        List<EbShipAddr> userAddress = addrService.findUserAddress(ptlUserId);
        model.addAttribute("userAddress", userAddress);

        return "person/deliverAddress";
    }

    /**
     * 跳转到我的商城页面
     *
     * @return
     */
    @RequestMapping("/login/toPersonal.do")
    public String toPersonal() {

        return "person/index";
    }

    /**
     * 处理登陆
     *
     * @return
     */
    @RequestMapping("/login.do")
    public String login(String username, String password, String captcha, HttpSession session, Model model) {

        //首先校验验证码是否正确
        String piccode = (String) session.getAttribute("piccode");

        if (!StringUtils.equalsIgnoreCase(piccode, captcha)) {
            model.addAttribute("tip", "captchaError");
            return "passport/login";
        }

        //数据库存储的是MD5值，因此我们拿到密码也要md5一下
        String md5Code = MD5.GetMD5Code(password);

        //验证用户名和密码
        TsPtlUser user = userService.findUserByUsernamePass(username, md5Code);
        if (user == null) {
            model.addAttribute("tip", "userPassError");
            return "passport/login";
        }

        session.setAttribute("user", user);
        return "redirect:/item/toIndex.do";

    }

    /**
     * 获取验证码图片
     *
     * @return
     */
    @RequestMapping("/getImageCode.do")
    public void getImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("#######################生成数字和字母的验证码#######################");
        BufferedImage img = new BufferedImage(68, 22, 1);
        Graphics g = img.getGraphics();
        Random r = new Random();
        Color c = new Color(200, 150, 255);
        g.setColor(c);
        g.fillRect(0, 0, 68, 22);
        StringBuffer sb = new StringBuffer();
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        int len = ch.length;

        for (int i = 0; i < 4; ++i) {
            int index = r.nextInt(len);
            g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
            g.setFont(new Font("Arial", 3, 22));
            g.drawString("" + ch[index], i * 15 + 3, 18);
            sb.append(ch[index]);
        }

        request.getSession().setAttribute("piccode", sb.toString());
        ImageIO.write(img, "JPG", response.getOutputStream());

    }


    @RequestMapping("/getUser.do")
    public void getUser(HttpSession session, HttpServletResponse response) {

        //得到session的值，通过JSON输出给浏览器
        TsPtlUser user = (TsPtlUser) session.getAttribute("user");
        JSONObject jo = new JSONObject();
        jo.accumulate("user", user);
        String result = jo.toString();

        //编码后输出
        ResourcesUtils.printJSON(result, response);
    }
}
