package gyqx.spdhdi.myInfos.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import common.db.SimpleDao;
import common.utils.SysAtomUtil;
import common.utils.SysConfigUtil;
import gyqx.spdhdi.myInfos.dao.mapper.IProvRegistInfoPushMapper;
import gyqx.spdhdi.myInfos.vo.ProvRegistGoodsPushQR;
import gyqx.spdhdi.myInfos.vo.ProvRegistInfoPushVo;
import gyqx.spdhdi.po.ProvFrwts;
import gyqx.spdhdi.po.ProvRegistInfoPush;

@Repository
public class ProvRegistInfoPushDao {
	@Resource
	private SimpleDao dao;
	@Resource
	private SysConfigUtil configUtil;
	@Resource
	private SysAtomUtil atomUtil;
	@Resource
	private IProvRegistInfoPushMapper mapper;

	public ProvFrwts getFrwts(ProvRegistInfoPushVo infoPushVo) {
		String sql = "SELECT * from prov_frwts p WHERE p.prov_id = ? and p.hos_id = ?";
		List<ProvFrwts> list = dao.queryForList(sql, ProvFrwts.class, infoPushVo.getProvId(),infoPushVo.getHosId());
		return  list.size() < 1 ? null : list.get(0);
	}

	public ProvRegistInfoPush get(String id) {
		return dao.get(ProvRegistInfoPush.class, id);
	}

	public ProvRegistInfoPush insertAndGet(ProvRegistInfoPush provRegistInfoPush) throws Exception {
		return dao.insertAndGet(provRegistInfoPush);
	}

	public ProvRegistInfoPush updateAndGet(ProvRegistInfoPush provRegistInfoPush) throws Exception {
		return dao.updateAndGet(provRegistInfoPush);
	}

	public int updateFields(ProvRegistInfoPush provRegistInfoPush, String fieldNames) throws Exception {
		return dao.updateFields(provRegistInfoPush, fieldNames);
	}

	public int updateNotNullFields(ProvRegistInfoPush provRegistInfoPush) throws Exception {
		return dao.updateNotNullFields(provRegistInfoPush);
	}

	public void delete(ProvRegistInfoPush provRegistInfoPush) throws Exception {
		dao.delete(provRegistInfoPush);
	}

	public ProvRegistInfoPush getByFieldName(String fieldName, Object fieldValue) throws Exception {
		return (ProvRegistInfoPush) dao.getByFieldName(ProvRegistInfoPush.class, fieldName, fieldValue);
	}

	public List<ProvRegistInfoPush> query(String predicate, String orderBy, Object... fieldValue) throws Exception {
		return (List<ProvRegistInfoPush>) dao.queryBeanList(ProvRegistInfoPush.class, predicate, orderBy, fieldValue);
	}

	public long count(String predicate, Object... fieldValue) throws Exception {
		return dao.count(ProvRegistInfoPush.class, predicate, fieldValue);
	}

	public int updateVersion(Object bean) throws Exception {
		return dao.updateVersion(bean);
	}

	public List<ProvRegistInfoPush> list(ProvRegistInfoPush qbean) throws Exception {

		return mapper.list(qbean);
	}

	public List<ProvRegistInfoPushVo> pushedRegCertsProvListByPage(ProvRegistInfoPushVo queryObject) {
		return mapper.pushedRegCertsProvListByPage(queryObject);
	}

	public List<ProvRegistInfoPushVo> pushedGoodsProvListByPage(ProvRegistInfoPushVo queryObject) {
		return mapper.pushedGoodsProvListByPage(queryObject);
	}

	public List<ProvRegistGoodsPushQR> pushedGoodsList(ProvRegistInfoPushVo queryObject) {
		return mapper.pushedGoodsList(queryObject);
	}

	public ProvRegistInfoPush update(ProvRegistInfoPush bean) {
		mapper.update(bean);
		return get(bean.getId());
	}

	public ProvRegistInfoPush update(ProvRegistInfoPushVo provRegistInfoPushVo) {
		mapper.update(provRegistInfoPushVo);
		return get(provRegistInfoPushVo.getId());
	}

}
