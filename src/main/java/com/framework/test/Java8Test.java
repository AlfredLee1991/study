package com.framework.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * 功能描述：java8新特性，foreach使用.<br/>
 * 
 * #date： 2018年12月10日 上午8:33:34<br/>
 * #author 8104485-李旭<br/>
 * #since 1.0.0<br/>
 */
public class Java8Test{

    public static void main(String[] args) throws ParseException {
       /* String[] atp = { "Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych", "Juan Martin Del Potro" };
        List<String> players = Arrays.asList(atp);

        // 以前的循环方式
        // for (String player : players) {
        // System.out.print(player + "; ");
        // }

        // 使用 lambda 表达式以及函数操作(functional operation)
        // players.forEach((player) -> System.out.print(player + "; "));

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);*/

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String str1 = "2019-12";
        String str2 = "2020-01";
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(str1));
        aft.setTime(sdf.parse(str2));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        System.out.println(Math.abs(month + result));*/
        
       /* List<User> list = new ArrayList<>();
        User user = new User();
        user.setName("name1");
        user.setPwd("pwd1");
        list.add(user);
        User user2 = new User();
        user2.setName("name2");
        list.add(user2);
//        Map<String, String> userMap = list.stream()
//                .collect(Collectors.toMap(user -> user.getName(),
//                        user -> Optional.ofNullable(user.getPwd()).orElse("0")));
        Map<String, String> userMap = list.stream()
                .collect(Collectors.toMap(u -> u.getName(),
                        u -> Optional.ofNullable(u.getPwd()).orElse("0")));
                System.out.println(userMap.toString());*/
        
        List<OrgProductResDTO> orgProductIds = new ArrayList<>();
        OrgProductResDTO dto1 = new OrgProductResDTO();
        dto1.setOrgPersonId("person1");
        dto1.setCrmOrganizationId("org1");
        dto1.setPrdProductId("prd1");
        orgProductIds.add(dto1);
        OrgProductResDTO dto2 = new OrgProductResDTO();
        dto2.setOrgPersonId("person1");
        dto2.setCrmOrganizationId("org1");
        dto2.setPrdProductId("prd2");
        orgProductIds.add(dto2);
        OrgProductResDTO dto3 = new OrgProductResDTO();
        dto3.setOrgPersonId("person1");
        dto3.setCrmOrganizationId("org2");
        dto3.setPrdProductId("prd1");
        orgProductIds.add(dto3);
        OrgProductResDTO dto4 = new OrgProductResDTO();
        dto4.setOrgPersonId("person1");
        dto4.setCrmOrganizationId("org2");
        dto4.setPrdProductId("prd3");
        orgProductIds.add(dto4);
        OrgProductResDTO dto5 = new OrgProductResDTO();
        dto5.setOrgPersonId("person2");
        dto5.setCrmOrganizationId("org3");
        dto5.setPrdProductId("prd4");
        orgProductIds.add(dto5);
        
        System.out.println(orgProductIds.stream().collect(Collectors.toMap(item -> item.getPrdProductId(), item -> {
            Map<String, OrgProductResDTO> inner = new HashMap<>();
            inner.put(item.getCrmOrganizationId(), item);
            return inner;
        }, (v1, v2) -> {
            v2.putAll(v1);
            return v2;
        })));
    }
}
