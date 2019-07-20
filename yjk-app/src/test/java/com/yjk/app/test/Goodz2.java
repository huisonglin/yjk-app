package com.yjk.app.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjk.common.dao.RentModelMapper;
import com.yjk.common.dao.RentSpecMapper;
import com.yjk.common.dao.RentTwoStageModeMapper;
import com.yjk.common.entity.RentalModelDO;
import com.yjk.common.entity.RentalSpecDO;
import com.yjk.common.entity.RentalTwoStageModeDO;

/*@RunWith(SpringRunner.class)  
@SpringBootTest  */
public class Goodz2 {
	@Autowired
	RentModelMapper rentModelMapper;
	@Autowired
	RentTwoStageModeMapper rentTwoStageModeMapper;
	@Autowired
	RentSpecMapper rentSpecMapper;
	
	@Test
	public void test() {
		String a="履带式：1-3吨微挖、6吨、5-8吨、9-13吨、15-18吨、20吨、22-25吨、26-32吨、35-40吨、45-55吨、56-75吨、76-90吨、100-200吨、220-360吨、400吨及以上\r\n" + 
				"轮挖：6吨、7-10吨、6-10吨带破碎、6-10吨带夹木、12-15吨、12-20吨带破碎、18-25吨、18-25吨带破碎、30吨及以上、\r\n" + 
				"加长臂：14-18米、14-18米带破碎、20-25米、20-25米带破碎、26-32米、15-20米贝壳斗、18-22拆楼机、24-28米拆楼机、30米以上拆楼机\r\n" + 
				"伸缩臂抓斗：16-20米、22-28米、30米及以上\r\n" + 
				"水挖：60-120加长臂、60-150、150-200加长臂、200、220-260、220-260加长臂、300加长臂\r\n" + 
				"带破碎：1-3吨破碎锤、5-10吨破碎锤、12-18吨破碎锤、20吨破碎锤、22-28吨破碎锤、30-40吨破碎锤、45-60吨破碎锤、65吨以上破碎锤、30-60吨重型破碎锤、20-40吨液压切割锯、13-30吨带液压钳、20-35吨液压剪、15-25吨液压破碎斗、26-40吨液压破碎斗、\r\n" + 
				"其他属具：6-15吨平板震动夯、18-26吨平板震动夯、6-15吨挖树钩、6-15吨螺旋钻机、20-26吨螺旋钻机、15-30吨抓木器、20-35吨单钩松土器、40-60吨单钩松土器、30-50吨一体式岩石臂、55-70吨一体式岩石臂\r\n" + 
				"挖树机：60-150型挖机带挖树钩、下铲式60-80型、链锯60-80型、链锯100-120型、履带吊树机100-200型、螺旋钻30-50型、螺旋钻60-80型\r\n" + 
				"挖泥船：船载20-36挖机、船载40-60吨挖机、绞吸60-150方、绞吸200-300方、绞吸400-700方、绞吸800-1500方、绞吸2000方以上、链斗60-150方、链斗200-300方、射吸100-200方、射吸300-500方\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"推土机\r\n" + 
				"干地型：60-100、130、120-140、160、160-180带三齿松土器、180-240、200-220带三齿松土器、220-320带单钩、360-600带单钩、700-1000及以上、\r\n" + 
				"湿地型：80-100、130、120-140、160、180-200、220-240、160-180超湿地、D1-2超湿地、D3超湿地\r\n" + 
				"造型机：D3-D4干地、D5-D6干地、D5-D6湿地、D3-D4湿地、D3-D6推耙机\r\n" + 
				"轮式：180-240马力、300马力及以上\r\n" + 
				"装载机\r\n" + 
				"轮式：1-2吨、1-2吨夹木型、3吨、3-4吨侧卸型、3-5吨夹木型、5吨、5-6吨侧卸、6-8吨、10-12吨及以上、5-6吨带垂直液压夯\r\n" + 
				"滑移和履带：1吨级、2吨级、1-2吨带清扫机、1-2吨带铣刨机、1-2吨清扫加铣刨、1-2吨在破碎锤、2-3吨履带式、4-6吨履带式\r\n" + 
				"俩头忙：7-8吨、9吨及以上、7-9带破碎、7-9吨带六合一斗\r\n" + 
				"铲运机：2.5-3方牵引式、4-6方牵引式、8-12方牵引式、6-9方自行式、10-18方自行式\r\n" + 
				"路基土方机械\r\n" + 
				"平地机：100-150、160-180、200-240、260-320、360及以上、2-3吨牵引式、4-6吨牵引式、80-120马力披挂式、150-200马力披挂式\r\n" + 
				"静压路机：1-3吨、5-10吨、12-15吨、18-21吨、22-25吨、26吨及以上\r\n" + 
				"单钢轮振动压路机：6-12吨、14-20吨、22吨、14-22吨带羊角碾、24-26吨带羊角碾、24-32吨、22-26吨全液压双驱、28-36吨全液压双驱、24-32吨垂直振动、36吨以上、20-26吨拖振\r\n" + 
				"冷再生机：200-260、300-460、500-700、400-600加水车、拖拉机悬挂小型\r\n" + 
				"路拌机：200-230、240-280、300及以上、小宝马80-100马力、小宝马120-150马力、小宝马160马力及以上\r\n" + 
				"铧犁：2-3铧犁、4-5铧犁、重型4-5铧犁、牵引五铧犁\r\n" + 
				"水泥撒布车：12-16方、18-22方及以上\r\n" + 
				"洒水车：4-6方、8-12方、14-18方、20方及以上、\r\n" + 
				"稳定土拌合站：300-500型、600-800型及以上\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"路面机械\r\n" + 
				"双钢轮振动：1-3吨、5-8吨、9-12吨、15-18吨、22吨及以上、\r\n" + 
				"胶轮：16-20吨、3-5吨前钢后胶、6-8吨前钢后胶、22-26吨、28-32吨、36-40吨及以上\r\n" + 
				"单钢轮振动：16-20吨、22吨、24-28吨、30-36吨、24-32吨全液压双驱、36吨以上\r\n" + 
				"冲击夯压路机：20-25千焦三边型、20-25千焦五边梅花型、25-32千焦三边、35千焦以上三边型、装载机带垂直液压夯\r\n" + 
				"路面破碎机：160-320门夯式、200-300多点锤式、320-400多点锤式\r\n" + 
				"\r\n" + 
				"铣刨机：100-150、160-195、200-250及以上、150-200混凝土型、轮式0.35-0.5米、轮式1-1.5米\r\n" + 
				"沥青洒布车：4-8方、10-16方\r\n" + 
				"封层车：稀浆封层8-12方、稀浆封层14-18方、同步碎石8-12方、同步碎石14-18方\r\n" + 
				"\r\n" + 
				"热再生修补车：小型、中型、大型\r\n" + 
				"沥青摊铺机：2.5-4.2米轮式伸缩、6-7.5米直板、8-9米直板、12米直板、15米及以上直板、0.8-3.2米轮式伸缩、2.5-6米伸缩板、7--9米伸缩板、12米伸缩板\r\n" + 
				"水稳摊铺机：2.5-4.5米轮式伸缩、3-6米轮式伸缩、7.5米、9米、12米、15米及以上、路肩土专用型\r\n" + 
				"水泥滑模摊铺机：侧滑模15型、侧滑模25型、500-800型、900-1200型、1500型以上\r\n" + 
				"开沟机：0.5-1米链条型、1.2-1.5米链条型、1.5米以上链条型、0.5-1.0米刀盘型、1.2-1.5米刀盘型\r\n" + 
				"清扫机：滑移清扫机1-2吨、30装载机带、高压环保清扫机\r\n" + 
				"护栏打桩机：10-20型、30-50型\r\n" + 
				"沥青搅拌站：400-800型、1000-2000型、2200-3000型、3200-4000型\r\n" + 
				"水稳搅拌站：200-400型、500-800型、900型以上\r\n" + 
				"\r\n" + 
				"起重机\r\n" + 
				"汽车吊：3-5吨、6-12吨、5-12吨带吊篮、16-25吨带吊篮、16-20吨、25吨、25吨五节臂、30-45吨、50-70吨、75-100吨、110-150吨、160-200吨、220-350吨、400-600吨、650-850吨、600-1200吨风电专用、900-1500吨、1600吨及以上\r\n" + 
				"随车吊：3-8吨、12-16吨、8-16吨带吊篮、20-25吨、16-30吨折臂吊、35-50吨超低折臂吊、90-120吨超低折臂、150吨以上超低折臂\r\n" + 
				"履带吊：25-50吨、25-50吨带强夯机、60-80吨、90-130吨、150-200吨、220-350吨、400-600吨、700-900吨、1000-1500吨、2000-3600吨及以上\r\n" + 
				"叉车：1-2吨、1-3吨带抱夹、4-6吨带抱夹、1-3吨带夹桶、3吨、3-5吨越野型、5吨、6-8吨、10-12吨、16吨及以上、20-25吨叉装车、30-40吨叉装车、50吨及以上叉装车\r\n" + 
				"\r\n" + 
				"越野吊车：20-35吨、50-80吨、100吨及以上\r\n" + 
				"平板车\r\n" + 
				"本地平板车：4.2-6.8米、9.6-11米、13米、16-17.5米\r\n" + 
				"中短途回程平板车：4.2-6.8米、9.6-11米、13米、16-17.5米、16米三线六轴、16米五线十轴\r\n" + 
				"长途平板车：6.8-11米、13米、16-17.5米、16米三线六轴、16米五线十轴\r\n" + 
				"超低超长板车：、50-80吨、90-200吨及以上、风电扇叶运输车、风电扇叶转运车\r\n" + 
				"环卫机械\r\n" + 
				"高压清洗车：4-6方、8-12方、14方及以上、\r\n" + 
				"洒水车：4-6方、8-12方、14-18方、20方及以上、\r\n" + 
				"\r\n" + 
				"洒水雾炮抑尘车：6-10方10米、8-12方15米、10-16立方20米、12-20立方25米、22方以上25米\r\n" + 
				"吸污车：4-8方、9-12方、15方以上\r\n" + 
				"下水管道清洗车：小型、大型\r\n" + 
				"清洗扫地车：2-3米、3.5-4.0米、清洗护栏车\r\n" + 
				"垃圾压实机：20-25吨、28-32吨、40-45吨及以上\r\n" + 
				"环卫推土机：100-140马力、160马力、220马力及以上、\r\n" + 
				"垃圾转运车：8-12方、14-20方\r\n" + 
				"混凝土设备\r\n" + 
				"臂架泵车：18-32米、37-46米、50-66米、70-88米、92-110米及以上、\r\n" + 
				"固定泵：60-86方车载、90-110方车载、45-60方电动、70-80方电动、90-120方电动、60-80方柴油、70-90方柴油、100-120方柴油、\r\n" + 
				"搅拌车：2-6方、8-10方、12-16方、18方及以上\r\n" + 
				"湿喷机：干喷机5D-7D型、湿喷机60-90型、湿喷台车20-30型、湿喷台车08-15型、\r\n" + 
				"\r\n" + 
				"搅拌站：60-120方/小时、140-200方/小时、240-270方/小时及以上\r\n" + 
				"干混砂浆搅拌站：40吨/小时、60吨/小时、80吨/小时、120吨/小时\r\n" + 
				"\r\n" + 
				"自卸车\r\n" + 
				"环保型：4-6方、8-12方、14-18方、20-24方及以上\r\n" + 
				"非环保型：2-4方四不像、6-8方四不像、10方以上大四不像、4-6方608、8-12方、14-20方、22-28方、30方及以上\r\n" + 
				"铰接式：20-25吨、30-40吨、45-50吨及以上\r\n" + 
				"履带式运泥车：20-30型、50-60型及以上\r\n" + 
				"高空作业车（平台）\r\n" + 
				"车载高空作业车：6-9米、10-15米、16-24米、26米及以上、\r\n" + 
				"剪叉式高空平台：6-10米、12-16米及以上、12-16米重载型\r\n" + 
				"臂架式高空车：6-9米、12-16米、18-24米、26-40米、45-60米\r\n" + 
				"伸缩臂叉车：6-9米、12-20米、25米以上\r\n" + 
				"施工吊篮：单人型、1*3型、1*6型、1.5*6型\r\n" + 
				"高空搬运云梯车：16-25米、28-32米、35米以上、32-46米消防登高车、60-100米消防登高、\r\n" + 
				"室内高空蜘蛛车：17-26米、28-36米、39-48米、50米及以上、蜘蛛吊1-3吨、蜘蛛吊4-5吨\r\n" + 
				"钻机系列\r\n" + 
				"旋挖钻：100--150型轮式、100-120型、150-180型、220-280型、320-360型、420-460型、500型及以上\r\n" + 
				"\r\n" + 
				"长螺旋钻机：16-25米、30-45米及以上、\r\n" + 
				"循环钻机：1-1.5米、2-2.5米、3-4米、5-7寸反循环、8-10寸反循环、12-15寸反循环\r\n" + 
				"水平定向钻顶拉管机：110-150型、180-260型、320-400型、450-760型、800-1500型、1600-4000型、5000型及以上、100-200吨人工顶管机、0.8-1.5米盾构顶管机、1.8-2.6米盾构顶管机、气动夯管机\r\n" + 
				"深井钻机：180-300米、400-530米、600米及以上、降水井专用钻机\r\n" + 
				"建筑和桩基机械\r\n" + 
				"\r\n" + 
				"振动打桩机：150-200型、200-260型光伏桩、220-360型、400-550型、600-800型及以上\r\n" + 
				"静压桩机：80-180吨、200-460吨、500-800吨、860-1200吨及以上、\r\n" + 
				"柴油打桩机：轮式20-23型、筒式36-50型、筒式62-72型、筒式80-100型、导杆式36-55型、导杆式65-100型\r\n" + 
				"\r\n" + 
				"双轮铣槽机：25-32型、40-55型、62-80型、100-135型及以上、链条50型\r\n" + 
				"地下连续墙抓斗：22-26型、35-46型、50-70型及以上、100-150型钻孔机、220-260型钻孔机\r\n" + 
				"锚杆机：80-120型、130-160型\r\n" + 
				"混凝土湿喷机：干喷机5D-7D型、湿喷机60-90型、湿喷台车20-30型、湿喷台车08-15型、\r\n" + 
				"强夯机：200-320型、400-500型、600-800型、1000型及以上、\r\n" + 
				"\r\n" + 
				"塔吊：20-30轮胎型、30-40型、30-50折臂型、50-63型、70-80型、90-125型、140-160型、180-250型、260-320型、120-160动臂型、200-320动臂型、\r\n" + 
				"施工升降机：1-2吨、3-5吨\r\n" + 
				"钢板桩：U型45、U型75、U型57、U型9米*75、Z型65、Z型85\r\n" + 
				"钢管桩：25-30、32-45、100-200、300以上\r\n" + 
				"试压块：500吨、1000吨\r\n" + 
				"脚手架：30-40型、50-60型\r\n" + 
				"施工吊篮：单人型、1*3型、1*6型、1.5*6型\r\n" + 
				"\r\n" + 
				"搅拌机：小型圆盘型、200-350滚筒型、双轴卧式、自动提升500-800型、自动提升1000-1500型\r\n" + 
				"工地洗轮机：简易型、自动洗轮型、渣土车洗轮型、固定雾炮机\r\n" + 
				"\r\n" + 
				"铁路隧道桥梁机械\r\n" + 
				"盾构机：土压平衡6-6.5米、土压平衡7.6-8.6米、泥水平衡0.8-3.2米、泥水平衡4-6.5米、泥水平衡7.6-8.6米、泥水平衡9.5-12米、泥水平衡15米、岩石型6.4-7.5米、岩石型8.6-9.5米\r\n" + 
				"隧道掘进机：120-160型、200-260型、300-420型、500-620型\r\n" + 
				"龙门吊：10-30吨、40-80吨、90-150吨、180-300吨及以上、900吨提梁机\r\n" + 
				"架桥机：100-300吨、400-800吨、900吨、600-900吨隧道专用型\r\n" + 
				"运梁车：30-50吨挂车、60-100吨挂车、150-600吨运梁车、900吨运梁车\r\n" + 
				"通风设备：压入式100-300型、压入式500型以上、射流型100-300型\r\n" + 
				"混凝土湿喷机：干喷机5D-7D型、湿喷机60-90型、湿喷台车20-30型、湿喷台车08-15型、\r\n" + 
				"侧卸装载机：3吨型、5吨型、6-8吨型、10吨以上型、井下8-12吨型、井下15吨以上\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"轨枕铺设（更换）机：12-24吨挖机配铺设机、10-24吨挖机配更换机、轨枕铺设列车、10-24吨挖机配振捣器\r\n" + 
				"矿山机械\r\n" + 
				"矿用卡车：50-100吨、120-200吨、220-400吨、450吨以上\r\n" + 
				"移动破碎机（站）：挖机配破碎斗10-20型、履带90-120型、履带180-240型、履带360-450型、轮胎90-150型、轮胎240-360型\r\n" + 
				"空压机：螺杆5-10千瓦、螺杆12-25千瓦、螺杆30千瓦以上、柴油动力25-50千瓦、柴油动力60千瓦以上\r\n" + 
				"潜孔钻机：单臂架凿岩150-400型、双臂架凿岩200-300、勘探钻机、\r\n" + 
				"二氧化碳爆破设备：小型、大型\r\n" + 
				"液压破裂棒：小型、大型\r\n" + 
				"井下设备：井下装载机3-6吨、井下装载机8吨以上、井下运输车\r\n" + 
				"\r\n" + 
				"特种物资设备\r\n" + 
				"住人集装箱：6米*3米带空调、6米*3米、6米*3米双层\r\n" + 
				"发电机组：20-30千瓦、20-50千瓦静音型、40-90千瓦、60-120千瓦静音型、100-180千瓦、200-300千瓦、400-500千瓦、600-800千瓦、900-1600千瓦及以上\r\n" + 
				"铺路钢板：6米*1米*1.2公分、6米*1.5米*1.5公分、钢板箱6*1.8*22公分、钢板箱6*2*25公分\r\n" + 
				"槽钢：12-18型、20-25型、20-32工字钢、36-45工字钢\r\n" + 
				"水泵：3-6寸清水泵、3-6寸污水泵、3-6寸汽柴油泵站、8-10寸清水泵、8-10寸污水泵、5-10寸泥浆泵、8-14寸柴油泵站、16-24寸柴油泵站、8-14寸防汛应急泵车、16-24寸防汛应急泵车\r\n" + 
				"土石方供应：砖渣、黄土、沙土、杂土、混凝土块和石块、沙子、石子\r\n" + 
				"土石方求购：砖渣、黄土、沙土、杂土、混凝土块和石块、沙子、石子\r\n" + 
				"试压块：200-500吨、600-1000吨\r\n" + 
				"\r\n" + 
				"加油车：柴油、汽油\r\n" + 
				"";
		
		
		Long modelid = null;
		Long twoId = null;
		Long specId = null;
		String[] items = a.split("\r\n");
		for (String string : items) {
			if(!string.equals("不限")&&!string.equals("不限、")&&!string.equals(""))
				if(!string.contains("：")) {
					System.out.println(string);
					System.out.println("-------------");
					
/*					RentalModelDO rentalModelDO = new RentalModelDO();
					rentalModelDO.setName(string);
					rentalModelMapper.insertSelective(rentalModelDO);
					System.out.println("rentalModelDO = "+rentalModelDO.getId());
					modelid = rentalModelDO.getId();*/
				}else {
/*					String[] split = string.split("：");
					System.out.println(split[0]);
					
					RentalTwoStageModeDO rentalTwoStageModeDO = new RentalTwoStageModeDO();
					rentalTwoStageModeDO.setName(split[0]);
					rentalTwoStageModeDO.setRentalModelId(modelid);
					rentalTwoStageModeMapper.insertSelective(rentalTwoStageModeDO);
					twoId = rentalTwoStageModeDO.getId();
					System.out.println("-------------");
					String[] split2 = split[1].split("、");
					for (String string2 : split2) {
						System.out.println(string2);
						RentalSpecDO rentalSpecDO = new RentalSpecDO();
						rentalSpecDO.setName(string2);
						rentalSpecDO.setRentalTwoStageModeId(twoId);
						rentalSpecMapper.insert(rentalSpecDO);
					}*/
				}

			

		}
	}
	
}
