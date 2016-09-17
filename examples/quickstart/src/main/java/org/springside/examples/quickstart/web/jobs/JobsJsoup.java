package org.springside.examples.quickstart.web.jobs;


import java.net.Proxy;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
	public static Map<Object, Object> ipinfos = new HashMap<Object, Object>();

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
//			System.out.println("--------------------------------------------");
//			System.out.println(eleLi.html());
//			System.out.println("--------------------------------------------");
			for (Element info : eleLi) {
				String zwurl = null;
				Elements jobname = info.getElementsByClass("job-name");
				for (Element element2 : jobname) {
					Elements elementsByTag = element2.getElementsByTag("a");
					zwurl = elementsByTag.attr("href") ;
				}
				
				String qiyxz = info.getElementsByTag("i").text();
				// System.out.println(qiyxz);

				String jobinfo = info.getElementsByClass("job-info").text();
				String[] jobi = jobinfo.split(" ");
				// System.out.println(jobi[5]);
				if (true||jobi[5].contains("分钟") || jobi[5].contains("小时")) {
					for (int i = 0; i < jobi.length; i++) {
						// System.out.println(i + " " + jobi[i]);
					}

					String companyinfo = info.getElementsByClass("company-info").text();
					String[] companyi = companyinfo.split(" ");
					for (int i = 0; i < companyi.length; i++) {
						// System.out.println(i + " " + companyi[i]);
					}

					int times = 0;
					String infokey = jobi[0]+" "+jobi[1]+" "+jobi[2]+" "+jobi[3]+" "+jobi[4] + "|" + companyinfo;
					System.out.println("vmap.size() = "+vmap.size());
					if (vmap.containsKey(infokey)) {
						times = Integer.valueOf(vmap.get(infokey)[0].toString()) + 1;
						vmap.put(infokey, new Object[] { times, jobinfo, companyinfo, jobi[5], qiyxz ,zwurl,jobinfo,companyinfo,false});
					}else{
						vmap.put(infokey, new Object[] { times, jobinfo, companyinfo, jobi[5], qiyxz ,zwurl,jobinfo,companyinfo,true});
					}
				}
			}
		}

	}

	private Document connection(String url, Document doc, String[] ip) {
		try {
			System.out.println(url +"  "+ ip[0]);
			doc = Jsoup.connect(url).proxy(Proxy.Type.HTTP, ip[0], Integer.valueOf(ip[1])).userAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)").cookie("sd65fjl", "token=sfs;;7vvv").timeout(12000).post();
		} catch (Exception e) {
			doc = null;
		}
		return doc;
	}
	
	@Scheduled(cron = "0 0/30 * * * ? ") // 间隔25分钟执行
	public void execute() throws Exception {
		
		
		System.out.println(new Date() + "--------------------------------------------");
		try {
			String[] url = new String[] { 
					
					
					
			};
			
			List<String> urllist = new ArrayList<String>();
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=&curPage=1");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E6%80%BB%E7%9B%91");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E6%80%BB%E7%9B%91&curPage=1");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E6%80%BB%E7%9B%91&curPage=2");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E6%80%BB%E7%9B%91&curPage=3");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E6%80%BB%E7%9B%91&curPage=4");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E7%BB%8F%E7%90%86");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E7%BB%8F%E7%90%86&curPage=1");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E7%BB%8F%E7%90%86&curPage=2");
			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=%E6%8A%80%E6%9C%AF%E7%BB%8F%E7%90%86&curPage=3");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=1&sortFlag=15&fromSearchBtn=1&headckid=4c766a80cd31ce1a&key=%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E5%B8%88");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&headckid=4bd94d46272257c2&key=%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E5%B8%88&ckid=4bd94d46272257c2&curPage=1");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&headckid=4bd94d46272257c2&key=%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E5%B8%88&ckid=4bd94d46272257c2&curPage=2");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&headckid=4bd94d46272257c2&key=%E7%B3%BB%E7%BB%9F%E6%9E%B6%E6%9E%84%E5%B8%88&ckid=4bd94d46272257c2&curPage=3");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=1&sortFlag=15&fromSearchBtn=1&key=%E7%A7%BB%E5%8A%A8%E7%AB%AF");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&key=%E7%A7%BB%E5%8A%A8%E7%AB%AF&ckid=29ea10711a9d50cb&headckid=29ea10711a9d50cb&curPage=1");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&key=%E7%A7%BB%E5%8A%A8%E7%AB%AF&ckid=29ea10711a9d50cb&headckid=29ea10711a9d50cb&curPage=2");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&key=%E7%A7%BB%E5%8A%A8%E7%AB%AF&ckid=29ea10711a9d50cb&headckid=29ea10711a9d50cb&curPage=3");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&key=%E7%A7%BB%E5%8A%A8%E7%AB%AF&ckid=29ea10711a9d50cb&headckid=29ea10711a9d50cb&curPage=4");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=1&sortFlag=15&fromSearchBtn=1&headckid=fff933876e2cf912&key=%E4%BA%A7%E5%93%81%E6%80%BB%E7%9B%91");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&headckid=27c52293bb493ae7&key=%E4%BA%A7%E5%93%81%E6%80%BB%E7%9B%91&ckid=27c52293bb493ae7&curPage=1");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&headckid=27c52293bb493ae7&key=%E4%BA%A7%E5%93%81%E6%80%BB%E7%9B%91&ckid=27c52293bb493ae7&curPage=2");
			urllist.add("https://www.liepin.com/zhaopin/?industries=&dqs=020&salary=&jobKind=&pubTime=&compkind=&compscale=&industryType=&searchType=1&clean_condition=&isAnalysis=&init=-1&sortFlag=15&fromSearchBtn=2&headckid=27c52293bb493ae7&key=%E4%BA%A7%E5%93%81%E6%80%BB%E7%9B%91&ckid=27c52293bb493ae7&curPage=3");
//			
//			urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=");
//			for (int i = 1; i <=500; i++) {
//				urllist.add("https://www.liepin.com/sh/zhaopin/?sfrom=click-pc_homepage-centre_searchbox-search_new&key=&curPage=" +( i));
//			}
			
			saveInfo(urllist);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void saveInfo(List<String> urllist) throws Exception, InterruptedException {
		for (String string : urllist) {
			collect(string);
			System.out.println("================4========");
			Set<String> infos = vmap.keySet();
			Iterator<String> info = infos.iterator();
			while (info.hasNext()) {
				String jobcomp = info.next();
				int in = Integer.valueOf(vmap.get(jobcomp)[0].toString());
				System.out.println(in + "   " + jobcomp);
			}

			Set<String> infos2 = vmap.keySet();
			Iterator<String> info2 = infos2.iterator();
			while (info2.hasNext()) {
				String jobcomp = info2.next();
				Object[] is = vmap.get(jobcomp);
				String companyinfo = (String) is[2];
				String jobinfo = (String) is[1];
				JobInfo jobii = new JobInfo();
				jobii.setCompany(companyinfo.split(" ")[0]);
				jobii.setCreateDate(new Date());
				jobii.setJobxz(is[4] + "");
				jobii.setUrl(is[5] + "");
				jobii.setSalary(jobinfo.split(" ")[1]);
				jobii.setTitile(jobinfo.split(" ")[0]);
				jobii.setType("liepin");
				jobii.setJobtime(jobinfo.split(" ")[5]);
				jobii.setJobinfo(is[6]+"");
				jobii.setCompanyinfo(is[7]+"");
				jobii.setIsNew(is[8]+"");
				jobInfoDao.save(jobii);
			}
			
			vmap = new HashMap<String, Object[]>();
			// Thread.sleep(50000);
			Thread.sleep(2000);
		}
	}
}
