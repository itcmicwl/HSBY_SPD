package gyqx.spdherp.reportAnalysis.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;

import common.db.SimpleDao;
import gyqx.spdherp.provManager.vo.OutdateCertsVo;
import gyqx.spdherp.reportAnalysis.service.IIndexStatisticsService;
import gyqx.spdherp.reportAnalysis.vo.CertificateStacsVo;

@Service
public class IndexStatisticsService implements IIndexStatisticsService{
	
	@Resource
	private SimpleDao simpleDao;
	/**
	 * 查询医院供应商证各证照数量分布
	 * @param hosId 医院id
	 * @return
	 */
	public List<CertificateStacsVo> queryCertificateStacs(String hosId){
		StringBuffer sb = new StringBuffer();
		if(StringUtil.isNotEmpty(hosId)){
			sb.append(" SELECT a.certKind,count(1) qty FROM ( ")
			.append(" 			SELECT b.id,'供应商营业执照' AS certKind,10 AS cert_type,b.expdt_begin_date,b.expdt_end_date, ")
			.append(" 				b.company_id AS about_id,c.cname AS about_name,b.cert_code AS about_code,b.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,b.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM bas_company_certificate b LEFT JOIN bas_company_info c ON b.company_id = c.id ")
			.append(" 				WHERE b.cert_kind=1 AND b.company_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 					UNION ALL ")
			.append(" 			SELECT b.id,'供应商经营许可证' AS certKind,20 AS cert_type,b.expdt_begin_date,b.expdt_end_date, ")
			.append(" 				b.company_id AS about_id,c.cname AS about_name,b.cert_code AS about_code,b.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,b.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM bas_company_certificate b LEFT JOIN bas_company_info c ON b.company_id = c.id ")
			.append(" 				WHERE b.cert_kind=2 AND b.company_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 					UNION ALL ")
			.append(" 			SELECT b.id,'供应商税务登记证' AS certKind,30 AS cert_type,b.expdt_begin_date,b.expdt_end_date, ")
			.append(" 				b.company_id AS about_id,c.cname AS about_name,b.cert_code AS about_code,b.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,b.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM bas_company_certificate b LEFT JOIN bas_company_info c ON b.company_id = c.id ")
			.append(" 				WHERE b.cert_kind=3 AND b.company_id  in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 					UNION ALL ")
			.append(" 			SELECT m.id,'生产厂家营业执照' AS certKind,40 AS cert_type,m.expdt_begin_date,m.expdt_end_date, ")
			.append(" 				m.mfrs_id AS about_id,b.mfrs_name AS about_name,m.cert_code AS about_code,m.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,m.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM prov_mfrs_certificate m LEFT JOIN bas_mfrs_info b ON b.id = m.mfrs_id ")
			.append(" 				WHERE m.cert_kind = 1 ")
			.append(" 				AND m.p_id IN ( ")
			.append(" 					SELECT p.id FROM prov_mfrs_info p WHERE p.prov_id  in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 				) ")
			.append(" 				AND m.mfrs_id IN ( ")
			.append(" 					SELECT DISTINCT p.mfrs_id FROM prov_regist_info_push p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 					AND p.hos_id = '"+hosId+"' ")
			.append(" 				) ")
			.append(" 					UNION ALL ")
			.append(" 			SELECT m.id,'生产厂家经营许可证' AS certKind,50 AS cert_type,m.expdt_begin_date,m.expdt_end_date, ")
			.append(" 				m.mfrs_id  AS about_id,b.mfrs_name AS about_name,m.cert_code AS about_code,m.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,m.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM prov_mfrs_certificate m LEFT JOIN bas_mfrs_info b ON b.id = m.mfrs_id ")
			.append(" 				WHERE m.cert_kind = 2 ")
			.append(" 				AND m.p_id IN ( ")
			.append(" 					SELECT p.id FROM prov_mfrs_info p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 				) ")
			.append("				AND m.mfrs_id IN ( ")
			.append("					SELECT DISTINCT p.mfrs_id FROM prov_regist_info_push p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"')  AND p.hos_id = '"+hosId+"' ")
			.append("				) ")
			.append("					UNION ALL ")
			.append("			SELECT m.id,'生产厂家税务登记证' AS certKind,60 AS cert_type,m.expdt_begin_date,m.expdt_end_date, ")
			.append("				m.mfrs_id  AS about_id,b.mfrs_name AS about_name,m.cert_code AS about_code,m.is_validing AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,m.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_mfrs_certificate m LEFT JOIN bas_mfrs_info b ON b.id = m.mfrs_id ")
			.append("				WHERE m.cert_kind = 3 ")
			.append("				AND m.p_id IN ( ")
			.append("					SELECT p.id FROM prov_mfrs_info p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				) ")
			.append("				AND m.mfrs_id IN ( ")
			.append("					SELECT DISTINCT p.mfrs_id FROM prov_regist_info_push p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("					AND p.hos_id = '"+hosId+"' ")
			.append("				) ")
			.append("					UNION ALL ")
			.append("			SELECT p.id,'医疗器械注册证' AS certKind,70 AS cert_type,p.expdt_begin_date,p.expdt_end_date, ")
			.append("				p.prov_id AS about_id,p.product_name AS about_name,p.certificate_code AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,p.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_registration_info p WHERE p.id IN  ")
			.append("				(SELECT p.prov_regist_id FROM prov_regist_info_push p  ")
			.append("				WHERE p.push_status='30'AND p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND p.hos_id = '"+hosId+"') ")
			.append("					UNION ALL ")
			.append("			SELECT f.id,'法人委托书' AS certKind,80 AS cert_type,f.expdt_begin_date,f.expdt_end_date, ")
			.append("				f.prov_id AS about_id,b.cname AS about_name,f.wtr_move_num AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,f.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_frwts f LEFT JOIN bas_company_info b ON b.id = f.prov_id ")
			.append("				WHERE f.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND f.hos_id = '"+hosId+"' ")
			.append("					UNION ALL ")
			.append("			SELECT f.id,'承诺书' AS certKind,90 AS cert_type,f.expdt_begin_date,f.expdt_end_date, ")
			.append("				f.prov_id AS about_id,b.cname AS about_name,'' AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,f.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_cns f LEFT JOIN bas_company_info b ON b.id = f.prov_id ")
			.append("				WHERE f.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND f.hos_id = '"+hosId+"' ")
			.append("					UNION ALL ")
			.append("			SELECT f.id,'销售代表' AS certKind,91 AS cert_type,f.expdt_begin_date,f.expdt_end_date, ")
			.append("				f.prov_id AS about_id,b.cname AS about_name,'' AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,f.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_sales_rep f LEFT JOIN bas_company_info b ON b.id = f.prov_id ")
			.append("				WHERE f.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND f.hos_id = '"+hosId+"' ")
			.append("					UNION ALL ")
			.append("			SELECT f.id,'廉政协议书' AS certKind,92 AS cert_type,f.expdt_begin_date,f.expdt_end_date, ")
			.append("				f.prov_id AS about_id,b.cname AS about_name,'' AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,f.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_incorrupt_agreement f LEFT JOIN bas_company_info b ON b.id = f.prov_id ")
			.append("				WHERE f.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND f.hos_id = '"+hosId+"' ")
			.append("	) a GROUP BY a.certKind ");
			return simpleDao.queryForList(sb.toString(), CertificateStacsVo.class);
		}
		return null;
	}
	
	
	
	/**
	 * 查询医院供应商证各证照过期情况
	 * @param hosId 医院id
	 * @return
	 */
	public List<OutdateCertsVo> queryProvOutdateCerts(String hosId){
		StringBuffer sb = new StringBuffer();
		if(StringUtil.isNotEmpty(hosId)){
			sb.append(" SELECT a.id,a.certKind,a.cert_Type AS certType,a.expdt_begin_date AS expdtBeginDate,a.expdt_end_date AS expdtEndDate,a.is_validing AS isValiding, ")
			.append("  a.outDate,a.about_id AS aboutId,a.about_name AS aboutName,a.about_code AS aboutCode FROM ( ")
			.append(" 			SELECT b.id,'供应商营业执照' AS certKind,10 AS cert_type,b.expdt_begin_date,b.expdt_end_date, ")
			.append(" 				b.company_id AS about_id,c.cname AS about_name,b.cert_code AS about_code,b.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,b.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM bas_company_certificate b LEFT JOIN bas_company_info c ON b.company_id = c.id ")
			.append(" 				WHERE b.cert_kind=1 AND b.company_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 					UNION ALL ")
			.append(" 			SELECT b.id,'供应商经营许可证' AS certKind,20 AS cert_type,b.expdt_begin_date,b.expdt_end_date, ")
			.append(" 				b.company_id AS about_id,c.cname AS about_name,b.cert_code AS about_code,b.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,b.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM bas_company_certificate b LEFT JOIN bas_company_info c ON b.company_id = c.id ")
			.append(" 				WHERE b.cert_kind=2 AND b.company_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 					UNION ALL ")
			.append(" 			SELECT b.id,'供应商税务登记证' AS certKind,30 AS cert_type,b.expdt_begin_date,b.expdt_end_date, ")
			.append(" 				b.company_id AS about_id,c.cname AS about_name,b.cert_code AS about_code,b.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,b.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM bas_company_certificate b LEFT JOIN bas_company_info c ON b.company_id = c.id ")
			.append(" 				WHERE b.cert_kind=3 AND b.company_id  in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 					UNION ALL ")
			.append(" 			SELECT m.id,'生产厂家营业执照' AS certKind,40 AS cert_type,m.expdt_begin_date,m.expdt_end_date, ")
			.append(" 				m.mfrs_id AS about_id,b.mfrs_name AS about_name,m.cert_code AS about_code,m.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,m.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM prov_mfrs_certificate m LEFT JOIN bas_mfrs_info b ON b.id = m.mfrs_id ")
			.append(" 				WHERE m.cert_kind = 1 ")
			.append(" 				AND m.p_id IN ( ")
			.append(" 					SELECT p.id FROM prov_mfrs_info p WHERE p.prov_id  in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 				) ")
			.append(" 				AND m.mfrs_id IN ( ")
			.append(" 					SELECT DISTINCT p.mfrs_id FROM prov_regist_info_push p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 					AND p.hos_id = '"+hosId+"' ")
			.append(" 				) ")
			.append(" 					UNION ALL ")
			.append(" 			SELECT m.id,'生产厂家经营许可证' AS certKind,50 AS cert_type,m.expdt_begin_date,m.expdt_end_date, ")
			.append(" 				m.mfrs_id  AS about_id,b.mfrs_name AS about_name,m.cert_code AS about_code,m.is_validing AS is_validing, ")
			.append(" 				TIMESTAMPDIFF(DAY,m.expdt_end_date,NOW()) outDate ")
			.append(" 				FROM prov_mfrs_certificate m LEFT JOIN bas_mfrs_info b ON b.id = m.mfrs_id ")
			.append(" 				WHERE m.cert_kind = 2 ")
			.append(" 				AND m.p_id IN ( ")
			.append(" 					SELECT p.id FROM prov_mfrs_info p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append(" 				) ")
			.append("				AND m.mfrs_id IN ( ")
			.append("					SELECT DISTINCT p.mfrs_id FROM prov_regist_info_push p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"')  AND p.hos_id = '"+hosId+"' ")
			.append("				) ")
			.append("					UNION ALL ")
			.append("			SELECT m.id,'生产厂家税务登记证' AS certKind,60 AS cert_type,m.expdt_begin_date,m.expdt_end_date, ")
			.append("				m.mfrs_id  AS about_id,b.mfrs_name AS about_name,m.cert_code AS about_code,m.is_validing AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,m.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_mfrs_certificate m LEFT JOIN bas_mfrs_info b ON b.id = m.mfrs_id ")
			.append("				WHERE m.cert_kind = 3 ")
			.append("				AND m.p_id IN ( ")
			.append("					SELECT p.id FROM prov_mfrs_info p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				) ")
			.append("				AND m.mfrs_id IN ( ")
			.append("					SELECT DISTINCT p.mfrs_id FROM prov_regist_info_push p WHERE p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("					AND p.hos_id = '"+hosId+"' ")
			.append("				) ")
			.append("					UNION ALL ")
			.append("			SELECT p.id,'医疗器械注册证' AS certKind,70 AS cert_type,p.expdt_begin_date,p.expdt_end_date, ")
			.append("				p.prov_id AS about_id,p.product_name AS about_name,p.certificate_code AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,p.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_registration_info p WHERE p.id IN  ")
			.append("				(SELECT p.prov_regist_id FROM prov_regist_info_push p  ")
			.append("				WHERE p.push_status='30'AND p.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND p.hos_id = '"+hosId+"') ")
			.append("					UNION ALL ")
			.append("			SELECT f.id,'法人委托书' AS certKind,80 AS cert_type,f.expdt_begin_date,f.expdt_end_date, ")
			.append("				f.prov_id AS about_id,b.cname AS about_name,f.wtr_move_num AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,f.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_frwts f LEFT JOIN bas_company_info b ON b.id = f.prov_id ")
			.append("				WHERE f.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND f.hos_id = '"+hosId+"' ")
			.append("					UNION ALL ")
			.append("			SELECT f.id,'承诺书' AS certKind,90 AS cert_type,f.expdt_begin_date,f.expdt_end_date, ")
			.append("				f.prov_id AS about_id,b.cname AS about_name,'' AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,f.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_cns f LEFT JOIN bas_company_info b ON b.id = f.prov_id ")
			.append("				WHERE f.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND f.hos_id = '"+hosId+"' ")
			.append("					UNION ALL ")
			.append("			SELECT f.id,'销售代表' AS certKind,91 AS cert_type,f.expdt_begin_date,f.expdt_end_date, ")
			.append("				f.prov_id AS about_id,b.cname AS about_name,'' AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,f.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_sales_rep f LEFT JOIN bas_company_info b ON b.id = f.prov_id ")
			.append("				WHERE f.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND f.hos_id = '"+hosId+"' ")
			.append("					UNION ALL ")
			.append("			SELECT f.id,'廉政协议书' AS certKind,92 AS cert_type,f.expdt_begin_date,f.expdt_end_date, ")
			.append("				f.prov_id AS about_id,b.cname AS about_name,'' AS about_code,0 AS is_validing, ")
			.append("				TIMESTAMPDIFF(DAY,f.expdt_end_date,NOW()) outDate ")
			.append("				FROM prov_incorrupt_agreement f LEFT JOIN bas_company_info b ON b.id = f.prov_id ")
			.append("				WHERE f.prov_id in (SELECT prov_id FROM prov_hos_info  WHERE  `status` = 2 AND  hos_id = '"+hosId+"') ")
			.append("				AND f.hos_id = '"+hosId+"' ")
			.append("	) a ");
			return simpleDao.queryForList(sb.toString(), OutdateCertsVo.class);
		}
		return null;
	}
}
