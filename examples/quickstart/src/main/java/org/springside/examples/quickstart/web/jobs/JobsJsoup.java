package org.springside.examples.quickstart.web.jobs;

import java.net.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.examples.quickstart.entity.JobInfo;
import org.springside.examples.quickstart.repository.JobInfoDao;

@Lazy(false)
@Async
@Component
public class JobsJsoup {
	public static Map<String, Object[]> vmap = new HashMap<String, Object[]>();

	@Autowired
	private JobInfoDao jobInfoDao;

	public void collect(String url) throws Exception {
		String[] ip = ProxyIP.getRandomProxyIP();

		Document doc = null;

		doc = connection(url, doc, ip);
		int ii=0;
		while (doc == null) {
			ip = ProxyIP.getRandomProxyIP();
			doc = connection(url, doc, ip);
			System.out.println(new Date() +"yichang  "+(ii++ ));
		}

		Elements elements = doc.getElementsByClass("sojob-list");
		for (Element element : elements) {

			Elements eleLi = element.getElementsByTag("li");
			for (Element info : eleLi) {

				// System.out.println("--------------------------------------------");
				String qiyxz = info.getElementsByTag("i").text();
				// System.out.println(qiyxz);

				String jobinfo = info.getElementsByClass("job-info").text();
				String[] jobi = jobinfo.split(" ");
				// System.out.println(jobi[5]);
				if (jobi[5].contains("分钟") || jobi[5].contains("小时")) {
					for (int i = 0; i < jobi.length; i++) {
						// System.out.println(i + " " + jobi[i]);
					}

					String companyinfo = info.getElementsByClass("company-info").text();
					String[] companyi = companyinfo.split(" ");
					for (int i = 0; i < companyi.length; i++) {
						// System.out.println(i + " " + companyi[i]);
					}

					int times = 0;
					String infokey = jobi[5] + "|" + jobi[0] + "|" + jobi[1] + "|" + companyi[0];
					if (vmap.containsKey(infokey)) {
						times = Integer.valueOf(vmap.get(infokey)[0].toString()) + 1;
					}
					vmap.put(infokey, new Object[] { times, jobinfo, companyinfo, jobi[5], qiyxz });
				}
			}
		}

	}

	private Document connection(String url, Document doc, String[] ip) {
		try {
			doc = Jsoup.connect(url).proxy(Proxy.Type.HTTP, ip[0], Integer.valueOf(ip[1])).userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)").cookie("sd65fjl", "token=sfs;;7vvv").timeout(10000).post();
		} catch (Exception e) {
			doc = null;
		}
		return doc;
	}

	@Transactional
	@Scheduled(cron = "0 0/20 * * * ? ") // 间隔25分钟执行
	public void execute() throws Exception {
		System.out.println(new Date() + "--------------------------------------------");
		try {
			String[] url = new String[] { "https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E6%80%BB%E7%9B%91",
					"https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E6%80%BB%E7%9B%91&curPage=1",
					"https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E6%80%BB%E7%9B%91&curPage=2",
					"https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E7%BB%8F%E7%90%86",
					"https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E7%BB%8F%E7%90%86&curPage=1" };
			for (String string : url) {
				collect(string);
				System.out.println("================4========");
				Set<String> infos = vmap.keySet();
				Iterator<String> info = infos.iterator();
				StringBuffer sb = new StringBuffer(300);
				while (info.hasNext()) {
					String jobcomp = info.next();
					int in = Integer.valueOf(vmap.get(jobcomp)[0].toString());
					System.out.println(in + "   " + jobcomp);
					sb.append(in + "   " + jobcomp).append("/n");
				}

				// Thread.sleep(50000);
				Thread.sleep(10000);
			}

			Set<String> infos = vmap.keySet();
			Iterator<String> info = infos.iterator();
			while (info.hasNext()) {
				String jobcomp = info.next();
				Object[] is = vmap.get(jobcomp);
				int in = Integer.valueOf(vmap.get(jobcomp)[0].toString());

				// { times, jobinfo, companyinfo ,jobi[5],qiyxz}
				// jobi[5]+"|"+jobi[0]+"|"+jobi[1]+"|" + companyi[0];
				String companyinfo = (String) is[2];
				String jobinfo = (String) is[1];
				JobInfo jobii = new JobInfo();
				jobii.setCompany(companyinfo.split(" ")[0]);
				jobii.setCreateDate(new Date());
				jobii.setJobxz(is[4] + "");
				// 技术总监 60-90万 上海 学历不限 8年工作经验 2小时前
				jobii.setSalary(jobinfo.split(" ")[1]);
				jobii.setTitile(jobinfo.split(" ")[0]);
				jobii.setType("liepin");
				jobii.setUrl("");
				jobii.setJobtime(jobinfo.split(" ")[5]);
				jobInfoDao.save(jobii);
			}

			vmap = new HashMap<String, Object[]>();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
