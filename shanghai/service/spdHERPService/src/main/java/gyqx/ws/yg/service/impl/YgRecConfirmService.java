package gyqx.ws.yg.service.impl;

import common.db.query.QueryInfo;
import common.db.query.QueryResult;
import common.utils.PageUtils;
import gyqx.ws.yg.common.*;
import gyqx.ws.yg.dao.YgRecConfirmDao;
import gyqx.ws.yg.service.IYgRecConfirmService;
import gyqx.ws.yg.service.IYgSendBillListStateService;
import gyqx.ws.yg.vo.SMPType;
import gyqx.ws.yg.vo.repVo.YY131_REP_XML;
import gyqx.ws.yg.vo.repVo.YY161_REP_DETAIL;
import gyqx.ws.yg.vo.repVo.YY161_REP_XML;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_DETAIL;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_MAIN;
import gyqx.ws.yg.vo.reqVo.YY131_REQ_XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class YgRecConfirmService implements IYgRecConfirmService {
	
	@Resource
	private YgRecConfirmDao dao;
	@Resource
	private YgServices ygServices;
	@Resource
	private IYgSendBillListStateService ygSendBillStateSer;
    @Resource
    private YgSendUtil ygUtils;
    @Resource
    private IYgSendBillListStateService ygSBStateSer;
    private Logger logger = LoggerFactory.getLogger(YgRecConfirmService.class);
	@Override
	public YY131_REQ_DETAIL get(String psmxbh) throws Exception {
		return dao.get(psmxbh);
	}

	@Override
	public int updateByBatch(List<YY131_REQ_DETAIL> lst) throws Exception {
		return dao.updateByBatch(lst);
	}

	@Override
	public int insertByBatch(List<YY131_REQ_DETAIL> lst) throws Exception {
		List<String> psmxArr = lst.stream().map(o->{return o.getPsmxbh();}).collect(Collectors.toList());
		YY161_REP_DETAIL yy161 = new YY161_REP_DETAIL();
        yy161.setPsmxArr(psmxArr);
		List<YY161_REP_DETAIL> lstYY161 = ygSendBillStateSer.list(yy161);
		for (YY161_REP_DETAIL yy161_rep_detail : lstYY161) {
			Optional<YY131_REQ_DETAIL> yy131OP = lst.stream().filter(o->{return o.getPsmxbh().equals(yy161_rep_detail.getPsmxbh());}).findFirst();
			if(yy131OP.isPresent()){
				YY131_REQ_DETAIL yy131d = yy131OP.get();
				yy161_rep_detail.setPsmxzt("25");
				yy161_rep_detail.setYsytgs(yy131d.getYstgs());
				yy161_rep_detail.setYsybgs(yy131d.getYsbgs());
			}
		}
		ygSendBillStateSer.updateByBatch(lstYY161);
		return dao.insertByBatch(lst);
	}

	@Override
	public int update(YY131_REQ_DETAIL bean) throws Exception {
		return dao.update(bean);
	}

	@Override
	public int insert(YY131_REQ_DETAIL bean) throws Exception {
		return dao.insert(bean);
	}

	@Override
	public List<YY131_REQ_DETAIL> list(YY131_REQ_DETAIL queryInfo) throws Exception {
		return dao.list(queryInfo);
	}

	@Override
	public QueryResult<YY131_REQ_DETAIL> listByPage(QueryInfo<YY131_REQ_DETAIL> queryInfo) throws Exception {
		PageUtils.startPage(queryInfo);
		return PageUtils.endPage(dao.list(queryInfo.getQueryObject()));
	}

    @Override
    public int setBillConfirm(String billId) throws Exception {
        return dao.setBillConfirm(billId);
    }

    @Override
    public void autoRecSendBill() throws Exception {
        YY131_REQ_DETAIL query = new YY131_REQ_DETAIL();
        query.setIsSend(0);
        List<YY131_REQ_DETAIL> lstUnConfim = this.list(query);
        //按配送单编号分组
        Map<String, List<YY131_REQ_DETAIL>> collect = lstUnConfim.stream()
                .collect(Collectors.groupingBy(YY131_REQ_DETAIL::getPsdbh));
        //一单一单验收
        collect.forEach(new BiConsumer<String,List<YY131_REQ_DETAIL>>(){
            @Override
            public void accept(String s, List<YY131_REQ_DETAIL> lstYY131D){
                YY131_REQ_XML yy131 = new YY131_REQ_XML();
                yy131.setReqHead(HostUtils.getReqHead(""));
                yy131.setDetail(lstYY131D);
                YY131_REQ_MAIN YY131M = new YY131_REQ_MAIN();
                YY131M.setJls(lstYY131D.size());
                YY131M.setPsyslx(Constance.DISTR_16.ACTUAL.toString());
                yy131.setMain(YY131M);
                String xmlData = JAXBUtil.toXML(yy131,YY131_REQ_XML.class);
                try {
                    YY131_REP_XML res = ygUtils.sendRecv(YY131_REP_XML.class, SMPType.YY131,xmlData);
                    setHadRecConfirm(s);
                }catch (Exception e){
                    logger.error(String.format("验收配送单%s 失败。",s),e);
                }
            }
        });
    }

    @Override
    public int setHadRecConfirm(String billId) throws Exception {
        YY161_REP_XML ygRes = ygSBStateSer.checkIn(billId);
        YY131_REQ_DETAIL query =new YY131_REQ_DETAIL();
        query.setPsdbh(billId);
        List<YY131_REQ_DETAIL> lst = this.list(query);
        if(ygRes.getRepHead().getRes().equals("00000")){
            for (YY131_REQ_DETAIL item : lst) {
              Optional<YY161_REP_DETAIL>  OpYY161 = ygRes.getDetail().stream().filter(o->{return o.getPsmxbh().equals(item.getPsmxbh());}).findFirst();
              if(OpYY161.isPresent()){
                  if(OpYY161.get().getPsmxzt().equals("30")){
                      item.setIsSend(1);
                  }
              }
            }
            this.updateByBatch(lst);
        }
        return  0;
    }
}


