package com.rjxx.taxeasy.bizcomm.security;

import com.rjxx.taxeasy.domains.*;
import com.rjxx.taxeasy.service.*;
import com.rjxx.utils.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2016/2/29.
 */
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService, Serializable {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private transient YhService yhService;

    @Autowired
    private transient XfService xfService;

    @Autowired
    private transient SkpService skpService;

    @Autowired
    private transient PrivilegesService privilegesService;

    @Autowired
    private transient PrivilegeTypesService privilegeTypesService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
        	Map nparams = new HashMap<>();
        	nparams.put("dlyhid", s);
            Yh yh = yhService.findOneByParams(nparams);
            if (yh == null) {
                throw new UsernameNotFoundException(s + " not found");
            }
            List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_LOGIN_USER"));
            List<Xf> xflist = xfService.getXfListByYhId(yh.getId());
            List<Skp> skpList = skpService.getSkpListByYhId(yh.getId());
            WebPrincipal webPrincipal = new WebPrincipal();
            String roleIds = yh.getRoleids();
            if (StringUtils.isBlank(roleIds)) {
                throw new UsernameNotFoundException(s + " no privileges");
            }
            List<Integer> paramsList = new ArrayList<>();
            String[] arr = roleIds.split(",");
            for (String str : arr) {
                paramsList.add(Integer.valueOf(str));
            }
            Map params = new HashMap<>();
            params.put("roleIds", paramsList);
            List<Privileges> privilegesList = privilegesService.findByRoleIds(params);
            List<PrivilegeTypes> privilegeTypesList = privilegeTypesService.findAllByParams(null);
            List<PrivilegeTypes> list = new ArrayList<>();
            for (PrivilegeTypes privilegeTypes : privilegeTypesList) {
                boolean flag = false;
                for (Privileges privileges : privilegesList) {
                    if (privilegeTypes.getId() == privileges.getPrivilegetypeid()) {
                        flag = true;
                    }
                }
                if (!flag) {
                    list.add(privilegeTypes);
                }
            }
            privilegeTypesList.removeAll(list);
            webPrincipal.setPrivilegesList(privilegesList);
            webPrincipal.setPrivilegeTypesList(privilegeTypesList);
            webPrincipal.setYhid(yh.getId());
            webPrincipal.setPassword(yh.getYhmm());
            webPrincipal.setDlyhid(yh.getDlyhid());
            webPrincipal.setYhmc(yh.getYhmc());
            webPrincipal.setSjhm(yh.getSjhm());
            webPrincipal.setYx(yh.getYx());
            webPrincipal.setXb(yh.getXb());
            webPrincipal.setSup(yh.getSup());
            webPrincipal.setXfList(xflist);
            webPrincipal.setSkpList(skpList);
            if (!xflist.isEmpty()) {
                webPrincipal.setXfmc(xflist.get(0).getXfmc());
                webPrincipal.setXfid(xflist.get(0).getId());
                webPrincipal.setXfsh(xflist.get(0).getXfsh());
            }
            webPrincipal.setGsdm(yh.getGsdm());
            UserDetails userDetails = new SecurityUser(webPrincipal, authorities);
            return userDetails;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

}
