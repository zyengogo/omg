package com.omg.evn.action.randcode;


import com.omg.evn.util.randcodeutil.SecurityCode;
import com.omg.evn.util.randcodeutil.SecurityImage;
import com.omg.evn.util.randcodeutil.SecurityCode.SecurityCodeLevel;
import com.opensymphony.xwork2.ActionSupport;
import java.io.ByteArrayInputStream;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

/**
 * 
 * @Company: OMG Technology co.Ltd. (c) copyright
 * @Title: CodeimageAction.java
 * @Package com.omg.evn.action.randcode
 * @discription 提供图片验证码
 * @author zyen
 * @date 2015-5-1 下午4:52:17 
 * @version V1.0 
 * @editor:
 * @editDate: 
 * @editInfo:
 */


@Results({
	@Result(name = "success", type="stream",params={"contentType", "image/jpeg", "inputName", "imageStream","bufferSize", "2048"})
})
public class CodeimageAction extends ActionSupport {
    //图片流
    private ByteArrayInputStream imageStream;

    
    
    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }
    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    
    /**
     * 验证码
     */
    public String execute() throws Exception {
        //获取默认难度和长度的验证码(Simple只包含数字，Medium包含数字和小写英文，Hard包含数字和大小写英文)
        String securityCode = SecurityCode.getSecurityCode(4, SecurityCodeLevel.Simple, false);
        
        /**
         * 测试时验证码全部为1111
         */
        securityCode="1111";
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        
        //放入session中
        HttpSession session = ServletActionContext.getRequest().getSession();  
        session.setAttribute("randCode", securityCode);
        
        return "success";
    }


}