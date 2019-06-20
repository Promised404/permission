package com.dpt.permission.service.impl;

import com.dpt.permission.beans.Mail;
import com.dpt.permission.beans.PageQuery;
import com.dpt.permission.beans.PageResult;
import com.dpt.permission.common.RequestHolder;
import com.dpt.permission.dao.SysUserMapper;
import com.dpt.permission.exception.ParamException;
import com.dpt.permission.exception.PermissionException;
import com.dpt.permission.model.SysUser;
import com.dpt.permission.param.UserParam;
import com.dpt.permission.service.SysLogService;
import com.dpt.permission.service.SysUserService;
import com.dpt.permission.util.*;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户服务接口实现.
 *
 * @author 邓鹏涛
 * @date 2019/1/23 12:37
 */
@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysLogService sysLogService;

    @Override
    @Transactional
    public void save(UserParam param){
        BeanValidator.check(param);
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        // TODO: 邮件和密码同时弄好，上线时.
        String password = "123456";
        try {
            password = PasswordUtil.randomPassword();
        } catch (InterruptedException e) {
            log.error("用户生成密码出错");
            throw new PermissionException("生成密码出错...");
        }
        password = "123456";
        String encryptedPassword = MD5Util.encrypt(password);
        SysUser user = SysUser.builder()
                .username(param.getUsername())
                .telephone(param.getTelephone())
                .mail(param.getMail())
                .password(encryptedPassword)
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        user.setOperator(RequestHolder.getCurrentUser().getUsername());
        user.setOperateTime(new Date());
        user.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        //TODO: sendEmail 上线时使用！
//        Mail mail = new Mail();
//        mail.setSubject("注册成功");
//        mail.setMessage("恭喜您注册成功，密码为：" + password + "请用此密码登录，并且修改密码。");
//        mail.setReceivers(param.getMail());
//        MailUtil.sendMail(mail);
        sysUserMapper.insertSelective(user);
        sysLogService.saveUserLog(null, user);
    }

    @Override
    @Transactional
    public void update(UserParam param) {
        BeanValidator.check(param);
        if (checkEmailExist(param.getMail(), param.getId())) {
            throw new ParamException("邮箱已被占用");
        }
        if (checkTelephoneExist(param.getTelephone(), param.getId())) {
            throw new ParamException("电话已被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新用户不存在");
        SysUser after = SysUser.builder()
                .id(param.getId())
                .username(param.getUsername())
                .telephone(param.getTelephone())
                .mail(param.getMail())
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark())
                .build();
        after.setOperator(RequestHolder.getCurrentUser().getUsername());
        after.setOperateTime(new Date());
        after.setOperateIp(IpUtil.getRemoteIp(RequestHolder.getCurrentRequest()));
        sysUserMapper.updateByPrimaryKeySelective(after);
        sysLogService.saveUserLog(before, after);
    }

    @Override
    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);
    }

    @Override
    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery pageQuery) {
        BeanValidator.check(pageQuery);
        int count = sysUserMapper.countByDeptId(deptId);
        if (count > 0) {
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId, pageQuery);
            return PageResult.<SysUser>builder().total(count).data(list).build();
        }
        return PageResult.<SysUser>builder().build();
    }

    @Override
    public List<SysUser> getAll() {
        return sysUserMapper.getAll();
    }

    private boolean checkEmailExist(String mail, Integer userId) {
        return sysUserMapper.countByMail(mail, userId) > 0;
    }

    private boolean checkTelephoneExist(String telephone, Integer userId) {
        return sysUserMapper.countByTelephone(telephone, userId) > 0;
    }
}
