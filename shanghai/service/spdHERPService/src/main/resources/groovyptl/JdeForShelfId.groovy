import groovy.sql.Sql
import gyqx.spdherp.spdOutPtl.vo.MessageResponse
import org.apache.commons.dbcp2.BasicDataSource
/**
 * Created by cjzyw on 2018/6/29.
 */
class JdeForShelfId {
    static MessageResponse forShelfId(Object... objects){
        def resp = new MessageResponse()
        resp.resultCode = 0
        resp.resultContent = "success"
        def dataSource = new BasicDataSource(driverClassName: "oracle.jdbc.OracleDriver",
                url: 'jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=128.100.200.181)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=JDEMIS)))',
                username: 'spdru', password: 'spdru')
        def spdsql = "SELECT TRIM(商品编码) as erpcode,TRIM(库位) as shelfId from MVMID.SV_IF_SPD_SHHSBYKC where 商品编码 = ?"
        def spdsqlout = "UPDATE out_stock_list set shelf_id = ?,version = version + 1 where goods_id = ? and pid = ?"
        Sql sql = new Sql(dataSource)
        //spd数据源
        Sql sql1 = new Sql(objects[3])
        def jdeShelfInfo = sql.rows(spdsql,[objects[0]])
        if(jdeShelfInfo.size()==0){
            resp.resultCode = -1
            resp.resultContent = "从JDE未找到该产品对应的货位编码"
            return resp
        }
        def shelfId = jdeShelfInfo.first().shelfId
        //更新出库单明细表
        sql1.execute(spdsqlout, [shelfId, objects[1], objects[2]])
        resp.data = shelfId
        return resp
    }
}
