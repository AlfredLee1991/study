package com.framework.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * 功能描述：添加功能描述.<br/>
 * 
 * #date： 2017年8月17日 下午4:36:32<br/>
 * #author 李旭<br/>
 * #since 1.0.0<br/>
 */
public class DataSql{

    private static Map<String, String> patientNamePhoneMap = new HashMap<>();
    private static Map<String, String> medicineNameCidMap = new HashMap<>();
    private static Map<String, String[]> patientMedicineMap = new HashMap<>();

    static {
        patientNamePhoneMap.put("王贵", "18801295263");
        patientNamePhoneMap.put("蔡小飞", "15818266825");
        patientNamePhoneMap.put("李晓燕", "13321187088");
        patientNamePhoneMap.put("朱红鑫", "13755988460");
        patientNamePhoneMap.put("李芹", "15987877701");
        patientNamePhoneMap.put("李明兴", "13552290960");
        patientNamePhoneMap.put("马凤芹", "13009909782");
        patientNamePhoneMap.put("王红伟", "13614761117");
        patientNamePhoneMap.put("王雪清", "15879641791");
        patientNamePhoneMap.put("王青山", "13701475132");
        patientNamePhoneMap.put("权建英", "13336170518");
        patientNamePhoneMap.put("肖晓平", "18659579927");
        patientNamePhoneMap.put("刘玉森", "15929298428");
        patientNamePhoneMap.put("张建", "15050896335");
        patientNamePhoneMap.put("林世敏", "13178984618");
        patientNamePhoneMap.put("彭文华", "13683690737");
        patientNamePhoneMap.put("蔡伟", "15109979860");
        patientNamePhoneMap.put("崔晓东", "13039676826");
        patientNamePhoneMap.put("周红", "18723124902");
        patientNamePhoneMap.put("廖曼婷", "13659486171");
        patientNamePhoneMap.put("张伟", "18268959953");
        patientNamePhoneMap.put("李香兰", "18206052883");
        patientNamePhoneMap.put("范立敏", "15204869146");
        patientNamePhoneMap.put("吕承武", "15114993625");
        patientNamePhoneMap.put("刘敏", "15332200219");
        patientNamePhoneMap.put("黄勇", "13902541118");
        patientNamePhoneMap.put("梅建辉", "13687122639");
        patientNamePhoneMap.put("徐玲", "18738798381");
        patientNamePhoneMap.put("崔册", "13845770434");
        patientNamePhoneMap.put("万春棋", "13397025232");
        patientNamePhoneMap.put("甘丽岩", "15849593999");
        patientNamePhoneMap.put("李宗友", "13793653998");
        patientNamePhoneMap.put("孙静", "15850491688");
        patientNamePhoneMap.put("李国华", "13172724779");
        patientNamePhoneMap.put("李寒琦", "18155761616");
        patientNamePhoneMap.put("李洪娟", "15930618061");
        patientNamePhoneMap.put("贺健", "13567682315");
        patientNamePhoneMap.put("赵国泰", "13909358728");
        patientNamePhoneMap.put("徐霆", "18116430951");
        patientNamePhoneMap.put("李云峰", "13970063159");
        patientNamePhoneMap.put("张健", "18385868124");
        patientNamePhoneMap.put("郭贻升", "13711980695");
        patientNamePhoneMap.put("蒋先春", "13518715519");
        patientNamePhoneMap.put("宋明武", "13888258925");
        patientNamePhoneMap.put("刘先生", "13373606907");
        patientNamePhoneMap.put("刘小红", "18928170732");
        patientNamePhoneMap.put("王东利", "18131485259");
        patientNamePhoneMap.put("刘振华", "15737031852");
        patientNamePhoneMap.put("孙金玲", "13841330952");
        patientNamePhoneMap.put("施巧玲", "13625140997");
        patientNamePhoneMap.put("周本新", "18692247628");
        patientNamePhoneMap.put("刘秋生", "13161090422");
        patientNamePhoneMap.put("莫小", "18778176517");
        patientNamePhoneMap.put("姚彦华", "15293235491");
        patientNamePhoneMap.put("陈北理", "18559389902");
        patientNamePhoneMap.put("谢世宽", "15215218153");
        patientNamePhoneMap.put("邓井祥", "13846657414");
        patientNamePhoneMap.put("陈国强", "15979172130");
        patientNamePhoneMap.put("王新娟", "15320095020");
        patientNamePhoneMap.put("陈玉婷", "18481812842");
        patientNamePhoneMap.put("扬启秀", "13813266970");
        patientNamePhoneMap.put("翟瑞宝", "15163065533");
        patientNamePhoneMap.put("陈威", "15776593333");
        patientNamePhoneMap.put("杨根福", "13362678176");
        patientNamePhoneMap.put("王学郁", "13553072829");
        patientNamePhoneMap.put("尤建勤", "15757282617");
        patientNamePhoneMap.put("达来", "13754186362");
        patientNamePhoneMap.put("张亮", "13330968888");
        patientNamePhoneMap.put("王文萱", "13659486171");
        patientNamePhoneMap.put("林立", "18777711293");
        patientNamePhoneMap.put("郑金", "18223419821");
        patientNamePhoneMap.put("孙铁臣", "13704228850");
        patientNamePhoneMap.put("张静", "13404538588");
        patientNamePhoneMap.put("张敬义", "15715203625");

        medicineNameCidMap.put("20170629165304714062b73e3d3303d0", "名正30粒");
        medicineNameCidMap.put("2017062916530492205210923f4d58dd", "依尼舒50MG");
        medicineNameCidMap.put("20170629165305021064383c67403a44", "润众7片");
        medicineNameCidMap.put("20170629165305411061b4de0cc704e7", "润众28片(14*2)");
        medicineNameCidMap.put("20170629165305825071f8372c41a81a", "甘平24粒");
        medicineNameCidMap.put("20170629165306175082d40844831b3f", "格尼可12粒");
        medicineNameCidMap.put("20170629165306269080b78d0732c750", "首辅");
        medicineNameCidMap.put("2017062916530641208987a7e020ed49", "苦胶24粒");
        medicineNameCidMap.put("201706291653064550903109f4ef4394", "苦针");
        medicineNameCidMap.put("201706291653065060855b33294df4ea", "小苦针");
        medicineNameCidMap.put("20170629165306884101c0ffe10d2da6", "天丁7片");
        medicineNameCidMap.put("20170629165307245112aa8d18947b03", "速乐10粒(配)");
        medicineNameCidMap.put("2017062916530729509906cc2a703a33", "速乐10粒");
        medicineNameCidMap.put("201706291653074991005e2b4a3e3594", "速乐30粒");
        medicineNameCidMap.put("20170629165307799106744078e6867d", "泰白60片");
        medicineNameCidMap.put("20170629165308595144b072d5f508d7", "依泰");

        patientMedicineMap.put("18801295263", new String[] { "2017062916530492205210923f4d58dd" });
        patientMedicineMap.put("15818266825", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13321187088", new String[] { "20170629165305411061b4de0cc704e7" });
        patientMedicineMap.put("13755988460", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("15987877701", new String[] { "20170629165305411061b4de0cc704e7",
                "20170629165305021064383c67403a44", "20170629165304714062b73e3d3303d0" });
        patientMedicineMap.put("13552290960", new String[] { "2017062916530492205210923f4d58dd" });
        patientMedicineMap.put("13009909782", new String[] { "201706291653074991005e2b4a3e3594" });
        patientMedicineMap.put("13614761117", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("15879641791",
                new String[] { "20170629165305021064383c67403a44", "20170629165305411061b4de0cc704e7" });
        patientMedicineMap.put("13701475132",
                new String[] { "20170629165306884101c0ffe10d2da6", "20170629165307799106744078e6867d" });
        patientMedicineMap.put("13336170518", new String[] { "201706291653074991005e2b4a3e3594" });
        patientMedicineMap.put("18659579927", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("15929298428", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("15050896335",
                new String[] { "20170629165305411061b4de0cc704e7", "20170629165305021064383c67403a44" });
        patientMedicineMap.put("13178984618", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13683690737", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("15109979860",
                new String[] { "201706291653074991005e2b4a3e3594", "20170629165307245112aa8d18947b03" });
        patientMedicineMap.put("13039676826",
                new String[] { "20170629165305411061b4de0cc704e7", "20170629165305021064383c67403a44" });
        patientMedicineMap.put("18723124902", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13659486171",
                new String[] { "2017062916530492205210923f4d58dd", "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("18268959953",
                new String[] { "201706291653074991005e2b4a3e3594", "20170629165307245112aa8d18947b03" });
        patientMedicineMap.put("18206052883",
                new String[] { "20170629165305411061b4de0cc704e7", "20170629165305021064383c67403a44" });
        patientMedicineMap.put("15204869146",
                new String[] { "20170629165305411061b4de0cc704e7", "20170629165305021064383c67403a44" });
        patientMedicineMap.put("15114993625",
                new String[] { "20170629165305411061b4de0cc704e7", "20170629165305021064383c67403a44" });
        patientMedicineMap.put("15332200219", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13902541118",
                new String[] { "20170629165305411061b4de0cc704e7", "20170629165305021064383c67403a44" });
        patientMedicineMap.put("13687122639", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("18738798381", new String[] { "20170629165306269080b78d0732c750" });
        patientMedicineMap.put("13845770434",
                new String[] { "2017062916530729509906cc2a703a33", "201706291653074991005e2b4a3e3594" });
        patientMedicineMap.put("13397025232", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("15849593999", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13793653998",
                new String[] { "2017062916530492205210923f4d58dd", "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("15850491688",
                new String[] { "2017062916530729509906cc2a703a33", "20170629165307245112aa8d18947b03" });
        patientMedicineMap.put("13172724779",
                new String[] { "20170629165305411061b4de0cc704e7", "20170629165305021064383c67403a44" });
        patientMedicineMap.put("18155761616", new String[] { "201706291653074991005e2b4a3e3594" });
        patientMedicineMap.put("15930618061", new String[] { "20170629165305021064383c67403a44" });
        patientMedicineMap.put("13567682315", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13909358728", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("18116430951", new String[] { "2017062916530641208987a7e020ed49" });
        patientMedicineMap.put("13970063159", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("18385868124", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13711980695", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13518715519", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("13888258925", new String[] { "20170629165305411061b4de0cc704e7" });
        patientMedicineMap.put("13373606907", new String[] { "20170629165308595144b072d5f508d7" });
        patientMedicineMap.put("18928170732", new String[] { "201706291653065060855b33294df4ea" });
        patientMedicineMap.put("18131485259", new String[] { "201706291653074991005e2b4a3e3594" });
        patientMedicineMap.put("15737031852", new String[] { "201706291653074991005e2b4a3e3594" });
        patientMedicineMap.put("13841330952", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13625140997", new String[] { "20170629165306884101c0ffe10d2da6" });

        patientMedicineMap.put("18692247628", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("13161090422", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("18778176517", new String[] { "20170629165306175082d40844831b3f" });

        patientMedicineMap.put("15293235491", new String[] { "20170629165305411061b4de0cc704e7" });
        patientMedicineMap.put("18559389902", new String[] { "20170629165308595144b072d5f508d7" });
        patientMedicineMap.put("15215218153", new String[] { "20170629165306884101c0ffe10d2da6" });

        patientMedicineMap.put("13846657414", new String[] { "20170629165305021064383c67403a44" });
        patientMedicineMap.put("15979172130", new String[] { "20170629165305021064383c67403a44" });
        patientMedicineMap.put("15320095020", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("18481812842", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13813266970",
                new String[] { "20170629165306884101c0ffe10d2da6", "20170629165305825071f8372c41a81a" });
        patientMedicineMap.put("15163065533", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("15776593333", new String[] { "201706291653064550903109f4ef4394" });

        patientMedicineMap.put("13362678176", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("13553072829", new String[] { "2017062916530729509906cc2a703a33" });
        patientMedicineMap.put("15757282617", new String[] { "2017062916530641208987a7e020ed49" });
        patientMedicineMap.put("13754186362", new String[] { "201706291653074991005e2b4a3e3594" });
        patientMedicineMap.put("13330968888",
                new String[] { "20170629165305021064383c67403a44", "20170629165305411061b4de0cc704e7" });
        patientMedicineMap.put("13659486171", new String[] { "20170629165306175082d40844831b3f" });
        patientMedicineMap.put("18777711293", new String[] { "20170629165305021064383c67403a44" });
        patientMedicineMap.put("18223419821", new String[] { "20170629165306884101c0ffe10d2da6" });
        patientMedicineMap.put("13704228850", new String[] { "2017062916530729509906cc2a703a33" });
        patientMedicineMap.put("13404538588",
                new String[] { "20170629165305021064383c67403a44", "20170629165305411061b4de0cc704e7" });
        patientMedicineMap.put("15715203625", new String[] { "201706291653064550903109f4ef4394" });
    }

    private static void generateSql() {
        Set<Entry<String, String>> set = patientNamePhoneMap.entrySet();
        Iterator<Entry<String, String>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Entry<String, String> entry = iterator.next();
            String cidOrPhone = entry.getValue();
            String name = entry.getKey();

            StringBuilder patientAccount = new StringBuilder();
            patientAccount.append(
                    "insert into cim_account_info(version,cid, app_code, account_type, account, create_datetime, update_datetime, reg_source) values(");
            patientAccount.append("'2.7.0',");
            patientAccount.append("'").append(cidOrPhone).append("',");
            patientAccount.append("'cim',");
            patientAccount.append("2,");
            patientAccount.append("'").append(cidOrPhone).append("',");
            patientAccount.append("'2017-08-17 17:00:00',");
            patientAccount.append("'2017-08-17 17:00:00',");
            patientAccount.append("4);");
            System.out.println(patientAccount.toString());

            StringBuilder patientInfo = new StringBuilder();
            patientInfo.append(
                    "insert into cim_patient_detail_info(version,cid, app_code, name, name_pinyin, phone_number, create_datetime, update_datetime) values(");
            patientInfo.append("'2.7.0',");
            patientInfo.append("'").append(cidOrPhone).append("',");
            patientInfo.append("'cim',");
            patientInfo.append("'").append(name).append("',");
            String pinyin = PinyinHelper.convertToPinyinString(name, "", PinyinFormat.WITHOUT_TONE);
            patientInfo.append("'").append(pinyin).append("',");
            patientInfo.append("'").append(cidOrPhone).append("',");
            patientInfo.append("'2017-08-17 17:00:00',");
            patientInfo.append("'2017-08-17 17:00:00');");
            System.out.println(patientInfo.toString());

            StringBuilder attention = new StringBuilder();
            attention.append(
                    "insert into cim_patient_attention_to_pharmacy(version, cid, app_code, create_datetime, update_datetime, patient_cid, pharmacy_cid) values(");
            attention.append("'2.7.0',");
            attention.append("'").append(cidOrPhone).append("',");
            attention.append("'cim',");
            attention.append("'2017-08-17 17:00:00',");
            attention.append("'2017-08-17 17:00:00',");
            attention.append("'").append(cidOrPhone).append("',");
            attention.append("'46f1d0acee9fafc93586f48c');");
            System.out.println(attention.toString());

        }
    }

    public static void main(String[] args) {
        generateSql();
    }
}
